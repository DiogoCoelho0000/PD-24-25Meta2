package pt.isec.pd.comum.modelos;

import pt.isec.pd.comum.enumeracoes.Estados;

import java.io.Serializable;

public class RespostaServidorMensagem implements Serializable{
    private static final long serialVersionUID = 1L;



    private Serializable conteudo;  //conteudo do objecto, em Array de bytes, a ser passado
    private Estados estado;

    public RespostaServidorMensagem(Estados estado,Serializable conteudo){
        this.conteudo = conteudo;
        this.estado = estado;

    }

    public Serializable getConteudo() {
        return conteudo;
    }
    public Estados getEstado(){
        return estado;
    }

    @Override
    public String toString() {
        return "Mensagem{" +
                "conteudo=" + conteudo +
                '}';
    }
}
