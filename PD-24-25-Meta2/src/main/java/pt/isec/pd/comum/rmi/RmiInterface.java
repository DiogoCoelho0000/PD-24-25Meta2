package pt.isec.pd.comum.rmi;

import pt.isec.pd.comum.modelos.mensagens.*;
import pt.isec.pd.models.Grupos;
import pt.isec.pd.models.User;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface RmiInterface extends Remote {

    // Registo e Autenticação
    boolean registarUtilizador(Registo registo) throws RemoteException;
    boolean autenticarUtilizador(Login login) throws RemoteException;

    List<User> obterListaUsuarios() throws RemoteException;  // Retorna a lista de usuários
    List<Grupos> obterListaGrupos() throws RemoteException;      // Retorna a lista de grupos

    // Operações com Despesas
    boolean inserirDespesa(CriaDespesa criaDespesa) throws RemoteException;
    boolean eliminarDespesa(EliminaDespesa eliminaDespesa) throws RemoteException;

    // Método para adicionar observadores
    void addObserver(RMIObserverInterface observer) throws RemoteException;

}
