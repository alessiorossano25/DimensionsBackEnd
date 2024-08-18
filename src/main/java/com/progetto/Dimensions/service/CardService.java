package com.progetto.Dimensions.service;

import com.progetto.Dimensions.model.Card;
import com.progetto.Dimensions.model.User;
import com.progetto.Dimensions.repository.CardRepository;
import com.progetto.Dimensions.response.AllCardResponse;
import com.progetto.Dimensions.response.CardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service

public class CardService {
    @Autowired
    private CardRepository cardRepository;

    public List<AllCardResponse> cards (){
        List<AllCardResponse> cards = cardRepository.findAll().stream()
                .map(card -> new AllCardResponse(card.getId(), card.getName(),card.getNumbercard(),card.getImg(),card.getExpansion().getId(), card.getExpansion().getName()))
                .collect(Collectors.toList());
        return cards;
    }

    public List<CardResponse> cardsByUsers (Long id_user){
        List<Card> cards = cardRepository.findAll();
        List<CardResponse> cardsResponse = new ArrayList<>();
        for (Card card : cards){
            if (card.getUsers().stream()
                    .map(User::getId)
                    .collect(Collectors.toList()).contains(id_user)) {
                cardsResponse.add(new CardResponse(card.getId(), card.getName(),card.getNumbercard(),card.getImg(),card.getExpansion().getId(), card.getExpansion().getName(),true));
            }
            else{
                cardsResponse.add(new CardResponse(card.getId(), card.getName(),card.getNumbercard(),card.getImg(),card.getExpansion().getId(), card.getExpansion().getName(),false));        }
        }

        return cardsResponse;
    }
}
