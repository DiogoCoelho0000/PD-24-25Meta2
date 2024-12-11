package pt.isec.pd.comum.modelos.mensagens;

import java.io.Serializable;

public class EliminaPagamento implements Serializable {
    private static final long serialVersionUID = 1L;

    private String groupId;
    private String data;
    private double valor;
    private String pagaPor;
    private String recebidoPor;

    public EliminaPagamento(String groupId/*, String data*/, double valor, String pagaPor, String recebidoPor) {
        this.groupId = groupId;
        //this.data = data;
        this.valor = valor;
        this.pagaPor = pagaPor;
        this.recebidoPor = recebidoPor;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getPagaPor() {
        return pagaPor;
    }

    public void setPagaPor(String pagaPor) {
        this.pagaPor = pagaPor;
    }

    public String getRecebidoPor() {
        return recebidoPor;
    }

    public void setRecebidoPor(String recebidoPor) {
        this.recebidoPor = recebidoPor;
    }

    @Override
    public String toString() {
        return "EliminaPagamento{" +
                "groupId='" + groupId + '\'' +
                ", data='" + data + '\'' +
                ", valor=" + valor +
                ", pagaPor='" + pagaPor + '\'' +
                ", recebidoPor='" + recebidoPor + '\'' +
                '}';
    }
}
