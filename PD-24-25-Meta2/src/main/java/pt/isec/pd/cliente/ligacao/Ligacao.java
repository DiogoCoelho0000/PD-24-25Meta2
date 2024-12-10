package pt.isec.pd.cliente.ligacao;

import pt.isec.pd.comum.modelos.RespostaServidorMensagem;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Ligacao {

    static InetAddress servidorEndereco;
    static int servidorPorto;
    private Socket socket = null;
    private static final Object lock = new Object();

    public Ligacao(String endereco, String porto){
        try {
            servidorEndereco = InetAddress.getByName(endereco);
            servidorPorto = Integer.parseInt(porto);
            //Cria o Socket para ligar ao Servidor Principal
            socket = new Socket(servidorEndereco,servidorPorto);
            System.out.println("Ligacao efectuada com sucesso!");
            //socket.setSoTimeout(10 * 1000);

        } catch (UnknownHostException e) {
            System.out.println("Destino desconhecido:\n\t" + e);
        } catch (IOException e) {
            System.out.println("Ocorreu um erro no acesso ao socket:\n\t" + e);
        }
    }
    public void LigacaoNotificacao(String endereco){
        try {
            servidorEndereco = InetAddress.getByName(endereco);
            servidorPorto = 1234;
            //Cria o Socket para ligar ao Servidor Principal
            socket = new Socket(servidorEndereco,servidorPorto);
            System.out.println("Ligacao efectuada com sucesso!");
            //socket.setSoTimeout(10 * 1000);

        } catch (UnknownHostException e) {
            System.out.println("Destino desconhecido:\n\t" + e);
        } catch (IOException e) {
            System.out.println("Ocorreu um erro no acesso ao socket:\n\t" + e);
        }
    }
    public Ligacao(){

    }
    //metodo que recebe o socket e a serialização do conteudo do objecto
    public void enviaMensagem(/*Socket socket,*/ Serializable mensagem){
        try {
            ObjectOutputStream out;
            out = new ObjectOutputStream(socket.getOutputStream());
            out.writeObject(mensagem); //colocar o objecto aqui serializado a enviar
            out.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public RespostaServidorMensagem recebeMensagem(){

            RespostaServidorMensagem resposta;
            try {
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                resposta = (RespostaServidorMensagem) in.readObject();
                System.out.println("RESPOSTA RECEBE -> " + resposta);

            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            return resposta;

    }


}
