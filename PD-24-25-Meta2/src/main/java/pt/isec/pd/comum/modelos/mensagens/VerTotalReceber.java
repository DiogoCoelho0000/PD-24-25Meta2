package pt.isec.pd.comum.modelos.mensagens;

import java.io.Serializable;

public class VerTotalReceber implements Serializable { // Não está a ser usado
    private String grupoNome;
    private String email;

    public VerTotalReceber(String grupoNome, String email) {
        this.grupoNome = grupoNome;
        this.email = email;
    }

    public String getGrupoNome() {
        return grupoNome;
    }

    public String getEmail() {
        return email;
    }
}
