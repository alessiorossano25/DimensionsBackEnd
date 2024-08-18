package com.progetto.Dimensions.controller;

import com.progetto.Dimensions.model.Card;
import com.progetto.Dimensions.model.User;
import com.progetto.Dimensions.repository.CardRepository;
import com.progetto.Dimensions.request.AuthenticationRequest;
import com.progetto.Dimensions.response.AllCardResponse;
import com.progetto.Dimensions.response.AuthenticationResponse;
import com.progetto.Dimensions.response.CardResponse;
import com.progetto.Dimensions.service.CardService;
import com.progetto.Dimensions.service.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@CrossOrigin(origins="*",maxAge = 3600)
@RequestMapping("/cards")
@RestController
public class CardController {
@Autowired
private CardService cardService;

    @Autowired
    private JwtService jwtService;


    @GetMapping("/cardsAll")
    public ResponseEntity <List<AllCardResponse>> cards(){
    return ResponseEntity.ok(cardService.cards());
    }

    @GetMapping("/collection")
    public ResponseEntity <List<CardResponse>> cards(HttpServletRequest httpServletRequest){
        String header = httpServletRequest.getHeader("Authorization");

        if(header != null && header.startsWith("Bearer ")){
            String token = header.substring(7);
            Long userID = jwtService.extractUserID(token);
            return ResponseEntity.ok(cardService.cardsByUsers(userID));
        }
        return ResponseEntity.badRequest().body(new ArrayList<>());
    }
}
