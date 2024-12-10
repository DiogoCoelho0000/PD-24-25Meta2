package pt.isec.pd.cliente.vistas;

import pt.isec.pd.cliente.controladores.ControladorPrincipal;
import pt.isec.pd.comum.modelos.Despesa;
import pt.isec.pd.comum.modelos.Grupos;


import java.util.List;
import java.util.Scanner;

public class Vista {
    public static void menuPrincipal() {
        Scanner entrada = new Scanner(System.in);

        System.out.println("+------------------+");
        System.out.println("|  MENU - Login    |");
        System.out.println("+------------------+");
        System.out.println("|  1 - login       |");
        System.out.println("|  2 - registo     |");
        System.out.println("+------------------+");
        System.out.print("#> ");


    }
    public static void menuPrincipalCliente() {
        System.out.println("+------------------------------------------+");
        System.out.println("|            MENU - Principal              |");
        System.out.println("+------------------------------------------+");
        System.out.println("|  1  - Grupo Menu.                        |");
        System.out.println("|  2  - Despesas Menu.                     |");
        System.out.println("|  3  - Alterar Password ou nº Telefone.   |");
        System.out.println("|  4  - Logout.                            |");
        System.out.println("+------------------------------------------+");
        System.out.print("#>");
    }

    public static void menuGrupo() {
        System.out.println("+------------------------------------------+");
        System.out.println("|               MENU - Grupo               |");
        System.out.println("+------------------------------------------+");
        System.out.println("|  1  - Cria Grupo.                        |");
        System.out.println("|  2  - Selecionar um grupo.               |");
        System.out.println("|  3  - Listar Grupos.                     |");
        System.out.println("|  4  - Back                               |");
        System.out.println("+------------------------------------------+");
        System.out.print("#>");
    }

    public static void menuDespesa() {
        System.out.println("+------------------------------------------+");
        System.out.println("|             MENU - Despesa               |");
        System.out.println("+------------------------------------------+");
        System.out.println("|  1  - Inserir uma Despesa.               |");
        System.out.println("|  2 - Ver historico de Despesas.         |");
        System.out.println("|  3  - Back                               |");
        System.out.println("+------------------------------------------+");
        System.out.print("#>");
    }
    public static void tabelaGrupos(Grupos grupo) {
        int tamanhoNome = 0;
        int tamanhoTotal = 0;
        int espacosEsquerda;
        int espacosDireita;
        int espacos = 0;
        //calcular o maior dos nomes possíveis para adaptar a tabela
        for (Grupos grupos : grupo.getGruposList()) {
            tamanhoNome = Math.max(tamanhoNome, grupos.getNomeGrupo().length());
            ;
        }
        tamanhoTotal = tamanhoNome + 2;
        //linha 1
        System.out.print("+");
        for (int i = 0; i < tamanhoTotal; i++)
            System.out.print("-");
        System.out.println("+");
        //mostra título da tabela
        System.out.print("|");
        espacosEsquerda = (tamanhoTotal - "Grupos".length()) / 2;
        for (int i = 0; i < espacosEsquerda; i++)
            System.out.print(" ");
        System.out.print("Grupos");
        espacosDireita = tamanhoTotal - 6 - espacosEsquerda;
        for (int i = 0; i < espacosDireita; i++)
            System.out.print(" ");
        System.out.println("|");
        System.out.print("+");
        for (int i = 0; i < tamanhoTotal; i++)
            System.out.print("-");
        System.out.println("+");
        //linha 2
        System.out.print("+");
        for (int i = 0; i < tamanhoTotal; i++)
            System.out.print("-");
        System.out.println("+");

        for (Grupos grupos : grupo.getGruposList()) {
            System.out.print("| " + grupos.getNomeGrupo());
            espacos = tamanhoTotal - 1 - grupos.getNomeGrupo().length();
            for (int i = 0; i < espacos; i++)
                System.out.print(" ");
            System.out.print("|");
            System.out.print("\n+");
            for (int i = 0; i < tamanhoTotal; i++)
                System.out.print("-");
            System.out.println("+");

        }
        System.out.print("\n");

    }


