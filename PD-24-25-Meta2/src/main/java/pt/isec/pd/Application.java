package pt.isec.pd;

import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import pt.isec.pd.db.Bd;
import pt.isec.pd.rmi.server.RMIServiceLauncher;
import pt.isec.pd.security.RsaKeysProperties;

import pt.isec.pd.rmi.server.RMIServiceLauncher;

@SpringBootApplication
@ConfigurationPropertiesScan
public class Application {
	public static String resourceDirectory;
	private final RsaKeysProperties rsaKeys;
	private final RMIServiceLauncher rmiServiceLauncher;

	public Application(RsaKeysProperties rsaKeys, RMIServiceLauncher rmiServiceLauncher) {
		this.rsaKeys = rsaKeys;
		this.rmiServiceLauncher = rmiServiceLauncher;
	}

	public static void main(String[] args) {
		if (args.length < 1) {
			System.out.println("Must provide at least one argument in the command line: " +
					"path to the directory where data and image files are located ");
		}

		resourceDirectory = args[0];
		SpringApplication.run(Application.class, args);

		// Conectar ao banco de dados
		Bd.ligaBD("Base_de_dados");
	}

	@PostConstruct
	public void startRMI() {
		try {
			rmiServiceLauncher.start(); // Inicia o serviço RMI
			System.out.println("RMI Service iniciado com sucesso.");
		} catch (Exception e) {
			System.err.println("Erro ao iniciar o serviço RMI: " + e.getMessage());
			e.printStackTrace();
		}
	}

	@Bean
	JwtEncoder jwtEncoder() {
		JWK jwK = new RSAKey.Builder(rsaKeys.publicKey()).privateKey(rsaKeys.privateKey()).build();
		JWKSource<SecurityContext> jwkSource = new ImmutableJWKSet<>(new JWKSet(jwK));
		return new NimbusJwtEncoder(jwkSource);
	}

	@Bean
	JwtDecoder jwtDecoder() {
		return NimbusJwtDecoder.withPublicKey(rsaKeys.publicKey()).build();
	}
}