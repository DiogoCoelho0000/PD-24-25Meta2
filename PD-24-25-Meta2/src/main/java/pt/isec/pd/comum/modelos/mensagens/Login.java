package pt.isec.pd.comum.modelos.mensagens;

import java.io.Serializable;
//O que eu tenho de enviar para o servidor...
public class Login implements Serializable {
    private final String email;
    private final String password;

    public Login(String email, String password){
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }
    public String getPassword(){
        return password;
    }

    @Override
    public String toString() {
        return "Login{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
