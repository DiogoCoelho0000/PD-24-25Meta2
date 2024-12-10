package pt.isec.pd.cliente.controladores;

import pt.isec.pd.cliente.ligacao.Ligacao;
import pt.isec.pd.comum.enumeracoes.Tipomensagemenum;
import pt.isec.pd.comum.modelos.Mensagem;
import pt.isec.pd.comum.modelos.mensagens.*;

public class ControladorGrupoCliente {
    public static void criaGrupo(Ligacao ligacao, String nomeGrupo , String nomeUser){
        CriaGrupo criaGrupo = new CriaGrupo(nomeGrupo, nomeUser);
        Mensagem mensagem = new Mensagem(Tipomensagemenum.USER_CRIA_GRUPO, criaGrupo);
        ligacao.enviaMensagem(mensagem);
        System.out.println(mensagem);
    }
    public static void insereGrupo(Ligacao ligacao, String nomeGrupo, String Email){
        InsereGrupo insereGrupo = new InsereGrupo(Email, nomeGrupo);
        Mensagem mensagem = new Mensagem(Tipomensagemenum.USER_INSERIDO_NO_GRUPO,insereGrupo);
        ligacao.enviaMensagem(mensagem);
        System.out.println(mensagem);
    }
    public static void listarGrupos(Ligacao ligacao, String nomeUser) {

        ListarGrupo listarGrupo = new ListarGrupo(nomeUser);
        Mensagem mensagem = new Mensagem(Tipomensagemenum.USER_LISTA_GRUPOS, listarGrupo);
        ligacao.enviaMensagem(mensagem);
        System.out.println("ControladorGrupoCliente -> "+mensagem);
    }
    public static void selecionaGrupo(Ligacao ligacao, String email, String grupo){
        SelecionarGrupo selecionarGrupo = new SelecionarGrupo(email,grupo);
        Mensagem mensagem = new Mensagem(Tipomensagemenum.USER_SELECIONA_GRUPO, selecionarGrupo);
        ligacao.enviaMensagem(mensagem);
    }

}
