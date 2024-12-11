package pt.isec.pd.comum.enumeracoes;


import java.io.Serializable;

public enum Estados {

    USER_LOGADO_COM_SUCESSO("Utilizador logado com sucesso"),
    USER_REGISTADO_COM_SUCESSO("Utilizador registado com sucesso"),
    USER_REMOVIDO_COM_SUCESSO("User removido do Grupo com sucesso"),
    USER_MODIFICADO_COM_SUCESSO("User modificado com sucesso"),
    USER_PAGAMENTO_INSERIDO_COM_SUCESSO("Utilizador inseriu o pagamento com sucesso"),
    USER_TEM_DIVIDAS("Utilizador tem dividas no grupo, não pode sair do grupo até as dividas estarem saudadas"),
    USER_GRUPO_SELECIONADO_COM_SUCESSO("Grupo selecionado com sucesso"),

    GRUPO_REGISTADO_COM_SUCESSO("Grupo registado com sucesso"),
    GRUPO_USER_INSERIDO_COM_SUCESSO("Utilizador Inserido com Sucesso"),
    GRUPO_LISTADO_COM_SUCESSO("Grupo Listado com sucesso"),


    PAGAMENTO_LISTADO_COM_SUCESSO("Pagamento Listado com sucesso"),

    PAGAMENTO_ELIMINADO_COM_SUCESSO("Pagamento Eliminado com sucesso"),
    GRUPO_NOME_ALTERADO_COM_SUCESSO("Nome do Grupo Alterado com Sucesso"),
    GRUPO_ELIMINADO_COM_SUCESSO("Grupo Eliminado com sucesso"),
    GRUPO_CONVITE_COM_SUCESSO("Convite creado com sucesso"),
    VER_CONVITES_COM_SUCESSO("Convites encontrados"),
    GRUPO_ACEITE_CONVITE_COM_SUCESSO("Convite aceite com sucesso"),


    USER_CRIA_DESPESA_COM_SUCESSO("Utilizador cria despesa com sucesso"),
    USER_EDITA_DESPESA_COM_SUCESSO("Utilizador edita despesa com sucesso"),
    CONSULTA_DESPESA_TOTAL_COM_SUCESSO("Consulta da despesa com sucesso"),
    USER_EXPORTA_COM_SUCESSO("Ficheiro CSV exportado com sucesso"),
    USER_OBTEM_HISTORICO_DESPESA_COM_SUCESSO("Utilizador obtem histórico com sucesso"),
    DESPESA_ELIMINADA_COM_SUCESSO("Despesa eliminada com sucesso"),

    ERRO_AUTENTICACAO("O Utilizador não existe"),
    ERRO_EDITAR_USER("Erro ao atualizar os dados do utilizador"),
    ERRO_REGISTO("O Utilizador já existe"),
    ERRO_GRUPO_NAO_ENCONTRADO("Grupo nao existe"),
    ERRO_AO_SELECIONAR_GRUPO("Não pertence ao grupo"),

    ERRO_USER_NAO_PERTENCE_GRUPO("User nao pertence a esse grupo"),
    ERRO_SEM_GRUPOS("Não está em nenhum grupo"),
    ERRO_GRUPO("Grupo já existe"),
    ERRO_CRIA_CONVITE("Convite já existente"),
    ERRO_ACEITAR_CONVITE("Convite não existe"),
    ERRO_VER_CONVITES("Sem convites"),
    ERRO_INSERIR_PAGAMENTO("Inseriu Pagamento Incorretamente"),
    ERRO_CRIAR_DESPESA("ERRO AO CRIAR DESPESA"),
    ERRO_OBTER_HISTORICO("ERRO grupo não tem histórico para mostrar"),
    ERRO_CONSULTA_DESPESA_TOTAL("ERRO AO CONSULTAR VALOR DA DESPESA TOTAL"),
    ERRO_ELIMINAR_DESPESA("Erro ao eliminar Despesa"),
    ERRO_LISTAR_PAGAMENTO("Erro Listar Pagamento"),

    ERRO_ELIMINAR_PAGAMENTO("Erro Eliminar Pagamento"),

    ERRO_SEM_PAGAMENTOS("Nao Existem Pagamentos"),
    ERRO_AO_EXPORTAR_CSV("ERAR O FICHEIRO CSV"),
    ERRO_EDITAR_DESPESA("Erro ao editar Despesa"),
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
