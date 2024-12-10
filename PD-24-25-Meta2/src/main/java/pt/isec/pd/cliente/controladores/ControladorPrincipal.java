package pt.isec.pd.cliente.controladores;


import pt.isec.pd.cliente.ligacao.Ligacao;
import pt.isec.pd.cliente.modelos.Dados;

import pt.isec.pd.comum.modelos.*;

import java.util.List;

public class ControladorPrincipal {

    static String nome;
    static String password;
    static String email;
    static String nTelefoneString;
    static int nTelefone;


    static Ligacao ligacao;

    static Dados dados;
    private User userAutenticado;

     public ControladorPrincipal(Ligacao ligacao){
        this.ligacao = ligacao;

        this.dados = new Dados();
    }

    public List<User> getUsers(){
        return dados.getUtilizadores();
    }

    public boolean login(String email, String password) {
        String[] dadosLogin = {email, password};
        ControladorAutenticacaoCliente.login(ligacao, dadosLogin);
        recebeMensagem();

        User usuarioLogado = dados.getUtilizadorLogado();
        if (usuarioLogado != null) {
            this.userAutenticado = usuarioLogado;
            return true;
        }
        return false;
    }


    public void logout() {
        userAutenticado = null;
        dados.setUtilizadorLogado(null);
    }

    public boolean isUserAuthenticated() {
        return userAutenticado != null;
    }


    public int registo(String nome, String email, String password, String nTelefone) {



        if (!email.contains("@") && !email.contains(".com")) { //obriga o Cliente a introduzir o email no formato completo
            return 0;
        }

        try {//obriga o user a meter um número válido
            if (nTelefone.length() != 9) { //obriga o user a meter 9 dígitos
                Integer.parseInt(nTelefone); // para gerar exceção
                System.out.println("O número de telefone deve conter 9 dígitos.");
                return 1;
            }
        } catch (NumberFormatException e) {

            System.out.println("O número do telefone deve conter apenas números.");
            return 2;
        }
        String[] dadosRegisto = {email, nome, password, nTelefone};
        ControladorAutenticacaoCliente.registo(ligacao, dadosRegisto);
        recebeMensagem();
        return 3;
    }

    public void editaDados(String numeroTelefone, String password){
        Integer Telf = null;
        if (!numeroTelefone.isEmpty())
            try {
                Telf = Integer.parseInt(numeroTelefone);
            } catch (NumberFormatException e) {
                throw new RuntimeException(e);
            }

        ControladorAutenticacaoCliente.edita(ligacao, Telf, dados.getUtilizadorLogado().getEmail(), password);
        recebeMensagem();
    }

    public void criarGrupo(String nomeGrupo){
        ControladorGrupoCliente.criaGrupo(ligacao, nomeGrupo, dados.getUtilizadorLogado().getEmail());
        if(!recebeMensagem().toString().equals("ERRO_GRUPO")) {
            ControladorGrupoCliente.insereGrupo(ligacao, nomeGrupo, dados.getUtilizadorLogado().getEmail());
            recebeMensagem();
        }
    }


    public String selecionarGrupo(String nomeGrupo){
        ControladorGrupoCliente.selecionaGrupo(ligacao, dados.getUtilizadorLogado().getEmail(), nomeGrupo);
        recebeMensagem();
        return dados.getGrupoSelecionado().getNomeGrupo();
    }


    public Grupos listarGrupos(){

        ControladorGrupoCliente.listarGrupos(ligacao, dados.getUtilizadorLogado().getEmail());
        return (Grupos) recebeMensagem();
    }

    public void insereDespesa(String grupoSelecionado, double despesa, String quemPagou, String descricao, String data){
        ControladorDespesaCliente.inserirDespesa(ligacao, dados.getUtilizadorLogado().getEmail(), grupoSelecionado, despesa, quemPagou, descricao, data);
        recebeMensagem();
    }

    public Despesa mostrarDespesas(String grupoSelecionado){
        ControladorDespesaCliente.historicoDespesa(ligacao,grupoSelecionado);
        return (Despesa) recebeMensagem();

    }

   public static Object recebeMensagem(){

        RespostaServidorMensagem resposta = ligacao.recebeMensagem();
        System.out.println("Recebe Mensagem -> "+resposta);
        try {
            switch (resposta.getEstado()){
                case USER_LOGADO_COM_SUCESSO -> {
                    dados.setUtilizadorLogado((User) resposta.getConteudo());
                    return resposta.getEstado();
                }

               case USER_REGISTADO_COM_SUCESSO -> {


                }
                case ERRO_GRUPO -> {
                    return resposta.getEstado();
                }
                case USER_GRUPO_SELECIONADO_COM_SUCESSO -> {
                    dados.setGrupoSelecionado((Grupos) resposta.getConteudo());
                    return resposta.getEstado();
                }
                case CONSULTA_DESPESA_TOTAL_COM_SUCESSO -> {
                    String total;
                    total = (String) resposta.getConteudo();
                    return total;
                }
                case USER_CRIA_DESPESA_COM_SUCESSO -> {
                    System.out.println(" HELLO DESPESA " + resposta.getEstado());
                    return resposta.getEstado();
                }

                case GRUPO_USER_INSERIDO_COM_SUCESSO ->{
                    System.out.println(" HELLO INTEGRA "+resposta.getEstado());
                    return  resposta.getEstado();
                }
                case GRUPO_REGISTADO_COM_SUCESSO ->{
                    System.out.println(" HELLO REGISTO"+resposta.getEstado());
                    return  resposta.getEstado();
                }

                case GRUPO_LISTADO_COM_SUCESSO -> {
                    Grupos grupos = (Grupos) resposta.getConteudo();
                    return grupos;

                }
                case USER_OBTEM_HISTORICO_DESPESA_COM_SUCESSO ->{

                    Despesa despesa = (Despesa) resposta.getConteudo();
                    return despesa;
                }

            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return resposta.getEstado();

    }


}
