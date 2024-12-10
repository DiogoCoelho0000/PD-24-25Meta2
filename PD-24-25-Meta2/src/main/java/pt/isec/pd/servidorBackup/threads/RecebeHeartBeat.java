package pt.isec.pd.servidorBackup.threads;

import pt.isec.pd.comum.modelos.HeartBeat;
import pt.isec.pd.servidorBackup.BackupBd;

import java.io.*;
import java.net.DatagramPacket;
import java.net.MulticastSocket;
import java.net.SocketException;

public class RecebeHeartBeat extends Thread{

    private MulticastSocket multicastSocket;
    private String directoriaBD;

    public static int TAM_MAX = 1000;

    public RecebeHeartBeat(MulticastSocket multicastSocket, String directoriaBD){
        this.multicastSocket = multicastSocket;
        this.directoriaBD = directoriaBD;
        System.out.println(directoriaBD);
    }

    @Override
    public void run(){

        Object object;
        DatagramPacket datagramPacket;
        boolean BDExiste = false;
        File ficheiro = new File("src/pt/isec/pd/db/Base_de_dados.db");
        try {
            multicastSocket.setSoTimeout(30 * 1000); /** Timer especificado pelo Enunciado do trabalho */

            while (true){
                datagramPacket = new DatagramPacket(new byte[TAM_MAX],TAM_MAX);

                try {
                    multicastSocket.receive(datagramPacket);

                    try {
                        ObjectInputStream oin = new ObjectInputStream( new ByteArrayInputStream(datagramPacket.getData()));
                            object = oin.readObject();

                            /** heartbeat*/
                            if (object instanceof HeartBeat){
                                HeartBeat heartBeat = (HeartBeat) object;

                                if (!BDExiste){
                                    //byte[] dadosBD = heartBeat.getFicheiroDB();
                                    FileInputStream fich = new FileInputStream(ficheiro);
                                    byte[] dadosBD = new byte[(int) ficheiro.length()];
                                    fich.read(dadosBD);

                                    try (FileOutputStream fos = new FileOutputStream(directoriaBD+ "/Base_de_dados_backup.db")){
                                        fos.write(dadosBD);
                                        BackupBd.ligacao(directoriaBD + "/Base_de_dados_backup.db");
                                        BDExiste = true;
                                    } catch (IOException e) {
                                        throw new RuntimeException(e);
                                    }
                                }
                                else {
                                    if (heartBeat.getBdVersao() != BackupBd.getVersao())
                                        return;
                                }
                            }


                        } catch (IOException | ClassNotFoundException ex) {
                        throw new RuntimeException(ex);
                        }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }

    }
}
