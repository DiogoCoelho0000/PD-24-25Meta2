package pt.isec.pd.rmi;

import org.springframework.stereotype.Component;
import java.rmi.RemoteException;

@Component
public class RMIObserver implements RMIObserverInterface {

    protected RMIObserver() throws RemoteException {
    }

    @Override
    public void Notification(String description) throws RemoteException {
        System.out.println("Recebido a notificação: " + description);
    }
}

