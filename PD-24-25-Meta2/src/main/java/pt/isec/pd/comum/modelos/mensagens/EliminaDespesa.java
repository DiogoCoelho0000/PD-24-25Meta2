package pt.isec.pd.comum.modelos.mensagens;

import java.io.Serializable;

public class EliminaDespesa implements Serializable {
    private String email;
    private String ID;
    private String grupoNome;

    public EliminaDespesa(String email, String grupoNome,String ID) {
        this.email = email;
        this.ID = ID;
        this.grupoNome = grupoNome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getGrupoNome() {
        return grupoNome;
    }

    public void setGrupoNome(String grupoNome) {
        this.grupoNome = grupoNome;
    }

    @Override
    public String toString() {
        return "EliminaDespesa{" +
                "email='" + email + '\'' +
                ", ID='" + ID + '\'' +
                ", grupoNome='" + grupoNome + '\'' +
                '}';
    }
}
