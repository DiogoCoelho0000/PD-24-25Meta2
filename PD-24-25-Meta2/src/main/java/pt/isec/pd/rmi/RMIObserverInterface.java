package pt.isec.pd.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMIObserverInterface extends Remote {

    // Método para notificação de eventos
    void Notification(String description) throws RemoteException;

}