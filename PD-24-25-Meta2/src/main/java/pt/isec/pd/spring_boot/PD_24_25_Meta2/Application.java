package pt.isec.pd.spring_boot.PD_24_25_Meta2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);

		// Iniciar o servidor antigo em uma nova thread
		Thread serverThread = new Thread(() -> {
			// Código do servidor Meta 1
		});
		serverThread.start();
	}
}