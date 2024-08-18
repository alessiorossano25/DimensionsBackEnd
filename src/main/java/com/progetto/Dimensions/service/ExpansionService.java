package com.progetto.Dimensions.service;


import com.progetto.Dimensions.model.Card;
import com.progetto.Dimensions.model.User;
import com.progetto.Dimensions.repository.CardRepository;
import com.progetto.Dimensions.repository.ExpansionRepository;
import com.progetto.Dimensions.repository.UserRepository;
import com.progetto.Dimensions.request.ColorRequest;
import com.progetto.Dimensions.response.AllCardResponse;
import com.progetto.Dimensions.response.CardResponse;
import com.progetto.Dimensions.response.ExpansionResponse;
import com.progetto.Dimensions.response.UserExpansionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExpansionService {
    @Autowired
    private ExpansionRepository expansionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CardRepository cardRepository;



    public List<ExpansionResponse> expansionFindCards (){
       return expansionRepository.findAll().stream()
               .map(expansion -> new ExpansionResponse(expansion.getId(), expansion.getName(),expansion.getImg(),expansion.getCards()
                       .stream().map(card -> new AllCardResponse (card.getId(), card.getName(),card.getNumbercard(),card.getImg(),card.getExpansion().getId(), card.getExpansion().getName()))
                       .collect(Collectors.toList())))
               .collect(Collectors.toList());
    }

    public List<UserExpansionResponse> expansionFindCards (Long id){
        return expansionRepository.findAll().stream()
                .map(expansion -> new UserExpansionResponse(expansion.getId(), expansion.getName(),expansion.getImg(),expansion.getCards()
                        .stream()
                        .map(card -> new CardResponse(card.getId(), card.getName(),card.getNumbercard(),card.getImg(),card.getExpansion().getId(), card.getExpansion().getName(),card.getUsers()
                                .stream()
                                .map(user -> user.getId())
                                .collect(Collectors.toList())
                                .contains(id)))
                        .collect(Collectors.toList())))
                .collect(Collectors.toList());
    }


    public boolean colorCards (ColorRequest colorRequest, Long idUser){
    User user = userRepository.findById(idUser).orElseThrow(() -> new RuntimeException("Utente non trovato"));
    Card card = cardRepository.findById(colorRequest.getIdCard()).orElseThrow(() -> new RuntimeException("Carta non trovata"));
    if(colorRequest.isColor()){
        user.getCards().add(card);
    }
    else {
        user.getCards().remove(card);
    }
    userRepository.save(user);
    return true;
    }


}
