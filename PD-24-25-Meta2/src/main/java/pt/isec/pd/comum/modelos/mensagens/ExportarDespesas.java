package pt.isec.pd.comum.modelos.mensagens;

import java.io.Serializable;

public class ExportarDespesas implements Serializable {
    private String grupo;
    private String nome;

    public ExportarDespesas(String grupo, String nome) {
        this.grupo = grupo;
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }
}
