package pt.isec.pd.rmi.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.isec.pd.db.Bd;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

@Component
public class RMIServiceLauncher {

    // Injeção de dependência da classe Bd (Banco de Dados)
    @Autowired
    private Bd bd; // O Spring irá injetar a instância de Bd automaticamente

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

            // Criar o serviço RMI e passar a conexão do banco de dados
            RmiService service = new RmiService(bd);  // Passa o Bd para o RmiService
            Naming.rebind(serviceUrl, service);
            System.out.println("Serviço RMI registrado em: " + serviceUrl);
        } catch (Exception e) {
            System.err.println("Erro ao iniciar o serviço RMI: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
