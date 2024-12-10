package pt.isec.pd.servidorBackup;

import pt.isec.pd.servidorBackup.threads.RecebeHeartBeat;

import java.io.IOException;
import java.net.*;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ServidorBackup {


    private static String enderecoMulticast = "230.44.44.44";
    private static int portoMulticast = 4444;
    private static InetAddress grupo;
    private static NetworkInterface nif;
    private static String directoriaBD;
    private static MulticastSocket multicastSocket;

    public static void main(String args[]){

        int argc = args.length;
        if (argc != 1){
            System.out.println("java ServidorBackup DATA_BASE_DIRECTORY");
            return;
        }

        directoriaBD = args[0];

        Path caminho = Paths.get(directoriaBD);

        boolean directoriaVazia = true;

        /**Cria a directoria se já não existir...*/
        if (!Files.exists(caminho) || !Files.isDirectory(caminho)){
            try{
                Files.createDirectories(caminho);
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        }

        /*TODO
        *  VAMOS VER SE O SERVIDOR TERMINA SE A DIRECTORIA ESTIVER VAZIA*/
        try {
            DirectoryStream<Path> directoryStream = Files.newDirectoryStream(caminho);
            directoriaVazia = !directoryStream.iterator().hasNext();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        if (!directoriaVazia){
            System.out.println("A directoria de backup não se encontra vazia. A sair....");
            return;
        }



        try {
            grupo = InetAddress.getByName(enderecoMulticast);

            try {
                nif = NetworkInterface.getByInetAddress(InetAddress.getByName(enderecoMulticast));
            } catch (SocketException e) {
                return;
            }

            multicastSocket = new MulticastSocket(portoMulticast);

            multicastSocket.joinGroup(new InetSocketAddress(grupo, portoMulticast),nif);

            RecebeHeartBeat recebeHeartBeat = new RecebeHeartBeat(multicastSocket, directoriaBD);
            recebeHeartBeat.start();
            recebeHeartBeat.join();

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
