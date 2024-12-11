package pt.isec.pd.comum.modelos.mensagens;

import java.io.Serializable;

public class ListarPagamentos implements Serializable {
    private String solicitadoPor;
    private String grupoId;

    public ListarPagamentos(String solicitadoPor, String grupoId) {
        this.solicitadoPor = solicitadoPor;
        this.grupoId = grupoId;
    }


    public String getSolicitadoPor() {
        return solicitadoPor;
    }

    public void setSolicitadoPor(String solicitadoPor) {
        this.solicitadoPor = solicitadoPor;
    }

    public String getGrupoId() {
        return grupoId;
    }

    public void setGrupoId(String grupoId) {
        this.grupoId = grupoId;
    }

    @Override
    public String toString() {
        return "ListarPagamentos{" +
                "solicitadoPor='" + solicitadoPor + '\'' +
                ", grupoId='" + grupoId + '\'' +
                '}';
    }
}
