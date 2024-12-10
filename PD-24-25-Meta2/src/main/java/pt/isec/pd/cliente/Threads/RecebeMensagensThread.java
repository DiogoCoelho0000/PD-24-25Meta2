package pt.isec.pd.cliente.Threads;

import pt.isec.pd.cliente.ligacao.Ligacao;
import pt.isec.pd.cliente.modelos.Dados;


public class RecebeMensagensThread extends Thread {
    private Ligacao ligacao;
    private Dados dados;


    public RecebeMensagensThread(Ligacao ligacao, Dados dados){
        this.ligacao = ligacao;
        this.dados = dados;
    }

    @Override
    public void run() {
        while (true) {


        }
    }
}







