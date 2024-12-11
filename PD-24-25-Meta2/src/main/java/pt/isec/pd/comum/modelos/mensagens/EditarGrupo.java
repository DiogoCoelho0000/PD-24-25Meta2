package pt.isec.pd.comum.modelos.mensagens;

import java.io.Serializable;

public class EditarGrupo implements Serializable {
    private String email;
    private String grupoNome;
    private String grupoNovoNome;

    public EditarGrupo(String email, String grupoNome, String grupoNovoNome) {
        this.email = email;
        this.grupoNome = grupoNome;
        this.grupoNovoNome = grupoNovoNome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGrupoNome() {
        return grupoNome;
    }

    public void setGrupoNome(String grupoNome) {
        this.grupoNome = grupoNome;
    }

    public String getGrupoNovoNome() {
        return grupoNovoNome;
    }

    public void setGrupoNovoNome(String grupoNovoNome) {
        this.grupoNovoNome = grupoNovoNome;
    }

    @Override
    public String toString() {
        return "EditarGrupo{" +
                "email='" + email + '\'' +
                ", grupoNome='" + grupoNome + '\'' +
                ", grupoNovoNome='" + grupoNovoNome + '\'' +
                '}';
    }
}
