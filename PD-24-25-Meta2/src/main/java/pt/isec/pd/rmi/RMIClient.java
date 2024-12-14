package pt.isec.pd.rmi;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

// Tem que ser tudo alterado
// Falta fazer o menu aqui
public class RMIClient {

    public static void main(String[] args) {
        try {
            // Conectar-se ao RMI registry (localhost ou servidor)
            LocateRegistry.getRegistry("localhost", 1099);

            // Obter referência ao serviço RMI
            RmiInterface rmiService = (RmiInterface) Naming.lookup("//localhost/RmiService");

            // Criar um observador (o próprio cliente)
            RMIObserver observer = new RMIObserver();

            // Registrar o observador no serviço RMI
            rmiService.addObserver(observer);

            System.out.println("Cliente RMI registrado como observador.");

            // A partir daqui, o cliente vai receber as notificações do servidor

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
