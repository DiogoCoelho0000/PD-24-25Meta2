package pt.isec.pd.db;

import pt.isec.pd.comum.enumeracoes.Estados;
import pt.isec.pd.comum.modelos.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Bd {


    private static Connection conn = null;
    private static boolean estaConectado = false;
    //private static final Object lock = new Object();

    public static boolean verificaExistenciaBD(String bd) {
        File ficheiroBD = new File(bd);
        return ficheiroBD.exists();
    }

    public static void ligaBD(String bd) {
        try {
            String link = "jdbc:sqlite:";
            System.out.println("A ligar à base de dados...");
            bd = "src/pt/isec/pd/db/" + bd + ".db";
            if (verificaExistenciaBD(bd)) {
                conn = DriverManager.getConnection(link + bd);
                //conn.setAutoCommit(false);
                //System.out.println("->" + conn);
                System.out.println("Ligação efectuada com sucesso!");
                setEstaConectado(true);
            } else {
                conn = DriverManager.getConnection(link + bd);
                criaTabelas();
                //conn.setAutoCommit(false);
                //System.out.println("->" + conn);
                System.out.println("Ligação efectuada com sucesso!");
                setEstaConectado(true);
                versaoUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean isEstaConectado() {
        return estaConectado;
    }

    public static void setEstaConectado(boolean estaConectado) {
        Bd.estaConectado = estaConectado;
    }

    //TERMINAR ISTO DEPOIS
    private static void criaTabelas(/*String bd*/) {
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("CREATE TABLE DESPESA (\n" +
                    "    ID            INTEGER PRIMARY KEY AUTOINCREMENT\n" +
                    "                          UNIQUE,\n" +
                    "    GROUP_ID      INTEGER,\n" +
                    "    VALOR,\n" +
                    "    DESCRICAO     TEXT,\n" +
                    "    DATA,\n" +
                    "    PAGA_POR      INTEGER REFERENCES USERS (ID),\n" +
                    "    REGISTADA_POR INTEGER REFERENCES USERS (ID),\n" +
                    "    FOREIGN KEY (\n" +
                    "        GROUP_ID\n" +
                    "    )\n" +
                    "    REFERENCES GRUPO (ID) ON DELETE CASCADE\n" +
                    ");");
            stmt.executeUpdate("CREATE TABLE GRUPO (\n" +
                    "    ID         INTEGER PRIMARY KEY AUTOINCREMENT\n" +
                    "                       NOT NULL\n" +
                    "                       UNIQUE,\n" +
                    "    NOME       TEXT    NOT NULL,\n" +
                    "    CRIADO_POR         REFERENCES USERS (EMAIL) \n" +
                    ");");
            stmt.executeUpdate("CREATE TABLE INTEGRA (\n" +
                    "    USER_ID   INTEGER REFERENCES USERS (ID),\n" +
                    "    GROUP_ID  INTEGER REFERENCES GRUPO (ID),\n" +
                    "    PRIMARY KEY (\n" +
                    "        USER_ID,\n" +
                    "        GROUP_ID\n" +
                    "    )\n" +
                    ");");
            stmt.executeUpdate("CREATE TABLE USERS (\n" +
                    "    ID         INTEGER NOT NULL\n" +
                    "                       UNIQUE\n" +
                    "                       PRIMARY KEY AUTOINCREMENT,\n" +
                    "    NOME       TEXT    NOT NULL,\n" +
                    "    N_TELEFONE NUMERIC NOT NULL,\n" +
                    "    EMAIL      TEXT    NOT NULL\n" +
                    "                       UNIQUE,\n" +
                    "    PASSWORD   TEXT    NOT NULL\n" +
                    ");");
            stmt.executeUpdate("CREATE TABLE VERSAO (NUMERO_VERSAO INTEGER NOT NULL PRIMARY KEY);");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Estados setGrupoDB(String grupoNome, String nomeUser) {

        String queryVerificaGrupo = "SELECT 1 FROM GRUPO WHERE NOME = ?";

        try (PreparedStatement stmt = conn.prepareStatement(queryVerificaGrupo)) {

            stmt.setString(1, grupoNome);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {

                return null;
            }
            try {
                Statement stmtu = conn.createStatement();

                stmtu.executeUpdate("INSERT INTO GRUPO (NOME, CRIADO_POR)" +
                        " VALUES ('" +
                        grupoNome + "','" +
                        nomeUser +
                        "')");
                versaoUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return Estados.GRUPO_REGISTADO_COM_SUCESSO;
    }

    public static Estados integraGrupo(String grupoNome, String email) {

        //User user = null;
        String userID = null;
        String grupoID = null;
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM USERS WHERE EMAIL='" +
                    email + "'");
            if (rs.next()) {
                userID = rs.getString("ID");
            }

            try {
                //Statement stmt = conn.createStatement();
                ResultSet rs2 = stmt.executeQuery("SELECT * FROM GRUPO WHERE NOME='" +
                        grupoNome + "'");
                if (rs.next()) {
                    grupoID = rs2.getString("ID");
                }
            } catch (SQLException e) {
                System.out.println("O Utilizador não existe!");
                /*user.setEstado(false);*/
                /*return user;*/
            }

            try {
                //Statement stmt = conn.createStatement();

                stmt.executeUpdate("INSERT INTO INTEGRA (USER_ID, GROUP_ID)" +
                        " VALUES ('" +
                        userID + "','" +
                        grupoID +
                        "')");
                versaoUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("O Utilizador não existe!");
            /*user.setEstado(false);*/
            /*return user;*/
        }
        return Estados.GRUPO_USER_INSERIDO_COM_SUCESSO;
    }

    public static Grupos listarGruposDB(String solicitadoPor) {
        List<Grupos> grupoList = new ArrayList<>();
        Grupos grupos = null;
        String sql = "SELECT g.NOME " +
                "FROM GRUPO g " +
                "JOIN INTEGRA i ON g.ID = i.GROUP_ID " +
                "JOIN USERS u ON i.USER_ID = u.ID " +
                "WHERE u.EMAIL = '" + solicitadoPor + "'";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                String nomeGrupo = rs.getString("NOME");
                grupos = new Grupos();
                grupos.setNomeGrupo(nomeGrupo);
                grupoList.add(grupos);
                grupos.setGruposList(grupoList);
                //grupoList.add(nomeGrupo);
            }
            //ACRESCENTEI ISTO <- Hugo
            stmt.close();
            rs.close();
            //----------------
        } catch (SQLException e) {
            System.err.println("Erro ao listar grupos: " + e.getMessage());
        }
        return grupos;
    }

    public static Grupos getGrupoDB(String email, String grupoNome) {
        Grupos grupo = null;

        String sql = "SELECT g.NOME " +
                "FROM GRUPO g " +
                "JOIN INTEGRA i ON g.ID = i.GROUP_ID " +
                "JOIN USERS u ON i.USER_ID = u.ID " +
                "WHERE u.EMAIL = '" + email + "' AND g.NOME = '" + grupoNome + "'";
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String nomeGrupoDB = rs.getString("NOME");
                grupo = new Grupos();
                grupo.setNomeGrupo(nomeGrupoDB);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao encontrar grupo: " + e.getMessage());
        }
        return grupo;
    }

    public static Estados setUserDB(String nome, int nTelefone, String Email, String password) {
        try {
            Statement stmt = conn.createStatement();

            stmt.executeUpdate("INSERT INTO USERS (NOME, N_TELEFONE, EMAIL, PASSWORD)" +
                    " VALUES ('" +
                    nome + "','" +
                    nTelefone + "','" +
                    Email + "','" +
                    password +
                    "')");
            versaoUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        User user = new User();
        user.setNome(nome);
        user.setEmail(Email);
        user.setnTelefone(nTelefone);
        user.setPassword(password);
        return Estados.USER_REGISTADO_COM_SUCESSO.setDados(user);
    }

    public static User getUserDB(String email, String password) {
        User user = null;
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM USERS WHERE EMAIL='" +
                    email + "' AND PASSWORD= '" + password + "'");
            if (rs.next()) {
                String emailDB = rs.getString("EMAIL");
                String nomeDB = rs.getString("NOME");
                String passDB = rs.getString("PASSWORD");
                String telefoneDB = rs.getString("N_TELEFONE");
                //System.out.println( "\nTABELA: \n" + nomeDB + "\n" + emailDB + "\n"+ passDB + "\n" + telefoneDB + "\n");
                user = new User();
                user.setNome(nomeDB);
                user.setEmail(emailDB);
                user.setnTelefone(Integer.parseInt(telefoneDB));
                user.setPassword(passDB);
                user.setEstado(true);

            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("O Utilizador não existe!");
            user.setEstado(false);
            return user;
        }

        return user;
    }

    public static void versaoUpdate() {
        int NUMERO_VERSAO = 0;
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT NUMERO_VERSAO FROM VERSAO" +
                    " ORDER BY NUMERO_VERSAO DESC LIMIT 1");
            if (rs.next()) {
                NUMERO_VERSAO = rs.getInt("NUMERO_VERSAO");

                //System.out.println( "\nTABELA: \n" + NUMERO_VERSAO + "\n");
                NUMERO_VERSAO++; //incrementa a versão
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {

            Statement stmt = conn.createStatement();

            stmt.executeUpdate("INSERT INTO VERSAO (NUMERO_VERSAO)" +
                    " VALUES ('" +
                    NUMERO_VERSAO +
                    "')");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static int obtemVersao() {
        int versao = 0;
        String query = "SELECT MAX(NUMERO_VERSAO) as NUMERO_VERSAO FROM VERSAO";

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            versao = rs.getInt("NUMERO_VERSAO");

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("O Utilizador não existe!");
        }

        return versao;
    }

    public static Estados criaDespesa(String email, String grupo, double despesa, String quemPagou, String descricao, String data) {
        String query = "INSERT INTO DESPESA (GROUP_ID, VALOR, DESCRICAO, DATA, PAGA_POR, REGISTADA_POR) " +
                "SELECT G.ID, " + despesa + ", '" + descricao + "', '" + data + "', U1.ID, U2.ID " +
                "FROM GRUPO G " +
                "JOIN INTEGRA I1 ON G.ID = I1.GROUP_ID " +
                "JOIN USERS U1 ON U1.ID = I1.USER_ID " +
                "JOIN USERS U2 ON U2.EMAIL = '" + email + "' " +
                "WHERE G.NOME = '" + grupo + "' " +
                "AND U1.EMAIL = '" + quemPagou + "' " +
                "AND EXISTS (SELECT 1 FROM INTEGRA i2 WHERE i2.GROUP_ID = g.ID AND i2.USER_ID IN (u1.ID, u2.ID))";

        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(query);
            versaoUpdate();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Estados.USER_CRIA_DESPESA_COM_SUCESSO;

    }

    public static Despesa historio(String grupo) {
        List<Despesa> despesaList = new ArrayList<>();
        Despesa despesa = null;
        String GRUPODB = null;
        String queryGrupoID = "SELECT ID " +
                "FROM GRUPO " +
                "WHERE NOME = '" + grupo + "'";

        try {
            Statement stmt = conn.createStatement();
            ResultSet rsID = stmt.executeQuery(queryGrupoID);
            if (rsID.next())
                GRUPODB = rsID.getString("ID");

            String query = "SELECT D.ID, D.DATA, D.VALOR, D.DESCRICAO, U1.NOME AS REGISTADA_POR, U.NOME AS PAGO_POR " +
                    "FROM DESPESA D " +
                    "JOIN USERS U ON D.PAGA_POR = U.ID " +
                    "JOIN USERS U1 ON D.REGISTADA_POR = U1.ID " +
                    "WHERE D.GROUP_ID = " + GRUPODB + " " +
                    "ORDER BY D.DATA ASC";
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String ID = rs.getString("ID");
                String DATA = rs.getString("DATA");
                String VALOR = rs.getString("VALOR");
                String DESCRICAO = rs.getString("DESCRICAO");
                String REGISTADA_POR = rs.getString("REGISTADA_POR");
                String PAGO_POR = rs.getString("PAGO_POR");
                despesa = new Despesa();
                despesa.setIdDespesa(ID);
                despesa.setData(DATA);
                despesa.setDespesa(Double.parseDouble(VALOR));
                despesa.setDescricao(DESCRICAO);
                despesa.setEmail(REGISTADA_POR);
                despesa.setQuemPagou(PAGO_POR);
                despesaList.add(despesa);
                despesa.setDespesaList(despesaList);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return despesa;
    }

}