    public static void tabelaDespesas(Despesa despesas) {
        int tamanhoDescricao = 0;
        int tamanhoRegistadoPor = 0;
        int tamanhoPagoPor = 0;
        int tamanhoData = 0;
        int tamanhoValor = 0;
        int tamanhoID = 0;
        int tamanhoTotal = 0;
        int espacosEsquerda;
        int espacosDireita;
        //calcular o maior dos nomes possíveis para adaptar a tabela
        for (Despesa despesa : despesas.getDespesaList()) {
            tamanhoID = Math.max(tamanhoID, despesa.getIdDespesa().length());
            tamanhoDescricao = Math.max(tamanhoDescricao, despesa.getDescricao().length());
            tamanhoRegistadoPor = Math.max(tamanhoRegistadoPor, despesa.getEmail().length());
            tamanhoPagoPor = Math.max(tamanhoPagoPor, despesa.getQuemPagou().length());
            tamanhoData = Math.max(tamanhoData, despesa.getData().length());
            tamanhoValor = Math.max(tamanhoValor, String.valueOf(despesa.getDespesa()).length());

        }
        tamanhoTotal = tamanhoDescricao + tamanhoRegistadoPor + tamanhoPagoPor +
                tamanhoData + tamanhoValor + tamanhoID + 14;
        //linha 1
        System.out.print("+");
        for (int i = 0; i < tamanhoTotal + 4; i++)
            System.out.print("-");
        System.out.println("+");
        //mostra título da tabela
        System.out.print("|");
        espacosEsquerda = (tamanhoTotal - "Histórico De Despesas".length()) / 2;
        for (int i = 0; i < espacosEsquerda; i++)
            System.out.print(" ");
        System.out.print("Histórico De Despesas");
        espacosDireita = tamanhoTotal - 21 - espacosEsquerda;
        for (int i = 0; i < espacosDireita + 4; i++)
            System.out.print(" ");
        System.out.println("|");
        System.out.print("+");
        for (int i = 0; i < tamanhoTotal + 4; i++)
            System.out.print("-");
        System.out.println("+");
        System.out.print("|");
        for (int i = 0; i < (tamanhoID + 2 - "ID".length()) / 2; i++)
            System.out.print(" ");
        System.out.print("ID");
        for (int i = 0; i < (tamanhoID + 2 - "ID".length()) / 2; i++)
            System.out.print(" ");
        System.out.print("|");
        for (int i = 0; i < (tamanhoData + 2 - "DATA".length()) / 2; i++)
            System.out.print(" ");
        System.out.print("DATA");
        for (int i = 0; i < (tamanhoData + 2 - "DATA".length()) / 2; i++)
            System.out.print(" ");
        System.out.print("|");
        for (int i = 0; i < (tamanhoValor + 3 - "Valor".length()) / 2; i++)
            System.out.print(" ");
        System.out.print("Valor");
        for (int i = 0; i < (tamanhoValor + 3 - "Valor".length()) / 2; i++)
            System.out.print(" ");
        System.out.print("|");
        for (int i = 0; i < (tamanhoDescricao + 2 - "Descrição".length()) / 2; i++)
            System.out.print(" ");
        System.out.print("Descrição");
        for (int i = 0; i < (tamanhoDescricao + 2 - "Descrição".length()) / 2; i++)
            System.out.print(" ");
        System.out.print("|");
        for (int i = 0; i < (tamanhoRegistadoPor + 2 - "Registo".length()) / 2; i++)
            System.out.print(" ");
        System.out.print("Registo");
        for (int i = 0; i < (tamanhoRegistadoPor + 2 - "Registo".length()) / 2; i++)
            System.out.print(" ");
        System.out.print("|");
        for (int i = 0; i < (tamanhoPagoPor + 2 - "Pago".length()) / 2; i++)
            System.out.print(" ");
        System.out.print("Pago");
        for (int i = 0; i < (tamanhoPagoPor + 2 - "Pago".length()) / 2; i++)
            System.out.print(" ");
        System.out.println("|");
        //linha 2
        System.out.print("+");
        for (int i = 0; i < tamanhoTotal + 4; i++)
            System.out.print("-");
        System.out.println("+");

        for (Despesa despesa : despesas.getDespesaList()) {

            System.out.print("| " + despesa.getIdDespesa());
            for (int i = 0; i < tamanhoID - String.valueOf(despesa.getIdDespesa()).length() + 1; i++)
                System.out.print(" ");
            System.out.print("| ");


            System.out.print(despesa.getData() + " | ");

            System.out.print(despesa.getDespesa() + "€");
            for (int i = 0; i < tamanhoValor - String.valueOf(despesa.getDespesa()).length() + 1; i++)
                System.out.print(" ");
            System.out.print("| ");
            System.out.print(despesa.getDescricao());

            for (int i = 0; i < tamanhoDescricao - despesa.getDescricao().length() + 1; i++)
                System.out.print(" ");

            System.out.print("| ");

            System.out.print(despesa.getEmail());
            for (int i = 0; i < tamanhoRegistadoPor - despesa.getEmail().length() + 1; i++)
                System.out.print(" ");
            System.out.print("| ");

            System.out.print(despesa.getQuemPagou());
            for (int i = 0; i < tamanhoPagoPor - despesa.getQuemPagou().length() + 1; i++)
                System.out.print(" ");
            System.out.print("|\n");


            System.out.print("+");
            for (int i = 0; i < tamanhoTotal + 4; i++)
                System.out.print("-");
            System.out.println("+");

        }
    }
}