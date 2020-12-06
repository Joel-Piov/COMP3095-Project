package com.assignment2.COMP3095.services;

import com.assignment2.COMP3095.models.Card;
import com.assignment2.COMP3095.repo.CardRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardService {

    @Autowired
    private CardRepo repo;

    public List<Card> listAll(int userId){
        return (List<Card>) repo.findAll();
    }

    public void save(Card client){
        repo.save(client);
    }

    public Card get(int cardId){ return repo.findById(cardId).get(); }

    public Card findByCardNumber(String cardNumber) {return repo.findByCardNumber(cardNumber);}

    public void delete(int id){
        repo.deleteById(id);
    }


}
