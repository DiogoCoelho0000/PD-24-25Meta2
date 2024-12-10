package pt.isec.pd.servidores.controladores;

import pt.isec.pd.comum.enumeracoes.Estados;
import pt.isec.pd.comum.modelos.RespostaListagemGrupos;
import pt.isec.pd.comum.modelos.RespostaServidorMensagem;
import pt.isec.pd.comum.modelos.mensagens.*;
import pt.isec.pd.comum.modelos.Mensagem;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ControladorMensagemServidor {

    public static RespostaServidorMensagem respostaServidor(Mensagem mensagem){

        RespostaServidorMensagem resposta = null;
        switch (mensagem.getTipoMensagem()){
            case USER_REGISTO:
            {
                Estados estado = ControladorAutenticacao.registo((Registo) mensagem.getConteudo());
                resposta = new RespostaServidorMensagem(estado, estado.getDados());
                break;
            }
            case LOGIN:
            {
                //User user;
                Estados estado = ControladorAutenticacao.login((Login) mensagem.getConteudo());
                resposta = new RespostaServidorMensagem(estado,estado.getDados());
                break;
            }
            case USER_EDITA_INFORMACAO:
            {
                Estados estados = ControladorAutenticacao.edita((EditaConta) mensagem.getConteudo());
                resposta = new RespostaServidorMensagem(estados,estados.getDados());
                break;
            }
            case LOGOUT: //PARA FAZER
            {
                break;
            }
            case USER_CRIA_GRUPO:
            {
                System.out.println("OLA");
                System.out.println(mensagem);
                Estados estado = ControladorGrupoServidor.grupoRegisto((CriaGrupo) mensagem.getConteudo());
                System.out.println("ESTADINHO " + estado);
                resposta = new RespostaServidorMensagem(estado, estado.getDados());
                System.out.println("RESPOSTA..... -> "+ resposta);
                break;
                //Estados estado =
            }
            case USER_INSERIDO_NO_GRUPO:
            {
                System.out.println(mensagem);
                Estados estado = ControladorGrupoServidor.insereGrupo((InsereGrupo) mensagem.getConteudo());
                resposta = new RespostaServidorMensagem(estado,estado.getDados());
                break;
            }

            case USER_LISTA_GRUPOS:
            {
                System.out.println("A Listar grupos...");
                Estados estado = ControladorGrupoServidor.listarGrupos((ListarGrupo) mensagem.getConteudo());
                resposta = new RespostaServidorMensagem(estado, estado.getDados());
                //System.out.println("USER_LISTA_GRUPOS -> " +  estado);
                //System.out.println("RESPOSTA -> " +resposta);
                break;
            }
            case USER_SELECIONA_GRUPO:
            {
                System.out.println("A selecionar grupo...");
                Estados estado = ControladorGrupoServidor.selecionaGrupo((SelecionarGrupo) mensagem.getConteudo());
                resposta = new RespostaServidorMensagem(estado, estado.getDados());
                break;
            }

            case USER_INSERE_DESPESA:
            {
                Estados estado = ControladorDespesaServidor.criaDespesa((CriaDespesa) mensagem.getConteudo());
                resposta = new RespostaServidorMensagem(estado, estado.getDados());
                break;
            }
            case USER_HISTORICO_DESPESAS:
            {
                Estados estado = ControladorDespesaServidor.historicoDespesa((HistoricoDespesa) mensagem.getConteudo());
                resposta = new RespostaServidorMensagem(estado,estado.getDados());
                break;
            }

        }
        return resposta;
    }
}
