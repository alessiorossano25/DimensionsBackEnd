package com.progetto.Dimensions.service;

import com.progetto.Dimensions.model.User;
import com.progetto.Dimensions.repository.UserRepository;
import com.progetto.Dimensions.request.AuthenticationRequest;
import com.progetto.Dimensions.request.RegisterRequest;
import com.progetto.Dimensions.response.AuthenticationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

@Autowired
private UserService userService;


    AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User authenticate(AuthenticationRequest auth) {
        try {
            User user = null;
            if (auth.getMailUsername().contains("@")) {
                user = userRepository.findByMail(auth.getMailUsername());
            } else {
                user = userRepository.findByUsername(auth.getMailUsername());
            }

            if (user != null && passwordEncoder.matches(auth.getPassword(), user.getPassword())) {
                return user;

            } else {
                throw new RuntimeException("Credenziali errate");
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Autenticazione fallita");
        }

    }

    public boolean register(RegisterRequest auth) {
        try {

            User user = new User(auth.getName(), auth.getSurname(), auth.getUsername(), auth.getMail(), passwordEncoder.encode(auth.getPassword()));
            user = userRepository.save(user);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public User saveUser (RegisterRequest auth, String username) {
    User user = userService.searchUserByUsername(username);
                user.setName(auth.getName());
                user.setSurname(auth.getSurname());
                user.setUsername(auth.getUsername());
                user.setMail(auth.getMail());
                if(!auth.getPassword().equals("")){
        user.setPassword(passwordEncoder.encode(auth.getPassword()));
    }
                return userRepository.save(user);
    }

}
