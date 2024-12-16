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
    private Bd bd;  // Conexão com a BD
    private static String nomeUser = null;
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
        boolean resultado = bd.setUserDB(registo.getNome(), registo.getnTelefone(), registo.getEmail(), registo.getPassword());
        if (resultado) {
            // Notifique os observadores apenas se o registro for bem-sucedido
            notifyObservers("Cliente " + registo.getNome() + " registado com sucesso!");
        } else {
            // Se houver erro, também notifique os observadores
            notifyObservers("Erro ao registar o cliente!");
        }

        return resultado;

    }

    @Override
    public boolean autenticarUtilizador(Login login) throws RemoteException {
        // Utilize a classe Bd para verificar a autenticação do usuário
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
    public List<Grupos> obterListaGrupos(String email) throws RemoteException {
        System.out.println(email);
        notifyObservers("Cliente " + nomeUser + " requisitou a lista de utilizadores!");
        return bd.listarGruposDB(email);
    }

    @Override
    public boolean inserirDespesa(Despesa despesa) throws RemoteException {
        boolean resultado =  bd.criaDespesa(despesa.getGrupo(), despesa, despesa.getEmail()); // Passando diretamente o objeto Despesa

        if (resultado){
            notifyObservers("Cliente " + nomeUser + " inseriu uma despesa com sussesso!");
        }
        else {
            notifyObservers("Cliente " + nomeUser + " erro ao inserir despesa");
        }
        return resultado;
    }

    @Override
    public boolean eliminarDespesa(Despesa despesa) throws RemoteException {
        System.out.println(despesa.getEmail()+despesa.getGrupo()+despesa.getIdDespesa());
        boolean resultado =  bd.eliminarDespesa(despesa.getEmail(), despesa.getGrupo(), despesa.getIdDespesa());

        if (resultado){
            notifyObservers("Cliente " + nomeUser + " eliminou uma despesa com sussesso!");
        }
        else {
            notifyObservers("Cliente " + nomeUser + " erro ao eliminar uma despesa");
        }
        return resultado;
    }

    // Observadores
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
