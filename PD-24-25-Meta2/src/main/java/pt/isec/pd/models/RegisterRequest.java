package pt.isec.pd.models;

public class RegisterRequest {
    private String username;
    private int nTelefone;
    private String email;
    private String password;


    public int getnTelefone() {
        return nTelefone;
    }

    public void setnTelefone(int nTelefone) {
        this.nTelefone = nTelefone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}