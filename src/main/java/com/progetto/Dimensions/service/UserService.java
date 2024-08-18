package com.progetto.Dimensions.service;


import com.progetto.Dimensions.model.User;
import com.progetto.Dimensions.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class UserService implements UserDetailsService  {
    @Autowired
    private UserRepository userRepository;

    private String DIR = "C:\\Users\\aless\\Videos\\vscode progetti\\Dimensions\\client\\public\\assets\\Avatar\\";
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null){
            throw new UsernameNotFoundException("Utente non trovato");
        }
        return user;
    }

    public UserDetails loadUserByMail(String mail) throws UsernameNotFoundException {
        User user = userRepository.findByMail(mail);
        if (user == null){
            throw new UsernameNotFoundException("Utente non trovato");
        }
        return user;
    }

    public User searchUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null){
            throw new UsernameNotFoundException("Utente non trovato");
        }
        return user;
    }


public User changeAvatar (Long id, MultipartFile file) throws UsernameNotFoundException {
    User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("Utente non trovato"));
    String img = "";
    if(file != null){
        img = file.getOriginalFilename();
        Path path = Paths.get(DIR + img);
        try {
            Files.write(path,file.getBytes());
        }catch (IOException ioException){
            ioException.printStackTrace();
            return null;
        }

    }
    user.setImg(img);
    userRepository.save(user);
    return user;
}
}
