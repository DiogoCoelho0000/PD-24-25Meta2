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
    public ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest) {
        // Implementar o processo de criação de usuário
        try {
            // Lógica para registrar o usuário
            Bd.ligaBD("Base_de_dados");

            boolean userCreated = Bd.setUserDB(
                    registerRequest.getUsername(),
                    registerRequest.getnTelefone(),
                    registerRequest.getEmail(),
                    registerRequest.getPassword()
            );

            if (userCreated) {
                return ResponseEntity.ok("Registo efetuado com sucesso");
            } else {
                return ResponseEntity.badRequest().body("O utilizador já existe");
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro ao registrar o usuário");
        }
    }

}
