package pt.isec.pd.security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import pt.isec.pd.db.Bd;

@Component
public class UserAuthenticationProvider implements AuthenticationProvider {

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username = authentication.getName();
		String password = authentication.getCredentials().toString();
		Bd.ligaBD("Base_de_dados");

		if (Bd.getUserDB(username,password)) {
			Bd.desligaBD("Base_de_dados");
			return new UsernamePasswordAuthenticationToken(username, password, null);
		}
		Bd.desligaBD("Base_de_dados");
		throw new BadCredentialsException("Utilizador não existe");
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
}
