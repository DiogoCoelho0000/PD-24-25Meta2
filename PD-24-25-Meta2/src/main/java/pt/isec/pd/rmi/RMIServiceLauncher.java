package pt.isec.pd.rmi;

import org.springframework.stereotype.Component;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

@Component
public class RMIServiceLauncher {

    private final RmiService rmiService;

    // Injeção de dependência do RmiService
    public RMIServiceLauncher(RmiService rmiService) {
        this.rmiService = rmiService;
    }

    // Este método agora será chamado para iniciar o RMI
    public void start() {
        try {
            int port = 1099;
            LocateRegistry.createRegistry(port);  // Inicia o registro RMI
            System.out.println("Registro RMI iniciado na porta " + port);

            // Registra o serviço RMI
            String serviceUrl = "rmi://localhost:" + port + "/" + RmiService.SERVICE_NAME;
            Naming.rebind(serviceUrl, rmiService);
            System.out.println("Serviço RMI registrado em: " + serviceUrl);

        } catch (Exception e) {
            System.err.println("Erro ao registrar o serviço RMI: " + e.getMessage());
        }
    }
}
