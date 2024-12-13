package pt.isec.pd;

import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.web.SecurityFilterChain;
import pt.isec.pd.db.Bd;
import pt.isec.pd.security.RsaKeysProperties;
import pt.isec.pd.security.UserAuthenticationProvider;
import pt.isec.pd.security.UserRegistoProvider;

@SpringBootApplication
@ConfigurationPropertiesScan
public class Application {
	public static String resourceDirectory;
	private final RsaKeysProperties rsaKeys;

	public Application(RsaKeysProperties rsaKeys) {
		this.rsaKeys = rsaKeys;
	}

	public static void main(String[] args) {
		if(args.length < 1){
			System.out.println("Must provide at least one argument in the command line: " +
					"path to the directory where data and image files are located ");
		}

		resourceDirectory = args[0];
		SpringApplication.run(Application.class, args);
		Bd.ligaBD("Base_de_dados");
	}

	@Bean
	JwtEncoder jetEncoder() {
		JWK jwK = new RSAKey.Builder(rsaKeys.publicKey()).privateKey(rsaKeys.privateKey()).build();
		JWKSource<SecurityContext> jwkSource = new ImmutableJWKSet<>(new JWKSet(jwK));
		return new NimbusJwtEncoder(jwkSource);
	}

	@Bean
	JwtDecoder jetDecoder() {
		return NimbusJwtDecoder.withPublicKey(rsaKeys.publicKey()).build();
	}
}
