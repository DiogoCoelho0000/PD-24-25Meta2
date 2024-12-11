package pt.isec.pd.comum.modelos.mensagens;

public class TotalDevidoInfo {
    private double totalDevido;
    private int numeroPessoas;
    private double valorPagoPorMembro;


    public double getTotalDevido() {
        return totalDevido;
    }

    public void setTotalDevido(double totalDevido) {
        this.totalDevido = totalDevido;
    }


    public int getNumeroPessoas() {
        return numeroPessoas;
    }

    public void setNumeroPessoas(int numeroPessoas) {
        this.numeroPessoas = numeroPessoas;
    }


    public double getValorPagoPorMembro() {
        return valorPagoPorMembro;
    }

    public void setValorPagoPorMembro(double valorPagoPorMembro) {
        this.valorPagoPorMembro = valorPagoPorMembro;
    }
}
