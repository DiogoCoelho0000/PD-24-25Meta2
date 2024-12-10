package pt.isec.pd.comum.modelos.mensagens;

import java.io.Serializable;

public class CriaDespesa implements Serializable {
    private String grupo;
    private double despesa;
    private String email;
    private String quemPagou;
    private String descricao;
    private String data;

    public CriaDespesa(double despesa,String grupo ,String email, String quemPagou, String descricao, String data) {
        this.despesa = despesa;
        this.grupo = grupo;
        this.email = email;
        this.quemPagou = quemPagou;
        this.descricao = descricao;
        this.data = data;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
        return "CriaDespesa{" +
                "despesa=" + despesa +
                ", email='" + email + '\'' +
                ", quemPagou='" + quemPagou + '\'' +
                ", descricao='" + descricao + '\'' +
                ", data='" + data + '\'' +
                '}';
    }
}
