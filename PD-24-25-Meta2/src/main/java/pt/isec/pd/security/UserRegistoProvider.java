package pt.isec.pd.security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import pt.isec.pd.db.Bd;
import pt.isec.pd.models.RegisterRequest;

@Component
public class UserRegistoProvider implements AuthenticationProvider {

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        System.out.println("REGISTO2");
        Bd.ligaBD("Base_de_dados");

        boolean success = Bd.setUserDB("manel",123456789,username,password);

        if (success) {

            Bd.desligaBD("Base_de_dados");
            return new UsernamePasswordAuthenticationToken(username, password, null);
        }
        Bd.desligaBD("Base_de_dados");
        return authentication;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

}
