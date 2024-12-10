package pt.isec.pd.comum.modelos;

import java.io.Serializable;
import java.util.List;

public class Despesa implements Serializable {
    private String email;
    private String grupo;
    private double despesa;
    private String quemPagou;
    private String descricao;
    private String data;
    private String idDespesa;
    private List<Despesa> despesaList;

    public String getIdDespesa() {
        return idDespesa;
    }

    public void setIdDespesa(String idDespesa) {
        this.idDespesa = idDespesa;
    }

    public List<Despesa> getDespesaList() {
        return despesaList;
    }

    public void setDespesaList(List<Despesa> despesaList) {
        this.despesaList = despesaList;
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
        return "Despesa{" +
                "email='" + email + '\'' +
                ", grupo='" + grupo + '\'' +
                ", despesa=" + despesa +
                ", quemPagou='" + quemPagou + '\'' +
                ", descricao='" + descricao + '\'' +
                ", data='" + data + '\'' +
                '}';
    }
}

