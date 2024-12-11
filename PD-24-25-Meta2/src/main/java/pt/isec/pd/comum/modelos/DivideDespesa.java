package pt.isec.pd.comum.modelos;

public class DivideDespesa {

    private int despesaId;
    private int grupoId;
    private double valorTotal;


    public DivideDespesa(int despesaId, int grupoId, double valorTotal) {
        this.despesaId = despesaId;
        this.grupoId = grupoId;
        this.valorTotal = valorTotal;
    }


    public int getDespesaId() {
        return despesaId;
    }

    public void setDespesaId(int despesaId) {
        this.despesaId = despesaId;
    }

    public int getGrupoId() {
        return grupoId;
    }

    public void setGrupoId(int grupoId) {
        this.grupoId = grupoId;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }
}
