package pt.isec.pd.servidores.controladores;

import pt.isec.pd.cliente.ligacao.Ligacao;
import pt.isec.pd.comum.enumeracoes.Estados;
import pt.isec.pd.comum.enumeracoes.Tipomensagemenum;
import pt.isec.pd.comum.modelos.Mensagem;
import pt.isec.pd.comum.modelos.mensagens.*;
import pt.isec.pd.db.Bd;

import java.io.Serializable;

public class ControladorDespesaServidor {

    public static Estados criaDespesa(CriaDespesa criaDespesa){
        Serializable despesa = null;
        try {
            despesa = Bd.criaDespesa(criaDespesa.getEmail(),criaDespesa.getGrupo() ,criaDespesa.getDespesa(), criaDespesa.getQuemPagou(), criaDespesa.getDescricao(), criaDespesa.getData() );


        } catch (Exception e) {
            System.err.println("Erro ao criar despesa para grupo: " + e.getMessage());
            //return Estados.ERRO_CRIAR_DESPESA;
        }
        return despesa == null ? Estados.ERRO_CRIAR_DESPESA : Estados.USER_CRIA_DESPESA_COM_SUCESSO.setDados(despesa);
    }

    public static Estados historicoDespesa(HistoricoDespesa historicoDespesa){
        Serializable historico = null;
        try {
            historico = Bd.historio(historicoDespesa.getGrupo());
        }catch (Exception e) {
            System.err.println("Erro ao exportar as despesas do grupo: " + e.getMessage());
        }
        return historico == null ? Estados.ERRO_OBTER_HISTORICO : Estados.USER_OBTEM_HISTORICO_DESPESA_COM_SUCESSO.setDados(historico);

    }

}
