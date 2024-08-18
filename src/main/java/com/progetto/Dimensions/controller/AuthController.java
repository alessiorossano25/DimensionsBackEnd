package com.progetto.Dimensions.controller;

import com.progetto.Dimensions.model.User;
import com.progetto.Dimensions.request.AuthenticationRequest;
import com.progetto.Dimensions.request.RegisterRequest;
import com.progetto.Dimensions.response.AuthenticationResponse;
import com.progetto.Dimensions.service.AuthService;
import com.progetto.Dimensions.service.JwtService;
import com.progetto.Dimensions.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/auth")
@RestController
public class AuthController {
    @Autowired
    private UserService userService;

    private final JwtService jwtService;

    private final AuthService authService;


    public AuthController(JwtService jwtService, AuthService authService) {
        this.jwtService = jwtService;
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest auth) {
        User user = authService.authenticate(auth);
        String token = jwtService.generateToken(user);
        AuthenticationResponse authenticationResponse = new AuthenticationResponse(token, jwtService.extractExpiration(token).getTime(), user.getUsername(), user.getMail(), user.getImg(), user.getName(), user.getSurname());
        return ResponseEntity.ok(authenticationResponse);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest auth) {
        if (authService.register(auth) == true) {
            return ResponseEntity.ok("Registrazione confermata");
        }
        return ResponseEntity.status(400).body("Problema con la registrazione");
    }

    @PostMapping("/saveUser")
    public ResponseEntity<?> saveUser(@RequestBody RegisterRequest auth, HttpServletRequest httpServletRequest) {
        String header = httpServletRequest.getHeader("Authorization");

        if (header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7);
            boolean notValid = jwtService.isExpired(token);
            if (!notValid) {
                String username = jwtService.extractUsername(token);
                User user = authService.saveUser(auth, username);
                return ResponseEntity.ok().body(new AuthenticationResponse(token, jwtService.extractExpiration(token).getTime(), user.getUsername(), user.getMail(), user.getImg(), user.getName(), user.getSurname()));
            }
        }
        return ResponseEntity.badRequest().body("Non autorizzato");
    }

    @GetMapping("/check")
    public ResponseEntity<?> check(HttpServletRequest httpServletRequest) {
        String header = httpServletRequest.getHeader("Authorization");

        if (header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7);
            boolean notValid = jwtService.isExpired(token);
            if (!notValid) {
                String username = jwtService.extractUsername(token);
                User user = userService.searchUserByUsername(username);
                return ResponseEntity.ok().body(new AuthenticationResponse(token, jwtService.extractExpiration(token).getTime(), user.getUsername(), user.getMail(), user.getImg(), user.getName(), user.getSurname()));
            }
        }
        return ResponseEntity.badRequest().body("Non autorizzato");
    }

    @PostMapping("/changeAvatar")
    public ResponseEntity<?> changeAvatar(HttpServletRequest httpServletRequest, @RequestParam(required = true)MultipartFile file) {
        String header = httpServletRequest.getHeader("Authorization");

        if (header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7);
            boolean notValid = jwtService.isExpired(token);
            if (!notValid) {
                Long id = jwtService.extractUserID(token);
                User user = userService.changeAvatar(id,file);
                return ResponseEntity.ok().body(new AuthenticationResponse(token, jwtService.extractExpiration(token).getTime(), user.getUsername(), user.getMail(), user.getImg(), user.getName(), user.getSurname()));
            }
        }
        return ResponseEntity.badRequest().body("Non autorizzato");
    }
}
