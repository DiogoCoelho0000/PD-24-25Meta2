package pt.isec.pd.comum.modelos.mensagens;

import java.io.Serializable;

public class EliminaGrupo implements Serializable {
    private String nome;
    private String eliminadoPor;

    public EliminaGrupo(String nome, String eliminadoPor) {
        this.nome = nome;
        this.eliminadoPor = eliminadoPor;
    }

    public String getEliminadoPor() {
        return eliminadoPor;
    }

    public void setEliminadoPor(String eliminadoPor) {
        this.eliminadoPor = eliminadoPor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


    @Override
    public String toString() {
        return "EliminaGrupo{" +
                "nome='" + nome + '\'' +
                ", eliminadoPor='" + eliminadoPor + '\'' +
                '}';
    }
}
