package pt.isec.pd.comum.modelos.mensagens;

import java.io.Serializable;

public class VerGastoTotal implements Serializable {
    private String grupoNome;
    private double valorTotal;
    private String email;

    public VerGastoTotal(String grupoNome, String email) {
        this.grupoNome = grupoNome;
        this.email = email;
    }

    public String getGrupoNome() {
        return grupoNome;
    }

    public void setGrupoNome(String grupoNome) {
        this.grupoNome = grupoNome;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "VerGastoTotal{" +
                "grupoNome='" + grupoNome + '\'' +
                ", valorTotal=" + valorTotal +
                ", email='" + email + '\'' +
                '}';
    }
}
