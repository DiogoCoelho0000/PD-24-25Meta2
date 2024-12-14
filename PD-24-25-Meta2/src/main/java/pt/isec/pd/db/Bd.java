package pt.isec.pd.db;

import pt.isec.pd.comum.enumeracoes.Estados;
import pt.isec.pd.comum.modelos.*;
import pt.isec.pd.models.Despesa;
import pt.isec.pd.models.Grupos;
import pt.isec.pd.models.User;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Bd {
    // Criar Servicos
    // Fazer servicos para a BD
    // Tem que ser alterado , colocar @Configuration e outras coisas
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
            bd = "src/main/java/pt/isec/pd/db/" + bd + ".db";
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
            stmt.executeUpdate("CREATE TABLE CONVITES (\n" +
                    "    ID              INTEGER  PRIMARY KEY AUTOINCREMENT\n" +
                    "                             UNIQUE,\n" +
                    "    GROUP_ID        INTEGER  NOT NULL,\n" +
                    "    USER_ID         INTEGER,\n" +
                    "    DESTINATARIO_ID INTEGER,\n" +
                    "    ESTADO          TEXT,\n" +
                    "    DATA_ENVIO      DATETIME DEFAULT (DATETIME(CURRENT_TIMESTAMP, '+1 hour') ),\n" +
                    "    FOREIGN KEY (\n" +
                    "        USER_ID\n" +
                    "    )\n" +
                    "    REFERENCES USERS (ID),\n" +
                    "    FOREIGN KEY (\n" +
                    "        GROUP_ID\n" +
                    "    )\n" +
                    "    REFERENCES GRUPO (ID),\n" +
                    "    FOREIGN KEY (\n" +
                    "        DESTINATARIO_ID\n" +
                    "    )\n" +
                    "    REFERENCES USERS (ID) \n" +
                    ");");
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
            stmt.executeUpdate("CREATE TABLE DIVIDE_DESPESA (\n" +
                    "    DESPESA_ID               REFERENCES DESPESA (ID),\n" +
                    "    USER_ID                  REFERENCES USERS (ID),\n" +
                    "    GRUPO_ID                 REFERENCES GRUPO (ID),\n" +
                    "    VALOR_PARTILHADO NUMERIC NOT NULL,\n" +
                    "    PRIMARY KEY (\n" +
                    "        DESPESA_ID,\n" +
                    "        USER_ID\n" +
                    "    )\n" +
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
            stmt.executeUpdate("CREATE TABLE PAGAMENTO (\n" +
                    "   ID           INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                    "   GROUP_ID     INTEGER REFERENCES GRUPO (ID),\n" +
                    "   DATA,\n" +
                    "   VALOR,\n" +
                    "   PAGA_POR             REFERENCES USERS (ID),\n" +
                    "   RECEBIDO_POR         REFERENCES USERS (ID)\n" +
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

    public static boolean integraGrupo(String grupoNome, String email) {
        String sql = "SELECT 1 FROM GRUPO G " +
                "JOIN INTEGRA I ON G.ID = I.GROUP_ID " +
                "JOIN USERS U ON U.ID = I.USER_ID " +
                "WHERE G.NOME = ? AND U.EMAIL = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, grupoNome);
            pstmt.setString(2, email);
            ResultSet rs = pstmt.executeQuery();

            return rs.next(); // Se houver um resultado, o usuário pertence ao grupo
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static List<Grupos> listarGruposDB(String solicitadoPor) {
/*        List<Grupos> grupoList = new ArrayList<>();
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
        System.out.println(grupoList);
        return grupoList;*/
        List<Grupos> grupos = new ArrayList<>();

        String sql = "SELECT G.NOME " +
                "FROM GRUPO G " +
                "JOIN INTEGRA I ON G.ID = I.GROUP_ID " +
                "JOIN USERS U ON U.ID = I.USER_ID " +
                "WHERE U.EMAIL = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, solicitadoPor);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Grupos grupo = new Grupos();
                grupo.setNomeGrupo(rs.getString("NOME"));
                grupos.add(grupo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
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



    public static boolean setUserDB(String nome, int nTelefone, String email, String password) {
        if (verificarEmailExistente(email)) {
            System.out.println("Este e-mail já está registrado.");
            return false;
        }
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("INSERT INTO USERS (NOME, N_TELEFONE, EMAIL, PASSWORD) VALUES ('" +
                    nome + "','" + nTelefone + "','" + email + "','" + password + "')");
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao registar o utilizador");
            return false;
        }
    }



    public static boolean getUserDB(String email, String password) {
        User user = null;
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM USERS WHERE EMAIL='" +
                    email + "' AND PASSWORD= '" + password + "'");
            if (rs.next()) {
                return true;
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("O Utilizador não existe!");
            user.setEstado(false);
        }
        return false;
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

    public static boolean criaDespesa(String grupoNome, Despesa despesa, String email) {
        String sql = "INSERT INTO DESPESA (GROUP_ID, VALOR, DESCRICAO, DATA, PAGA_POR, REGISTADA_POR) " +
                "SELECT G.ID, ?, ?, ?, U1.ID, U2.ID " +
                "FROM GRUPO G " +
                "JOIN INTEGRA I1 ON G.ID = I1.GROUP_ID " +
                "JOIN USERS U1 ON U1.ID = I1.USER_ID " +
                "JOIN USERS U2 ON U2.EMAIL = ? " +
                "WHERE G.NOME = ? AND U1.EMAIL = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDouble(1, despesa.getValor());
            pstmt.setString(2, despesa.getDescricao());
            pstmt.setString(3, despesa.getData());
            pstmt.setString(4, email);
            pstmt.setString(5, grupoNome);
            pstmt.setString(6, email);

            int rowsAffected = pstmt.executeUpdate();

            return rowsAffected > 0; // Se inseriu, retorna true
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static List<Despesa> listarDespesas(String grupoNome) {
        List<Despesa> despesas = new ArrayList<>();

        String sql = "SELECT D.ID, D.DESCRICAO, D.VALOR, D.DATA, U.EMAIL AS QUEM_PAGOU " +
                "FROM DESPESA D " +
                "JOIN GRUPO G ON D.GROUP_ID = G.ID " +
                "JOIN INTEGRA I ON G.ID = I.GROUP_ID " +
                "JOIN USERS U ON D.PAGA_POR = U.ID " +
                "WHERE G.NOME = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, grupoNome);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Despesa despesa = new Despesa();
                    despesa.setIdDespesa(rs.getString("ID"));
                    despesa.setDescricao(rs.getString("DESCRICAO"));
                    despesa.setValor(rs.getDouble("VALOR"));
                    despesa.setData(rs.getString("DATA"));
                    despesa.setQuemPagou(rs.getString("QUEM_PAGOU"));

                    despesas.add(despesa);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return despesas;
    }

    public static boolean userTemDividas(String email, String grupoNome){
        String sql = "SELECT COUNT(*) AS TOTAL " +
                "FROM DIVIDE_DESPESA DD " +
                "JOIN USERS U ON U.ID = DD.USER_ID " +
                "JOIN GRUPO G ON G.ID = DD.GRUPO_ID " +
                "WHERE U.EMAIL = ? AND G.NOME = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, email);
            pstmt.setString(2, grupoNome);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("TOTAL") > 0;
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao verificar se o utilizador tem despesas divididas no grupo: " + e.getMessage());
        }

        return false;
    }

    public static Boolean eliminarDespesa(String email, String nomeGrupo, String ID) {
        String query = "DELETE FROM DESPESA\n" +
                "WHERE ID = (SELECT D.ID\n" +
                "    FROM DESPESA D\n" +
                "    INNER JOIN INTEGRA I ON D.GROUP_ID = I.GROUP_ID\n" +
                "    WHERE I.USER_ID = (SELECT ID FROM USERS WHERE EMAIL = '" + email + "')\n" +
                "    AND D.GROUP_ID = (SELECT ID FROM GRUPO WHERE NOME = '" + nomeGrupo + "')\n" +
                "    AND D.ID = " + ID + "\n" +
                "    LIMIT 1);";
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(query);
            stmt.close();

        } catch (SQLException e) {
            System.err.println("Erro ao eliminar pagamento: " + e.getMessage());
            return false;
        }
        return true;
    }
    public static boolean verificarEmailExistente(String email) {
        String sql = "SELECT 1 FROM USERS WHERE EMAIL = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, email);

            // Executa a consulta e verifica se há algum resultado
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next(); // Se rs.next() retornar true, significa que o e-mail já existe
            }
        } catch (SQLException e) {
            e.printStackTrace();  // Em caso de erro, imprime o stack trace
            return false;  // Retorna false se ocorrer erro na consulta
        }
    }



}
