package pt.isec.pd.cliente.UI;

import pt.isec.pd.cliente.controladores.ControladorPrincipal;
import pt.isec.pd.cliente.vistas.Vista;
import pt.isec.pd.comum.modelos.Despesa;
import pt.isec.pd.comum.modelos.Grupos;
import pt.isec.pd.db.Bd;

import java.util.List;
import java.util.Scanner;

import static pt.isec.pd.db.Bd.*;

public class ClienteConsolaUI {
    ControladorPrincipal cp;
    private Scanner scanner;
    String grupoSelecionado = null;

    public ClienteConsolaUI(ControladorPrincipal cp) {
        this.cp = cp;
        this.scanner = new Scanner(System.in);
    }

    public void iniciar() {
        String escolha;
        do {
            if (!cp.isUserAuthenticated()) { 
                Vista.menuPrincipal();
                escolha = scanner.nextLine();
                switch (escolha) {
                    case "1":
                        if (login()) {
                            System.out.println("Login efetuado com sucesso.");
                        } else {
                            System.out.println("Falha no login.");
                        }
                        break;
                    case "2":
                        registo();
                        break;
                    default:
                        System.out.println("Escolha Invalida.");
                }
            } else {
                Vista.menuPrincipalCliente();
                escolha = scanner.nextLine();
                switch (escolha) {
                    case "1":
                        menuGrupos();
                        break;
                    case "2":
                        despesasMenu();
                        break;
                    case "3":
                        alteraDados();
                        break;
                    case "4":
                        cp.logout();
                        grupoSelecionado = null;
                        System.out.println("Logout efetuado com sucesso.");
                        break;
                    default:
                        System.out.println("Opção Inválida!");
                }
            }
        } while (true);
    }


    public boolean login() {
        boolean login;
        Scanner scanner = new Scanner(System.in);
        System.out.print("Login E-mail: ");
        String email = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();
        login = cp.login(email, password);
        if (login) {
            System.out.println("Login efectuado com sucesso");
            return true;
        } else {
            System.out.println("Falha no login");
            return false;
        }
    }

    public void registo() {
        int registo;
        String email, nome, password, nTelefoneString;
        System.out.print("Login E-mail: ");
        email = scanner.nextLine();
        System.out.print("NOME: ");
        nome = scanner.nextLine();
        System.out.print("Password: ");
        password = scanner.nextLine();
        System.out.print("Número de Telefone: ");
        nTelefoneString = scanner.nextLine();
        registo = cp.registo(nome, email, password, nTelefoneString);
        if (registo == 0)
            System.out.println("Tem de ter o seguinte formato: exemplo@exemplo.com");
        else if (registo == 1)
            System.out.println("O número de telefone deve conter 9 digitos.");
        else if (registo == 2)
            System.out.println("O número do telefone deve conter apenas números.");
        else
            System.out.println("Registo efectuado com sucesso.");
    }

    public void alteraDados() {
        String numeroTelefone, pass;
        System.out.println("Deixar vazio o que não é para alterar.");
        System.out.print("Número de Telefone: ");
        numeroTelefone = scanner.nextLine();
        System.out.print("\nPassword:");
        pass = scanner.nextLine();
        cp.editaDados(numeroTelefone, pass);
    }

    public void menuGrupos() {
        String escolha;

        do {
            Vista.menuGrupo();
            escolha = scanner.nextLine();
            switch (escolha) {
                case "1":
                    criarGrupo();
                    break;
                case "2":
                    selecionarGrupo();
                    break;
                case "3":
                    listarGrupos();
                    break;
                default:
                    if (!escolha.equalsIgnoreCase("8"))
                        System.out.println("Opção Inválida!");
            }
        } while (!escolha.equalsIgnoreCase("8"));

    }

    public void criarGrupo() {
        String nomeGrupo;
        System.out.print("Nome do grupo: ");
        nomeGrupo = scanner.nextLine();
        cp.criarGrupo(nomeGrupo);

    }

    public void selecionarGrupo() {
        String nomeGrupo;
        System.out.print("Nome do Grupo que pretende selecionar: ");
        nomeGrupo = scanner.nextLine();
        grupoSelecionado = cp.selecionarGrupo(nomeGrupo);
        System.out.println(grupoSelecionado);
    }

    public void listarGrupos() {
        Grupos grupos = cp.listarGrupos();
        Vista.tabelaGrupos(grupos);
    }

    public void despesasMenu() {
        String escolha;

        do {
            Vista.menuDespesa();
            escolha = scanner.nextLine();
            switch (escolha) {
                case "1":
                    criarDespesa();
                    break;
                case "2":
                    listarDespesas();
                    break;
                default:
                    if (!escolha.equalsIgnoreCase("7"))
                        System.out.println("Opção Inválida!");
            }
        } while (!escolha.equalsIgnoreCase("7"));
    }

    public void criarDespesa() {
        double despesa;
        String quemPagou;
        String descricao;
        String data;
        System.out.print("\nDespesa: ");
        despesa = Double.parseDouble(scanner.nextLine());
        System.out.print("\nPago por (email): ");
        quemPagou = scanner.nextLine();
        System.out.print("\nDescricao: ");
        descricao = scanner.nextLine();
        System.out.println("\nPago em (DD-MM-YYYY): ");
        data = scanner.nextLine();
        cp.insereDespesa(grupoSelecionado, despesa, quemPagou, descricao, data);

    }

    public void listarDespesas() {
        Despesa despesa = cp.mostrarDespesas(grupoSelecionado);
        Vista.tabelaDespesas(despesa);
    }

}

