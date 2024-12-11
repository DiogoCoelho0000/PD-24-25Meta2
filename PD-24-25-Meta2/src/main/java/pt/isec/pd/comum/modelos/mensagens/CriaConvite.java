package pt.isec.pd.comum.modelos.mensagens;

import java.io.Serializable;

public class CriaConvite implements Serializable {
    private String nomeGrupo;
    private String email;
    private String emailDestinatario;

    public CriaConvite(String nomeGrupo, String email, String emailDestinatario) {
        this.nomeGrupo = nomeGrupo;
        this.email = email;
        this.emailDestinatario = emailDestinatario;
    }

    public String getNomeGrupo() {
        return nomeGrupo;
    }

    public void setNomeGrupo(String nomeGrupo) {
        this.nomeGrupo = nomeGrupo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmailDestinatario() {
        return emailDestinatario;
    }



    public void setEmailDestinatario(String emailDestinatario) {
        this.emailDestinatario = emailDestinatario;

    }
    @Override
    public String toString() {
        return "CriaConvite{" +
                "nomeGrupo='" + nomeGrupo + '\'' +
                ", email='" + email + '\'' +
                ", emailDestinatario='" + emailDestinatario + '\'' +
                '}';
    }
}
