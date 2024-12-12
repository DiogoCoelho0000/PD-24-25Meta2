package pt.isec.pd.comum.rmi;

import pt.isec.pd.comum.modelos.mensagens.*;
import pt.isec.pd.models.Grupos;
import pt.isec.pd.models.User;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class RmiService extends UnicastRemoteObject implements RmiInterface {

    public static final String SERVICE_NAME = "Splitwise-service";
    private final List<Registo> utilizadores;
    private final List<CriaDespesa> despesas;
    private final List<RMIObserverInterface> observers;  // Lista de observadores
    private final List<User> usersList;  // Lista de usuários
    private final List<Grupos> groupsList;  // Lista de grupos

    public RmiService() throws RemoteException {
        super();
        this.utilizadores = new ArrayList<>();
        this.despesas = new ArrayList<>();
        this.observers = new ArrayList<>();  // Inicializando a lista de observadores
        this.usersList = new ArrayList<>(); // Inicializando lista de usuários
        this.groupsList = new ArrayList<>(); // Inicializando lista de grupos
    }

    @Override
    public boolean registarUtilizador(Registo registo) throws RemoteException {
        for (Registo u : utilizadores) {
            if (u.getEmail().equalsIgnoreCase(registo.getEmail())) {
                System.out.println("Utilizador já registado: " + registo.getEmail());
                return false;
            }
        }

        utilizadores.add(registo);
        System.out.println("Novo utilizador registado: " + registo.getEmail());
        return true;
    }

    @Override
    public boolean autenticarUtilizador(Login login) throws RemoteException {
        for (Registo u : utilizadores) {
            if (u.getEmail().equalsIgnoreCase(login.getEmail()) && u.getPassword().equals(login.getPassword())) {
                System.out.println("Utilizador autenticado: " + login.getEmail());
                return true;
            }
        }

        System.out.println("Falha na autenticação: " + login.getEmail());
        return false;
    }

    @Override
    public List<User> obterListaUsuarios() throws RemoteException {
        return usersList;  // Retorna a lista de usuários
    }

    @Override
    public List<Grupos> obterListaGrupos() throws RemoteException {
        return groupsList;  // Retorna a lista de grupos
    }

    @Override
    public boolean inserirDespesa(CriaDespesa criaDespesa) throws RemoteException {
        // Adiciona a despesa à lista e imprime os detalhes
        despesas.add(criaDespesa);
        System.out.println("Despesa inserida no grupo " + criaDespesa.getGrupo() + ":");
        System.out.println(" - Descrição: " + criaDespesa.getDescricao());
        System.out.println(" - Valor: " + criaDespesa.getDespesa());
        System.out.println(" - Quem pagou: " + criaDespesa.getQuemPagou());
        System.out.println(" - Data: " + criaDespesa.getData());

        // Notifica os observadores sobre a inserção da despesa
        notifyObservers("Nova despesa inserida no grupo " + criaDespesa.getGrupo());
        return true;
    }

    @Override
    public boolean eliminarDespesa(EliminaDespesa eliminaDespesa) throws RemoteException {
        // Procura e remove a despesa com base no ID
        for (CriaDespesa d : despesas) {
            if (d.getGrupo().equals(eliminaDespesa.getGrupoNome()) && d.getDescricao().equalsIgnoreCase(eliminaDespesa.getID())) {
                despesas.remove(d);
                System.out.println("Despesa eliminada: " + eliminaDespesa.getID() + " no grupo " + eliminaDespesa.getGrupoNome());

                // Notifica os observadores sobre a eliminação da despesa
                notifyObservers("Despesa eliminada: " + eliminaDespesa.getID() + " no grupo " + eliminaDespesa.getGrupoNome());
                return true;
            }
        }

        System.out.println("Despesa não encontrada: " + eliminaDespesa.getID() + " no grupo " + eliminaDespesa.getGrupoNome());
        return false;
    }

    @Override
    public void addObserver(RMIObserverInterface observer) throws RemoteException {
        observers.add(observer);
        System.out.println("Novo observador adicionado.");
    }

    private void notifyObservers(String description) throws RemoteException {
        for (RMIObserverInterface observer : observers) {
            observer.Notification(description);
        }
    }

    static public void main(String[] args) throws RemoteException, MalformedURLException, AlreadyBoundException {
        // Criar Registry
        LocateRegistry.createRegistry(1099);

        // Criar Serviço
        RmiService service = new RmiService();

        // Registar Serviço
        Naming.bind("rmi://localhost:1099/" + SERVICE_NAME, service);
        System.out.println("RMI Service started...");
    }
}
