package pt.isec.pd.cliente.modelos;

import pt.isec.pd.comum.modelos.Grupos;
import pt.isec.pd.comum.modelos.User;
import java.util.List;


public class Dados {
    private User utilizadorLogado;
    private Grupos GrupoSelecionado;
    List<User> utilizadores;
    private Grupos grupos;

    public Dados(){
        this.utilizadorLogado = null;
    }

    public Grupos getGrupoSelecionado() {
        return GrupoSelecionado;
    }

    public void setGrupoSelecionado(Grupos grupoSelecionado) {
        GrupoSelecionado = grupoSelecionado;
    }

    public Grupos getGrupos() {
        return grupos;
    }

    public void setGrupos(Grupos grupos) {
        this.grupos = grupos;
    }

    public User getUtilizadorLogado(){
        return utilizadorLogado;
    }
    public void setUtilizadorLogado(User utilizadorLogado){
        this.utilizadorLogado = utilizadorLogado;
    }

    public List<User> getUtilizadores(){
        return utilizadores;
    }
    public void setUtilizadores(List<User> conteudo){
        this.utilizadores = conteudo;
    }

    }



