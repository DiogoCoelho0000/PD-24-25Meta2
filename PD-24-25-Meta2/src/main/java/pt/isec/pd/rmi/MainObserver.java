package pt.isec.pd.rmi;

import pt.isec.pd.rmi.observer.RMIObserver;
import pt.isec.pd.rmi.server.RmiInterface;

import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;

public class MainObserver {
    public static void main(String[] args) {
        System.out.println("Starting observer RMI...");

        try {
            String registry = "localhost";
            String registration = "rmi://" + registry + ":" + Registry.REGISTRY_PORT + "/RmiService";

            Remote remoteService = Naming.lookup(registration);
            RmiInterface rmiService = (RmiInterface) remoteService;

            RMIObserver observer = new RMIObserver();
            rmiService.addObserver(observer);


            /*while (true){
                Thread.sleep(1000);
            }*/
        } catch ( NotBoundException e ) {
            System.out.println ("No 'RMIObserverInterface' service available!");
        } catch ( RemoteException e ) {
            System.out.println("RMI error - " + e);
        } catch ( Exception e ) {
            System.out.println("Error - " + e);
        }
    }
}
