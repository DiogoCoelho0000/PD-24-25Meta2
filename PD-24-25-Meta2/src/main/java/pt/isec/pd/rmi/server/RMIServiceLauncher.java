package pt.isec.pd.rmi.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.isec.pd.db.Bd;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

@Component
public class RMIServiceLauncher {
private final RmiService rmiService;

    @Autowired
    public RMIServiceLauncher(RmiService rmiService) {
        this.rmiService = rmiService;
    }

    public void start() throws RemoteException {
        int registryPort = 1099;
        String hostname = "127.0.0.1";

        try {
            LocateRegistry.createRegistry(registryPort);
            System.out.println("RMI Registry iniciado na porta " + registryPort);

            String serviceName = "RmiService";
            String serviceUrl = "rmi://" + hostname + ":" + registryPort + "/" + serviceName;

            Naming.rebind(serviceUrl, rmiService);
            System.out.println("Serviço RMI registado em: " + serviceUrl);
        } catch (Exception e) {
            System.err.println("Erro ao iniciar o serviço RMI: " + e.getMessage());
            e.printStackTrace();
        }
    }

}
