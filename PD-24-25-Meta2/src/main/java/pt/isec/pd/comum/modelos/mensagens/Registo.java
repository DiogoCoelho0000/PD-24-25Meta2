package pt.isec.pd.comum.modelos.mensagens;

import java.io.Serializable;

public class Registo implements Serializable {
    private String email;
    private String password;
    private String nome;
    private int nTelefone;

    public Registo(String email,String password, int nTelefone, String nome){
        this.email = email;
        this.nome = nome;
        this.password = password;
        this.nTelefone = nTelefone;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getNome() {
        return nome;
    }

    public int getnTelefone() {
        return nTelefone;
    }
}
