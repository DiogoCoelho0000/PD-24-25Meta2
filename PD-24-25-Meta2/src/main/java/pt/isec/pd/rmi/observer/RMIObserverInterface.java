package pt.isec.pd.rmi.observer;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMIObserverInterface extends Remote {

    void Notification(String description) throws RemoteException;

}