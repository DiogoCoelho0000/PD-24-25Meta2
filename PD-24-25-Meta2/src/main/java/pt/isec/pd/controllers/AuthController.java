package pt.isec.pd.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import pt.isec.pd.security.TokenService;

@RestController
public class AuthController {
	private final TokenService tokenService;

	public AuthController(TokenService tokenService) {
		this.tokenService = tokenService;
    }

	@GetMapping("/login")
	public ResponseEntity<String> login(Authentication authentication) {
		if (authentication == null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais inválidas");
		}
		return ResponseEntity.ok(tokenService.generateToken(authentication));
	}


	@GetMapping("/authorization")
	public String authorization(Authentication authentication) {
		return authentication.getAuthorities().toString();
	}
}
