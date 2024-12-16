package pt.isec.pd.rmi.server;

import org.springframework.stereotype.Service;
import pt.isec.pd.comum.modelos.mensagens.*;
import pt.isec.pd.models.Despesa;
import pt.isec.pd.models.Grupos;
import pt.isec.pd.models.User;
import pt.isec.pd.db.Bd; // Importando a classe Bd
import pt.isec.pd.rmi.observer.RMIObserverInterface;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

@Service
public class RmiService extends UnicastRemoteObject implements RmiInterface {

    private final List<RMIObserverInterface> observers;
    private Bd bd;
    private static String nomeUser = null;

    public RmiService(Bd bd) throws RemoteException {
        super();
        this.bd = bd;
        this.observers = new ArrayList<>();
    }

    // Registo e Autenticação
    @Override
    public boolean registarUtilizador(Registo registo) throws RemoteException {

        boolean resultado = bd.setUserDB(registo.getNome(), registo.getnTelefone(), registo.getEmail(), registo.getPassword());
        if (resultado) {
            notifyObservers("Cliente " + registo.getNome() + " registado com sucesso!");
        } else {
            notifyObservers("Erro ao registar o cliente!");
        }

        return resultado;

    }

    @Override
    public boolean autenticarUtilizador(Login login) throws RemoteException {
        boolean resultado = bd.getUserDB(login.getEmail(), login.getPassword());

        if (resultado) {
            notifyObservers("Cliente " + login.getEmail() + " autenticado com sucesso!");
            nomeUser = login.getEmail();
        }
        else
            notifyObservers("Falha na autenticacao!");
        return resultado;
    }


    @Override
    public List<User> obterListaUsers() throws RemoteException {
        notifyObservers("Cliente " + nomeUser + " requisitou a lista de utilizadores!");
        return bd.obterUsers();
    }


    @Override
    public List<Grupos> obterListaGrupos() throws RemoteException {
        notifyObservers("Um cliente requisitou a lista de todos os grupos!");
        return bd.listarGruposDB();
    }

    @Override
    public void addObserver(RMIObserverInterface observer) throws RemoteException {
        observers.add(observer);
        System.out.println("Novo observador adicionado.");
    }

    @Override
    public void removeObserver(RMIObserverInterface observer) throws RemoteException {
        observers.remove(observer);
        System.out.println("Observador removido.");
    }

    public void notifyObservers(String description) {
        observers.removeIf(observer -> {
            try {
                System.out.println("ADEUS");
                observer.Notification(description);
                return false;
            } catch (RemoteException e) {
                System.out.println("Observador desconectado. Removendo...");
                return true;
            }
        });
    }

}
