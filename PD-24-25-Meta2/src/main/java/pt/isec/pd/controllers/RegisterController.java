package pt.isec.pd.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;
import pt.isec.pd.db.Bd;
import pt.isec.pd.models.RegisterRequest;

@RestController
@RequestMapping("/register")
public class RegisterController {
    private final AuthenticationManager authenticationManager;

    public RegisterController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }
    @PostMapping
    public ResponseEntity<String> register(@RequestBody RegisterRequest registerRequest) {
        // Verifica se os campos obrigatórios não estão nulos ou vazios
        if (registerRequest.getUsername() == null || registerRequest.getUsername().isEmpty()) {
            return ResponseEntity.badRequest().body("O nome de usuário é obrigatório.");
        }

        if (registerRequest.getEmail() == null || registerRequest.getEmail().isEmpty()) {
            return ResponseEntity.badRequest().body("O e-mail é obrigatório.");
        }

        if (registerRequest.getPassword() == null || registerRequest.getPassword().isEmpty()) {
            return ResponseEntity.badRequest().body("A senha é obrigatória.");
        }

        Bd.ligaBD("Base_de_dados");

        // Verifica se o e-mail já está registrado
        boolean emailExiste = Bd.verificarEmailExistente(registerRequest.getEmail());
        if (emailExiste) {
            return ResponseEntity.badRequest().body("O e-mail já está em uso.");
        }

        boolean userCreated = Bd.setUserDB(
                registerRequest.getUsername(),
                registerRequest.getnTelefone(),
                registerRequest.getEmail(),
                registerRequest.getPassword()
        );

        if (userCreated) {
            return ResponseEntity.ok("Registo efetuado com sucesso");
        } else {
            return ResponseEntity.badRequest().body("Erro ao registrar o usuário");
        }
    }


/*    @PostMapping
    public ResponseEntity<String> register(@RequestBody RegisterRequest registerRequest) {
        System.out.println("REGISTO");
        Bd.ligaBD("Base_de_dados");

        boolean userCreated = Bd.setUserDB(
                registerRequest.getUsername(),
                registerRequest.getnTelefone(),
                registerRequest.getEmail(),
                registerRequest.getPassword()
        );

        if (userCreated) {
            return ResponseEntity.ok("Registo efectuado com sucesso");
        } else {
            return ResponseEntity.badRequest().body("O utilizador ja existe");
        }
    }*/

}
