package pt.isec.pd.rmi;

import java.rmi.Naming;
import java.rmi.RemoteException;
// tem que adicionar

public class RMIObserver implements RMIObserverInterface {

    protected RMIObserver() throws RemoteException {
    }

    @Override
    public void Notification(String description) throws RemoteException {
        System.out.println("Received notification: " + description);
    }

}
