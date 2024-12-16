package pt.isec.pd.rmi.observer;


import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


public class RMIObserver extends UnicastRemoteObject implements RMIObserverInterface {

    public RMIObserver() throws RemoteException {
        super();
    }

    @Override
    public void Notification(String description) throws RemoteException {
        System.out.println("Recebido a notificação: " + description);
    }


}

