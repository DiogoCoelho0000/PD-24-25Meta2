package pt.isec.pd.models;

import java.io.Serializable;

public class User implements Serializable {
    private int id;
    private int nTelefone;
    private String email;
    private String password;
    private String nome;
    private boolean estado;

    // Construtor
    public User(int id, String nome, String email, String password, int nTelefone) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.password = password;
        this.nTelefone = nTelefone;
    }

    // Getter e Setter para o ID
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getnTelefone() {
        return nTelefone;
    }

    public void setnTelefone(int nTelefone) {
        this.nTelefone = nTelefone;
    }

    // Getter e Setter para o Email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Getter e Setter para a Senha
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Getter e Setter para o Nome
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    // Getter e Setter para o Estado (Ativo/Inativo)
    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    // Override do método toString
    @Override
    public String toString() {
        return "Nome: " + nome + "\n" +
                "Email: " + email + "\n" +
                "Password: " + password + "\n" +
                "Número de telefone: " + nTelefone + "\n" +
                "Estado: " + estado;
    }
}
