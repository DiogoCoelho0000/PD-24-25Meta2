package pt.isec.pd.models;

import java.io.Serializable;
import java.util.List;

public class Grupos implements Serializable {
    private static final long serialVersionUID = 1L;
    private String nomeGrupo;


    public String getNomeGrupo() {
        return nomeGrupo;
    }

    public void setNomeGrupo(String nomeGrupo) {
        this.nomeGrupo = nomeGrupo;
    }

    @Override
    public String toString() {
        return "Grupo{" +
                ", nomeGrupo='" + nomeGrupo + '\'' +
                '}';
    }
}
