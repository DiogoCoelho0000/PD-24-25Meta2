package pt.isec.pd.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import pt.isec.pd.rmi.server.RmiService;
import pt.isec.pd.security.TokenService;

@RestController
public class AuthController {
	private final TokenService tokenService;
	private final RmiService rmiService;

	public AuthController(TokenService tokenService, RmiService rmiService) {
		this.tokenService = tokenService;
        this.rmiService = rmiService;
    }

	@GetMapping("/login")
	public String login(Authentication authentication) {
		rmiService.notifyObservers("O cliente " + authentication.getName() + " fez login!");
		System.out.println(authentication);
		return tokenService.generateToken(authentication);
	}

	@GetMapping("/authorization")
	public String authorization(Authentication authentication) {
		return authentication.getAuthorities().toString();
	}
}
