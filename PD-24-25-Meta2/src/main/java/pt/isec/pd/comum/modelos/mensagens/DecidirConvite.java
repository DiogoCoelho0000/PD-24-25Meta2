package pt.isec.pd.comum.modelos.mensagens;

import java.io.Serializable;

public class DecidirConvite implements Serializable {
    private String nomeGrupo;
    private String emailUser;
    private String decisao;

    public DecidirConvite(String nomeGrupo, String emailUser, String decisao) {
        this.nomeGrupo = nomeGrupo;
        this.emailUser = emailUser;
        this.decisao = decisao;
    }

    public String getDecisao() {
        return decisao;
    }

    public void setDecisao(String decisao) {
        this.decisao = decisao;
    }

    public String getEmailUser() {
        return emailUser;
    }

    public void setEmailUser(String emailUser) {
        this.emailUser = emailUser;
    }


    public String getNomeGrupo() {
        return nomeGrupo;
    }

    public void setNomeGrupo(String nomeGrupo) {
        this.nomeGrupo = nomeGrupo;
    }

    @Override
    public String toString() {
        return "DecidirConvite{" +
                "nomeGrupo='" + nomeGrupo + '\'' +
                ", emailUser='" + emailUser + '\'' +
                '}';
    }
}
