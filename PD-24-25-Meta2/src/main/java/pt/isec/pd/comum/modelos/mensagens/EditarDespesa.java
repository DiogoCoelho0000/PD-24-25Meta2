package pt.isec.pd.comum.modelos.mensagens;

import java.io.Serializable;

public class EditarDespesa implements Serializable {
    String email;
    String grupo;
    String despesa;
    String quemPagou;
    String descricao;
    String data;
    String ID_despesa;

    public EditarDespesa(String email,String grupo, String despesa, String quemPagou, String descricao, String data, String ID_despesa) {
        this.email = email;
        this.grupo = grupo;
        this.despesa = despesa;
        this.quemPagou = quemPagou;
        this.descricao = descricao;
        this.data = data;
        this.ID_despesa = ID_despesa;
    }

    public String getID_despesa() {
        return ID_despesa;
    }

    public void setID_despesa(String ID_despesa) {
        this.ID_despesa = ID_despesa;
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

    public String getDespesa() {
        return despesa;
    }

    public void setDespesa(String despesa) {
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
