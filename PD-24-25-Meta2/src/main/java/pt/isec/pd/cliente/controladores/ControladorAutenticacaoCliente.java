package pt.isec.pd.cliente.controladores;

import pt.isec.pd.cliente.ligacao.Ligacao;
import pt.isec.pd.comum.modelos.User;
import pt.isec.pd.comum.modelos.mensagens.EditaConta;
import pt.isec.pd.comum.modelos.mensagens.Login;
import pt.isec.pd.comum.modelos.Mensagem;
import pt.isec.pd.comum.modelos.mensagens.Registo;
import pt.isec.pd.comum.enumeracoes.Tipomensagemenum;

public class ControladorAutenticacaoCliente {
    public static void registo(Ligacao ligacao, String []dados/*User user*/){
        //Registo registo = new Registo(user.getEmail(), user.getPassword(), user.getnTelefone(), user.getNome());
        Registo registo = new Registo(dados[0],dados[2],Integer.parseInt(dados[3]),dados[1]);
        Mensagem mensagem = new Mensagem(Tipomensagemenum.USER_REGISTO, registo);
        ligacao.enviaMensagem(mensagem);
    }
    public static void login(Ligacao ligacao, String [] dados){
        Login login = new Login(dados[0],dados[1]);
        Mensagem mensagem = new Mensagem(Tipomensagemenum.LOGIN,login);
        ligacao.enviaMensagem(mensagem);
    }
    public static void edita(Ligacao ligacao, Integer telf, String email, String pass){
        EditaConta editaConta = new EditaConta(email,pass,telf);
        Mensagem mensagem = new Mensagem(Tipomensagemenum.USER_EDITA_INFORMACAO, editaConta);
        ligacao.enviaMensagem(mensagem);
    }

}
