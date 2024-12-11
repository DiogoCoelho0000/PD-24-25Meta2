package pt.isec.pd.controllers;

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
	public String login(Authentication authentication) {
		System.out.println(authentication);
		return tokenService.generateToken(authentication);
	}

	@GetMapping("/authorization")
	public String authorization(Authentication authentication) {
		return authentication.getAuthorities().toString();
	}
}
