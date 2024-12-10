package pt.isec.pd.cliente.controladores;

import pt.isec.pd.cliente.ligacao.Ligacao;
import pt.isec.pd.comum.enumeracoes.Tipomensagemenum;
import pt.isec.pd.comum.modelos.Mensagem;
import pt.isec.pd.comum.modelos.mensagens.*;

public class ControladorDespesaCliente {
    public static void inserirDespesa(Ligacao ligacao, String email, String grupo, double despesa, String quemPagou, String descricao, String data) {
        CriaDespesa criaDespesa = new CriaDespesa(despesa, grupo, email, quemPagou, descricao, data);
        Mensagem mensagem = new Mensagem(Tipomensagemenum.USER_INSERE_DESPESA, criaDespesa);
        ligacao.enviaMensagem(mensagem);
    }

    // Listar Despesas
    public static void historicoDespesa(Ligacao ligacao, String grupo) {
        HistoricoDespesa historicoDespesa = new HistoricoDespesa(grupo);
        Mensagem mensagem = new Mensagem(Tipomensagemenum.USER_HISTORICO_DESPESAS, historicoDespesa);
        ligacao.enviaMensagem(mensagem);
    }

    /*
    public static void verTotalReceber(Ligacao ligacao, String email, String grupoNome){
        VerTotalReceber verTotalReceber = new VerTotalReceber(grupoNome, email);
        Mensagem mensagem = new Mensagem(Tipomensagemenum.USER_VISUALIZA_TOTAL_RECEBER, verTotalReceber);
        ligacao.enviaMensagem(mensagem);
    }
    public static void verReceberPorMembro(Ligacao ligacao, String email, String grupoNome){
        VerReceberPorMembro verReceberPorMembro = new VerReceberPorMembro(grupoNome, email);
        Mensagem mensagem = new Mensagem(Tipomensagemenum.USER_VISUALIZA_RECEBER_POR_MEMBRO, verReceberPorMembro);
        ligacao.enviaMensagem(mensagem);
    }
     */
}

