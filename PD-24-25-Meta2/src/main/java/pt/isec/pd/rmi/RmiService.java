package pt.isec.pd.rmi;

import org.springframework.stereotype.Service;
import pt.isec.pd.comum.modelos.mensagens.*;
import pt.isec.pd.models.Grupos;
import pt.isec.pd.models.User;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// tenho que passar a conexao da BD

@Service
public class RmiService extends UnicastRemoteObject implements RmiInterface {

    public static final String SERVICE_NAME = "Splitwise-service";
    private final List<Registo> utilizadores;
    private final List<CriaDespesa> despesas;
    private final List<RMIObserverInterface> observers;
    private final List<User> usersList;
    private final List<Grupos> groupsList;

    public RmiService() throws RemoteException {
        super();
        this.utilizadores = new ArrayList<>();
        this.despesas = new ArrayList<>();
        this.observers = new ArrayList<>();
        this.usersList = new ArrayList<>();
        this.groupsList = new ArrayList<>();
    }

    // Registo e Autenticação
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

    // Lista de Usuários e Grupos
    @Override
    public List<User> obterListaUsuarios() throws RemoteException {
        return usersList;
    }

    @Override
    public List<Grupos> obterListaGrupos(String email) throws RemoteException {
        return groupsList.stream()
                .filter(g -> g.getNomeGrupo().contains(email))  // Aqui seria a lógica para garantir a associação correta com o e-mail
                .collect(Collectors.toList());
    }

    // Operações com Despesas
    @Override
    public boolean inserirDespesa(CriaDespesa criaDespesa) throws RemoteException {
        Grupos grupo = groupsList.stream()
                .filter(g -> g.getNomeGrupo().equals(criaDespesa.getGrupo()))
                .findFirst()
                .orElse(null);

        if (grupo == null) {
            System.out.println("Grupo não encontrado: " + criaDespesa.getGrupo());
            return false;
        }

        despesas.add(criaDespesa);
        System.out.println("Despesa inserida no grupo " + criaDespesa.getGrupo());
        notifyObservers("Nova despesa inserida no grupo " + criaDespesa.getGrupo());
        return true;
    }

    @Override
    public boolean eliminarDespesa(EliminaDespesa eliminaDespesa) throws RemoteException {
        for (CriaDespesa d : despesas) {
            if (d.getGrupo().equals(eliminaDespesa.getGrupoNome())) {
                despesas.remove(d);
                System.out.println("Despesa eliminada: " + eliminaDespesa.getID() + " no grupo " + eliminaDespesa.getGrupoNome());
                notifyObservers("Despesa eliminada: " + eliminaDespesa.getID() + " no grupo " + eliminaDespesa.getGrupoNome());
                return true;
            }
        }
        System.out.println("Despesa não encontrada: " + eliminaDespesa.getID() + " no grupo " + eliminaDespesa.getGrupoNome());
        return false;
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
