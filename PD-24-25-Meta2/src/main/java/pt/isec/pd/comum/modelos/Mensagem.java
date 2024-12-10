package pt.isec.pd.comum.modelos;


//esta classe está responsável para fazer a serialização para o objecto!!!!!


import pt.isec.pd.comum.enumeracoes.Tipomensagemenum;

import java.io.Serial;
import java.io.Serializable;

public class Mensagem implements Serializable {
    private static final long serialVersionUID = 1L;

    private Tipomensagemenum tipomensagemenum;  //tipo de mensagem que vai ser passada para o servidor  ou para cliente
    private Serializable conteudo;  //conteudo do objecto, em Array de bytes, a ser passado

    public Mensagem(Tipomensagemenum tipo, Serializable conteudo){
        this.tipomensagemenum = tipo;
        this.conteudo = conteudo;
    }


    public Serializable getConteudo() {
        return conteudo;
    }

    public Tipomensagemenum getTipoMensagem(){
        return tipomensagemenum;
    }


    @Override
    public String toString() {
        return "Mensagem{" +
                "tipoMensagem=" + tipomensagemenum +
                ", conteudo=" + conteudo +
                '}';
    }
}

