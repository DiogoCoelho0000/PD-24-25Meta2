package pt.isec.pd.rmi.cliente;

import pt.isec.pd.models.Grupos;
import pt.isec.pd.models.User;
import pt.isec.pd.rmi.server.RmiInterface;

import java.rmi.Naming;
import java.util.List;
import java.util.Scanner;

public class RMIClient {

    public static void main(String[] args) {
        try {

            String serviceUrl = "rmi://localhost:1099/RmiService";
            RmiInterface rmiService = (RmiInterface) Naming.lookup(serviceUrl);
            System.out.println("Conectado ao servidor RMI.");


            Scanner scanner = new Scanner(System.in);

            while (true) {

                System.out.println("\n=== Menu RMI Cliente ===");
                System.out.println("1. Listar utilizadores");
                System.out.println("2. Listar grupos");
                System.out.println("3. Sair");
                System.out.print("Escolha uma opção: ");

                int opcao = scanner.nextInt();
                scanner.nextLine();

                switch (opcao) {
                    case 1:
                        listarUtilizadores(rmiService);
                        break;
                    case 2:
                        listarGrupos(rmiService);
                        break;
                    case 3:
                        System.out.println("Encerrando o cliente RMI...");
                        return;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                }
            }
        } catch (Exception e) {
            System.err.println("Erro ao conectar-se ao servidor RMI: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void listarUtilizadores(RmiInterface rmiService) {
        try {
            System.out.println("\n=== Lista de Utilizadores ===");
            List<User> users = rmiService.obterListaUsers();

            if (users.isEmpty()) {
                System.out.println("Nenhum utilizador encontrado.");
            } else {
                users.forEach(user -> System.out.println("- " + user.getNome() + " (" + user.getEmail() + ")"));
            }
        } catch (Exception e) {
            System.err.println("Erro ao listar utilizadores: " + e.getMessage());
        }
    }

    private static void listarGrupos(RmiInterface rmiService) {
        try {
            System.out.println("\n=== Lista de Todos os Grupos ===");
            List<Grupos> grupos = rmiService.obterListaGrupos();

            if (grupos.isEmpty()) {
                System.out.println("Nenhum grupo disponível.");
            } else {
                grupos.forEach(grupo -> System.out.println("- " + grupo.getNomeGrupo()));
            }
        } catch (Exception e) {
            System.err.println("Erro ao listar grupos: " + e.getMessage());
        }
    }
}
