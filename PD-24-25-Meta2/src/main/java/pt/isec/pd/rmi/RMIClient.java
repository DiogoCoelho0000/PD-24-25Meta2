package pt.isec.pd.rmi;

import pt.isec.pd.comum.modelos.mensagens.*;
import pt.isec.pd.models.Grupos;
import pt.isec.pd.models.User;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.util.List;
import java.util.Scanner;

public class RMIClient {

    public static void main(String[] args) {
        try {
            // Conectar-se ao RMI registry no servidor
            String serviceUrl = "rmi://localhost:1099/RmiService";
            RmiInterface rmiService = (RmiInterface) Naming.lookup(serviceUrl);
            System.out.println("Conectado ao servidor RMI.");

            // Criar scanner para entrada do usuário
            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("\n=== Menu RMI Cliente ===");
                System.out.println("1. Registrar novo utilizador");
                System.out.println("2. Autenticar utilizador");
                System.out.println("3. Inserir despesa");
                System.out.println("4. Eliminar despesa");
                System.out.println("5. Listar utilizadores");
                System.out.println("6. Listar grupos");
                System.out.println("0. Sair");
                System.out.print("Escolha uma opção: ");

                int opcao = scanner.nextInt();
                scanner.nextLine(); // Consumir a quebra de linha

                switch (opcao) {
                    case 1:
                        registrarUtilizador(rmiService, scanner);
                        break;
                    case 2:
                        autenticarUtilizador(rmiService, scanner);
                        break;
                    case 3:
                        inserirDespesa(rmiService, scanner);
                        break;
                    case 4:
                        eliminarDespesa(rmiService, scanner);
                        break;
                    case 5:
                        listarUtilizadores(rmiService);
                    case 6:
                        listarGrupos(rmiService, scanner);
                        break;
                    case 0:
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

    private static void registrarUtilizador(RmiInterface rmiService, Scanner scanner) {
        try {
            System.out.println("\n=== Registro de Utilizador ===");
            System.out.print("Nome: ");
            String nome = scanner.nextLine();
            System.out.print("Email: ");
            String email = scanner.nextLine();
            System.out.print("Telefone: ");
            int telefone = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha
            System.out.print("Senha: ");
            String senha = scanner.nextLine();

            Registo registo = new Registo(email, senha, telefone, nome);
            boolean sucesso = rmiService.registarUtilizador(registo);

            System.out.println(sucesso ? "Utilizador registrado com sucesso!" : "Erro: Utilizador já registrado.");
        } catch (Exception e) {
            System.err.println("Erro ao registrar utilizador: " + e.getMessage());
        }
    }

    private static void autenticarUtilizador(RmiInterface rmiService, Scanner scanner) {
        try {
            System.out.println("\n=== Autenticação de Utilizador ===");
            System.out.print("Email: ");
            String email = scanner.nextLine();
            System.out.print("Senha: ");
            String senha = scanner.nextLine();

            Login login = new Login(email, senha);
            boolean autenticado = rmiService.autenticarUtilizador(login);

            System.out.println(autenticado ? "Autenticação bem-sucedida!" : "Erro: Credenciais inválidas.");
        } catch (Exception e) {
            System.err.println("Erro ao autenticar utilizador: " + e.getMessage());
        }
    }

    private static void inserirDespesa(RmiInterface rmiService, Scanner scanner) {
        try {
            System.out.println("\n=== Inserir Despesa ===");
            System.out.print("Nome do grupo: ");
            String grupoNome = scanner.nextLine();
            System.out.print("Valor da despesa: ");
            double valor = scanner.nextDouble();
            scanner.nextLine(); // Consumir a quebra de linha

            // Criando objeto CriaDespesa com os argumentos na ordem correta
            CriaDespesa criaDespesa = new CriaDespesa(valor, grupoNome);

            // Chamando o método do serviço RMI
            boolean sucesso = rmiService.inserirDespesa(criaDespesa);

            // Resultado
            System.out.println(sucesso ? "Despesa inserida com sucesso!" : "Erro ao inserir despesa.");
        } catch (Exception e) {
            System.err.println("Erro ao inserir despesa: " + e.getMessage());
        }
    }

    private static void eliminarDespesa(RmiInterface rmiService, Scanner scanner) {
        try {
            System.out.println("\n=== Eliminar Despesa ===");
            System.out.print("Nome do grupo: ");
            String grupoNome = scanner.nextLine();
            System.out.print("ID da despesa: ");
            String idDespesa = scanner.nextLine();

            EliminaDespesa eliminaDespesa = new EliminaDespesa(idDespesa, grupoNome);
            boolean sucesso = rmiService.eliminarDespesa(eliminaDespesa);

            System.out.println(sucesso ? "Despesa eliminada com sucesso!" : "Erro ao eliminar despesa.");
        } catch (Exception e) {
            System.err.println("Erro ao eliminar despesa: " + e.getMessage());
        }
    }

    private static void listarUtilizadores(RmiInterface rmiService) {
        try {
            System.out.println("\n=== Lista de Utilizadores ===");
            List<User> users = rmiService.obterListaUsuarios();

            if (users.isEmpty()) {
                System.out.println("Nenhum utilizador encontrado.");
            } else {
                users.forEach(user -> System.out.println("- " + user.getNome() + " (" + user.getEmail() + ")"));
            }
        } catch (Exception e) {
            System.err.println("Erro ao listar utilizadores: " + e.getMessage());
        }
    }

    private static void listarGrupos(RmiInterface rmiService, Scanner scanner) {
        try {
            System.out.println("\n=== Lista de Grupos ===");
            System.out.print("Digite seu email para listar os grupos: ");
            String email = scanner.nextLine();

            List<Grupos> grupos = rmiService.obterListaGrupos(email);

            if (grupos.isEmpty()) {
                System.out.println("Nenhum grupo associado a este email.");
            } else {
                grupos.forEach(grupo -> System.out.println("- " + grupo.getNomeGrupo()));
            }
        } catch (Exception e) {
            System.err.println("Erro ao listar grupos: " + e.getMessage());
        }
    }
}
