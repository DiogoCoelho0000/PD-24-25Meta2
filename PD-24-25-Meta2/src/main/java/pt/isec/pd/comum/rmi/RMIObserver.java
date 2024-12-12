package pt.isec.pd.comum.rmi;

import java.rmi.Naming;
import java.rmi.RemoteException;

public class RMIObserver implements RMIObserverInterface {

    protected RMIObserver() throws RemoteException {
    }

    @Override
    public void Notification(String description) throws RemoteException {
        System.out.println("Received notification: " + description);
    }

    public static void main(String[] args) {
        try {
            // Criar URL
            String url = "rmi://localhost:1099/Splitwise-service";

            // Obter Referência
            RmiInterface service = (RmiInterface) Naming.lookup(url);

            // Criar o observador
            RMIObserverInterface observer = new RMIObserver();

            // Adicionar o observador ao serviço
            service.addObserver(observer);
            System.out.println("Observador adicionado com sucesso!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
