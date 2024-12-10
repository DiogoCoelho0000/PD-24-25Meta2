package pt.isec.pd.comum.modelos;

import java.io.Serializable;

public class User implements Serializable {
    private int nTelefone;
    private String email;
    private String password;
    private String nome;
    private boolean estado;

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public void setnTelefone(int nTelefone) {

        this.nTelefone = nTelefone;
    }
    public int getnTelefone() {

        return nTelefone;
    }
    public void setEmail(String email) {

        this.email = email;
    }
    public String getEmail() {

        return email;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getPassword() {

        return password;
    }
    public void setNome(String nome) {

        this.nome = nome;
    }
    public String getNome() {

        return nome;
    }

    @Override
    public String toString() {
        return "Nome: " + nome + "\n" +
                "Email: " + email + "\n" +
                "Password: " + password + "\n" +
                "Número de telefone: "+ nTelefone + "\n" +
                "Estaddo: " + estado;
    }
}
