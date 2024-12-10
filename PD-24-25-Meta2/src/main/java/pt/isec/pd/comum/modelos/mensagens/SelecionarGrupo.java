package pt.isec.pd.comum.modelos.mensagens;

import java.io.Serializable;

public class SelecionarGrupo implements Serializable {
    private String solicitadoPor;
    private String grupoNome;

    public SelecionarGrupo(String solicitadoPor, String grupoNome) {
        this.solicitadoPor = solicitadoPor;
        this.grupoNome = grupoNome;
    }

    public String getSolicitadoPor() {
        return solicitadoPor;
    }

    public void setSolicitadoPor(String solicitadoPor) {
        this.solicitadoPor = solicitadoPor;
    }

    public String getGrupoNome() {
        return grupoNome;
    }

    public void setGrupoNome(String grupoNome) {
        this.grupoNome = grupoNome;
    }

    @Override
    public String toString() {
        return "SelecionarGrupo{" +
                "solicitadoPor='" + solicitadoPor + '\'' +
                ", grupoNome='" + grupoNome + '\'' +
                '}';
    }
}
