package pt.isec.pd.comum.modelos.mensagens;

import java.io.Serializable;

public class CriaDespesa implements Serializable {
    private String grupo;
    private double despesa;

    public CriaDespesa(double despesa, String grupo, String email) {
        this.despesa = despesa;
        this.grupo = grupo;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public double getDespesa() {
        return despesa;
    }

    public void setDespesa(double despesa) {
        this.despesa = despesa;
    }

    @Override
    public String toString() {
        return "CriaDespesa{" +
                "grupo='" + grupo + '\'' +
                ", despesa=" + despesa +
                '}';
    }
}
