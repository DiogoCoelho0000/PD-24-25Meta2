package pt.isec.pd.rmi;

import org.springframework.stereotype.Service;
import pt.isec.pd.comum.modelos.mensagens.*;
import pt.isec.pd.models.Despesa;
import pt.isec.pd.models.Grupos;
import pt.isec.pd.models.User;
import pt.isec.pd.db.Bd; // Importando a classe Bd

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

@Service
public class RmiService extends UnicastRemoteObject implements RmiInterface {

    private final List<RMIObserverInterface> observers;
    private Bd bd;  // Conexão com a BD

    // Injetar a classe Bd no construtor do serviço RMI
    public RmiService(Bd bd) throws RemoteException {
        super();
        this.bd = bd;  // Inicializando a conexão com a BD
        this.observers = new ArrayList<>();
    }

    // Registo e Autenticação
    @Override
    public boolean registarUtilizador(Registo registo) throws RemoteException {
        // Utilize a classe Bd para registar o usuário na base de dados
        return bd.setUserDB(registo.getNome(), registo.getnTelefone(), registo.getEmail(), registo.getPassword());
    }

    @Override
    public boolean autenticarUtilizador(Login login) throws RemoteException {
        // Utilize a classe Bd para verificar a autenticação do usuário
        return bd.getUserDB(login.getEmail(), login.getPassword());
    }

    // Lista de Usuários e Grupos
    @Override
    public List<User> obterListaUsuarios() throws RemoteException {
        // Chama o método da classe Bd para obter a lista de usuários
        return bd.obterUsuarios();  // Supondo que bd seja uma instância da classe Bd
    }


    @Override
    public List<Grupos> obterListaGrupos(String email) throws RemoteException {
        // Use Bd para obter grupos de um usuário
        return bd.listarGruposDB(email);  // Ou o método adequado em Bd
    }

    @Override
    public boolean inserirDespesa(Despesa despesa) throws RemoteException {
        // Aqui você já tem o objeto despesa com todos os campos configurados, incluindo o email
        return bd.criaDespesa(despesa.getGrupo(), despesa, despesa.getEmail()); // Passando diretamente o objeto Despesa
    }

    @Override
    public boolean eliminarDespesa(Despesa despesa) throws RemoteException {
        // A lógica de eliminação da despesa vai utilizar a BD
        return bd.eliminarDespesa(despesa.getEmail(), despesa.getGrupo(), despesa.getIdDespesa());
    }

    // Observadores
    @Override
    public void addObserver(RMIObserverInterface observer) throws RemoteException {
        observers.add(observer);
        System.out.println("Novo observador adicionado.");
    }

    private void notifyObservers(String description) {
        observers.removeIf(observer -> {
            try {
                observer.Notification(description);
                return false;
            } catch (RemoteException e) {
                System.out.println("Observador desconectado. Removendo...");
                return true;
            }
        });
    }
}
