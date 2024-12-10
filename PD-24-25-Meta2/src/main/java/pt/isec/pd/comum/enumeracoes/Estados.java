package pt.isec.pd.comum.enumeracoes;


import java.io.Serializable;

public enum Estados {

    USER_LOGADO_COM_SUCESSO("Utilizador logado com sucesso"),
    USER_REGISTADO_COM_SUCESSO("Utilizador registado com sucesso"),
    USER_REMOVIDO_COM_SUCESSO("User removido do Grupo com sucesso"),
    USER_MODIFICADO_COM_SUCESSO("User modificado com sucesso"),
    USER_PAGAMENTO_INSERIDO_COM_SUCESSO("Utilizador inseriu o pagamento com sucesso"),
    USER_GRUPO_SELECIONADO_COM_SUCESSO("Grupo selecionado com sucesso"),

    GRUPO_REGISTADO_COM_SUCESSO("Grupo registado com sucesso"),
    GRUPO_USER_INSERIDO_COM_SUCESSO("Utilizador Inserido com Sucesso"),
    GRUPO_LISTADO_COM_SUCESSO("Grupo Listado com sucesso"),


    USER_CRIA_DESPESA_COM_SUCESSO("Utilizador cria despesa com sucesso"),
    USER_OBTEM_HISTORICO_DESPESA_COM_SUCESSO("Utilizador obtem histórico com sucesso"),
    ERRO_AUTENTICACAO("O Utilizador não existe"),
    ERRO_EDITAR_USER("Erro ao atualizar os dados do utilizador"),
    ERRO_REGISTO("O Utilizador já existe"),
    ERRO_GRUPO_NAO_ENCONTRADO("Grupo nao existe"),
    ERRO_AO_SELECIONAR_GRUPO("Não pertence ao grupo"),

    ERRO_USER_NAO_PERTENCE_GRUPO("User nao pertence a esse grupo"),
    ERRO_SEM_GRUPOS("Não está em nenhum grupo"),
    ERRO_GRUPO("Grupo já existe"),
    ERRO_CRIAR_DESPESA("ERRO AO CRIAR DESPESA"),
    ERRO_OBTER_HISTORICO("ERRO grupo não tem histórico para mostrar"),
    ;
    String mensagem;
    Serializable dados;

    Estados(String s, Serializable dados) {
        this.mensagem = s;
        this.dados = dados;
    }
    Estados(String s){
        this(s,null);
    }

    public String getMensagem(){
        return mensagem;
    }

    public Serializable getDados(){
        return dados;
    }

    public Estados setDados(Serializable dados){
        this.dados = dados;
        return this;
    }

}
