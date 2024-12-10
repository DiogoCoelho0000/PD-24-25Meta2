package pt.isec.pd.comum.modelos.mensagens;

import java.io.Serializable;

public class InsereGrupo implements Serializable {
    private String email;
    private String nomeGrupo;

    public InsereGrupo(String email, String nomeGrupo) {
        this.email = email;
        this.nomeGrupo = nomeGrupo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNomeGrupo() {
        return nomeGrupo;
    }

    public void setNomeGrupo(String nomeGrupo) {
        this.nomeGrupo = nomeGrupo;
    }

    @Override
    public String toString() {
        return "InsereGrupo{" +
                "email='" + email + '\'' +
                ", nomeGrupo='" + nomeGrupo + '\'' +
                '}';
    }
}
