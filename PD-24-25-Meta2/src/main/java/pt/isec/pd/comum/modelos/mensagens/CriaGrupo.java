package pt.isec.pd.comum.modelos.mensagens;

import java.io.Serializable;

public class CriaGrupo implements Serializable {
    private String nome;
    private String criadoPor;

    public CriaGrupo(String nome, String criadoPor) {
        this.nome = nome;
        this.criadoPor = criadoPor;
    }

    public String getCriadoPor() {
        return criadoPor;
    }

    public void setCriadoPor(String criadoPor) {
        this.criadoPor = criadoPor;
    }

    public CriaGrupo(String nome){
        this.nome = nome;
    }
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "CriaGrupo{" +
                "nome='" + nome + '\'' +
                ", criadoPor='" + criadoPor + '\'' +
                '}';
    }
}
