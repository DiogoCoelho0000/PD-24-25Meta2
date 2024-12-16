package pt.isec.pd.httpclient;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Base64;
import java.util.Scanner;

import static pt.isec.pd.db.Bd.listarDespesas;

public class RestClient {
    private static String jwtToken = null;
    private static final String BASE_URL = "http://localhost:8080";
    private static String userAutenticado;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            if (jwtToken == null) {
                System.out.println("1. Registar");
                System.out.println("2. Login");
                System.out.println("3. Sair");
                System.out.print("Escolha uma opção: ");
                int choice = scanner.nextInt();
                scanner.nextLine();

                if (choice == 1) {
                    registar(scanner);
                } else if (choice == 2) {
                    login(scanner);
                } else if (choice == 3) {
                    System.out.println("Desligar o RestClient...");
                    break;
                } else {
                    System.out.println("Opção inválida.");
                }
            } else {
                System.out.println("1. Listar grupos");
                System.out.println("2. Inserir despesa");
                System.out.println("3. Eliminar despesa");
                System.out.println("4. Listar despesas");
                System.out.println("5. Sair");
                System.out.print("Escolha uma opção: ");
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1 -> listarGrupos();
                    case 2 -> inserirDespesa(scanner);
                    case 3 -> eliminarDespesa(scanner);
                    case 4 -> listarDespesas(scanner);
                    case 5 -> {
                        jwtToken = null;
                        System.out.println("Logout efetuado.");
                    }
                    default -> System.out.println("Opção inválida.");
                }
            }
        }
    }

    private static void registar(Scanner scanner) {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();


        String body = String.format("{\"username\":\"%s\", \"email\":\"%s\", \"nTelefone\":\"%s\", \"password\":\"%s\"}",
                nome, email, telefone, senha);
        System.out.println("Corpo da requisição: " + body);

        String response = sendRequest("/register", "POST", body, null);
        System.out.println("Resposta do servidor: " + response);
    }

    private static void login(Scanner scanner) {
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        String basicAuth = Base64.getEncoder().encodeToString((email + ":" + senha).getBytes());

        String response = sendRequest("/login", "GET", null, "Basic " + basicAuth);

        if (response != null && !response.isEmpty()) {
            jwtToken = response;
            userAutenticado = email;
            System.out.println("Login efetuado com sucesso. Token recebido.");
        } else {
            System.out.println("Erro ao efetuar login.");
        }
    }

    private static void listarGrupos() {
        String response = sendRequest("/grupos/meus-grupos", "GET", null, jwtToken);
        System.out.println("Grupos:");
        System.out.println(response);
    }

    private static void listarDespesas(Scanner scanner) {
        System.out.print("Nome do grupo: ");
        String grupoNome = scanner.nextLine();
        String response = sendRequest("/despesa/minhas-despesas/" + grupoNome, "GET", null, jwtToken);
        System.out.println("Despesas:");
        System.out.println(response);
    }

    private static void inserirDespesa(Scanner scanner) {
        System.out.print("Nome do grupo: ");
        String grupoNome = scanner.nextLine();
        System.out.print("Descrição: ");
        String descricao = scanner.nextLine();
        System.out.print("Valor: ");
        String valor = scanner.nextLine();
        scanner.nextLine();
        System.out.print("Data (YYYY-MM-DD): ");
        String data = scanner.nextLine();
        System.out.print("Quem pagou: ");
        String quemPagou = scanner.nextLine();

        String body = String.format("{\"email\":\"%s\", \"valor\":%s, \"quemPagou\":\"%s\", \"descricao\":\"%s\", \"data\":\"%s\"}",
                userAutenticado, valor, quemPagou, descricao, data);

        String response = sendRequest("/despesa/inserir/" + grupoNome, "POST", body, jwtToken);
        System.out.println(response);
    }

    private static void eliminarDespesa(Scanner scanner) {
        System.out.print("Nome do grupo: ");
        String grupoNome = scanner.nextLine();
        System.out.print("ID da despesa: ");
        String idDespesa = scanner.nextLine();

        String response = sendRequest("/despesa/eliminar/" + grupoNome + "/" + idDespesa, "GET", null, jwtToken);
        System.out.println(response);
    }

    private static String sendRequest(String endpoint, String method, String body, String authHeader) {
        try {
            URL url = new URI(BASE_URL + endpoint).toURL();

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(method);
            connection.setRequestProperty("Content-Type", "application/json");

            if (authHeader != null) {
                if (endpoint.equals("/login")) {
                    connection.setRequestProperty("Authorization", authHeader);
                } else {
                    connection.setRequestProperty("Authorization", "Bearer " + authHeader);
                }
            }

            if (body != null) {
                connection.setDoOutput(true);
                try (OutputStream os = connection.getOutputStream()) {
                    byte[] input = body.getBytes("utf-8");
                    os.write(input, 0, input.length);
                }
            }

            int status = connection.getResponseCode();
            if (status == 401) {
                jwtToken = null;
                System.out.println("Token inválido. Faça login novamente.");
                return null;
            }

            InputStream responseStream = status < 400 ? connection.getInputStream() : connection.getErrorStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(responseStream, "utf-8"));
            StringBuilder response = new StringBuilder();
            String responseLine;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            connection.disconnect();
            return response.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
