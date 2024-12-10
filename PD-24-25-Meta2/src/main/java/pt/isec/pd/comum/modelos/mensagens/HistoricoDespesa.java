package pt.isec.pd.comum.modelos.mensagens;

import java.io.Serializable;

public class HistoricoDespesa implements Serializable {
    String email;
    String grupo;
    double despesa;
    String quemPagou;
    String descricao;
    String data;

    public HistoricoDespesa(String grupo) {
        this.grupo = grupo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getQuemPagou() {
        return quemPagou;
    }

    public void setQuemPagou(String quemPagou) {
        this.quemPagou = quemPagou;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "EditarDespesa{" +
                "email='" + email + '\'' +
                ", grupo='" + grupo + '\'' +
                ", despesa=" + despesa +
                ", quemPagou='" + quemPagou + '\'' +
                ", descricao='" + descricao + '\'' +
                ", data='" + data + '\'' +
                '}';
    }
}
