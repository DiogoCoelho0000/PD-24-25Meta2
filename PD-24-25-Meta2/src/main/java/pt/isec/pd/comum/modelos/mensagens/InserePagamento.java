package pt.isec.pd.comum.modelos.mensagens;

import java.io.Serializable;

public class InserePagamento implements Serializable {
    private static final long serialVersionUID = 1L;

    private String groupId;
    private String pagaPor;
    private String recebidoPor;
    private double valor;
    private String data;


    public InserePagamento(String groupId, String pagaPor, String recebidoPor, double valor,String data) {
        this.groupId = groupId;
        this.pagaPor = pagaPor;
        this.recebidoPor = recebidoPor;
        this.valor = valor;
        this.data = data;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
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

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getData() {
        return data;
    }

    @Override
    public String toString() {
        return "InserePagamento{" +
                "groupId='" + groupId + '\'' +
                ", pagaPor='" + pagaPor + '\'' +
                ", recebidoPor='" + recebidoPor + '\'' +
                ", valor=" + valor +
                ", data='" + data + '\'' +
                '}';
    }
}
