package pt.isec.pd.servidorBackup.heartBeat;

import pt.isec.pd.comum.modelos.HeartBeat;
import pt.isec.pd.db.Bd;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class HeartBeatThread extends Thread{

    private int porto;
    InetAddress grupo;
    MulticastSocket multicastSocket;

    public HeartBeatThread(int porto, InetAddress grupo, MulticastSocket multicastSocket){
        this.porto = porto;
        this.grupo = grupo;
        this.multicastSocket = multicastSocket;

    }

    @Override
    public void run(){
        while (true){
            try {
                Thread.sleep(10*1000); //para fazer os 10 segundos
                System.out.println("HEART 10s");
                HeartBeat heartBeat = new HeartBeat(porto, Bd.obtemVersao());
                DatagramPacket datagramPacket;
                try (ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
                     ObjectOutputStream objStream = new ObjectOutputStream(byteStream)){

                    objStream.writeObject(heartBeat);
                    objStream.flush();

                    datagramPacket = new DatagramPacket(byteStream.toByteArray(),byteStream.size(),grupo,porto);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                multicastSocket.send(datagramPacket);
            } catch (InterruptedException | IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
