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
    List<User> obterListaUsers() throws RemoteException;  // Retorna a lista de usuários
   // List<Grupos> obterListaGrupos(String email) throws RemoteException;  // Retorna os grupos associados a um usuário

    List<Grupos> obterListaGrupos() throws RemoteException;

    // Operações com Despesas
    boolean inserirDespesa(Despesa criaDespesa) throws RemoteException;
    boolean eliminarDespesa(Despesa eliminaDespesa) throws RemoteException;

    // Método para adicionar observadores
    void addObserver(RMIObserverInterface observer) throws RemoteException;
    void removeObserver(RMIObserverInterface observer) throws RemoteException;
}
