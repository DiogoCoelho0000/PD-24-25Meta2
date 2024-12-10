package pt.isec.pd.cliente;

import pt.isec.pd.cliente.UI.ClienteConsolaUI;
import pt.isec.pd.cliente.controladores.ControladorPrincipal;
import pt.isec.pd.cliente.ligacao.Ligacao;

public class Cliente {




    public static void main(String[] args) throws InterruptedException {
        int argc = args.length;

        if (argc != 2){
            System.out.println("java Cliente IPAdress PORT");
        }
        Ligacao ligacao = new Ligacao(args[0],args[1]);
        ControladorPrincipal controladorPrincipal = new ControladorPrincipal(ligacao);
        ClienteConsolaUI clienteConsolaUI = new ClienteConsolaUI(controladorPrincipal);
        clienteConsolaUI.iniciar();
    }
}