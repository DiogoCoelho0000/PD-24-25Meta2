package pt.isec.pd.servidorBackup;

import java.sql.*;

public class BackupBd {
    private static Connection connection = null;

    public static void ligacao(String BDNome){
        try {
            String caminho = "jdbc:sqlite:./" + BDNome;

            connection = DriverManager.getConnection(caminho);
            System.out.println("Ligação à Base de dados feita com sucesso!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void updateVersao(){
        int NUMERO_VERSAO = 0;
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT NUMERO_VERSAO FROM VERSAO" +
                    " ORDER BY NUMERO_VERSAO DESC LIMIT 1");
            if(rs.next()){
                NUMERO_VERSAO = rs.getInt("NUMERO_VERSAO");

                //System.out.println( "\nTABELA: \n" + NUMERO_VERSAO + "\n");
                NUMERO_VERSAO++; //incrementa a versão
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try{

            Statement stmt = connection.createStatement();

            stmt.executeUpdate("INSERT INTO VERSAO (NUMERO_VERSAO)" +
                    " VALUES ('" +
                    NUMERO_VERSAO +
                    "')");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static int getVersao(){
        int versao = 0;
        String query = "SELECT MAX(NUMERO_VERSAO) as NUMERO_VERSAO FROM VERSAO";

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            versao = rs.getInt("NUMERO_VERSAO");

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Erro ao obter a versão");
        }
        return versao;
    }
}
