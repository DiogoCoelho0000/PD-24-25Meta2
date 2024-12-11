package pt.isec.pd.comum.modelos;

import java.io.Serializable;
import java.util.List;

public class Convites implements Serializable {
    private String nomeRemetente;
    private String grupoNome;
    private String estado;
    private List<Convites> convitesLista;

    public List<Convites> getConvitesLista() {
        return convitesLista;
    }

    public void setConvitesLista(List<Convites> convitesLista) {
        this.convitesLista = convitesLista;
    }

    public String getnomeRemetente() {
        return nomeRemetente;
    }

    public void setnomeRemetente(String nomeRemetente) {
        this.nomeRemetente = nomeRemetente;
    }

    public String getGrupoNome() {
        return grupoNome;
    }

    public void setGrupoNome(String grupoNome) {
        this.grupoNome = grupoNome;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Convites{" +
                " nomeRemetente='" + nomeRemetente + '\'' +
                ", grupoNome='" + grupoNome + '\'' +
                ", estado='" + estado + '\'' +
                '}';
    }
}
