package project.workouter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.RequestBody;
import project.workouter.model.TokenResponse;
import project.workouter.model.User;
import project.workouter.service.TokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import project.workouter.service.UserService;
/**
 * Klasa odpowiedzialna za endpoint wystawiający token użytkownika
 */
@CrossOrigin
@RestController
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;
    private static final Logger LOG = LoggerFactory.getLogger(AuthController.class);

    private final TokenService tokenService;

    public AuthController(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    /**
     * Po zalogowaniu w odpowiedzi użytkownik dostaje JWT token
     */
    @PostMapping("/auth/token")
    public TokenResponse token(@RequestBody User user){
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
            );
            String token = tokenService.generateToken(authentication);
            return new TokenResponse(token, token);
        } catch (AuthenticationException e) {
            throw new RuntimeException("Invalid login credentials");
        }
    }
}