package pt.isec.pd.rmi;

import org.springframework.stereotype.Component;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

@Component
public class RMIServiceLauncher {
    public void start() throws RemoteException {
        // Porta onde o RMI Registry será iniciado
        int registryPort = 1099;
        String hostname = "127.0.0.1"; // Use o IP explícito

        try {
            // Iniciar o RMI Registry, se necessário
            LocateRegistry.createRegistry(registryPort);
            System.out.println("RMI Registry iniciado na porta " + registryPort);

            // Registro do serviço RMI
            String serviceName = "RmiService";
            String serviceUrl = "rmi://" + hostname + ":" + registryPort + "/" + serviceName;

            RmiService service = new RmiService();
            Naming.rebind(serviceUrl, service);
            System.out.println("Serviço RMI registrado em: " + serviceUrl);
        } catch (Exception e) {
            System.err.println("Erro ao iniciar o serviço RMI: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
