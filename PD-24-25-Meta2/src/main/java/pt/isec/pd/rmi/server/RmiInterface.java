package pt.isec.pd.rmi.server;

import pt.isec.pd.comum.modelos.mensagens.*;
import pt.isec.pd.models.Despesa;
import pt.isec.pd.models.Grupos;
import pt.isec.pd.models.User;
import pt.isec.pd.rmi.observer.RMIObserverInterface;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface RmiInterface extends Remote {

    // Registo e Autenticação
    boolean registarUtilizador(Registo registo) throws RemoteException;
    boolean autenticarUtilizador(Login login) throws RemoteException;

    // Lista de Usuários e Grupos
    List<User> obterListaUsers() throws RemoteException;

    List<Grupos> obterListaGrupos() throws RemoteException;

    // Método para adicionar observadores
    void addObserver(RMIObserverInterface observer) throws RemoteException;
    void removeObserver(RMIObserverInterface observer) throws RemoteException;
}
