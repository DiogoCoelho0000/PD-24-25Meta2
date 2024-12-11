package pt.isec.pd.comum.modelos.mensagens;

import pt.isec.pd.comum.enumeracoes.Tipomensagemenum;
import pt.isec.pd.comum.modelos.Mensagem;

import java.io.Serializable;

public class SairGrupo extends Mensagem implements Serializable {
    private String nomeGrupo;
    private String emailUser;

    public SairGrupo(String nomeGrupo, String emailUser) {

        super(Tipomensagemenum.USER_SAI_GRUPO, null);
        this.nomeGrupo = nomeGrupo;
        this.emailUser = emailUser;
    }

    public String getNomeGrupo() {
        return nomeGrupo;
    }

    public String getEmailUser() {
        return emailUser;
    }
}


