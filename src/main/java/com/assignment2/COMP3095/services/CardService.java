/*********************************************************************************
 * Project: COMP3095 - Assignment 3
 * Assignment: Assignment 3
 * Author(s):       Joel Piovesan     Rachel Titco
 * Student Number:  101221909         101214347
 * Date: 06/12/2020
 * Description: class that provides functions with which to access the Credit Card
 * database and retrieve data
 *********************************************************************************/

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

    public List<Card> listAll(){
        return (List<Card>) repo.findAll();
    }

    public void save(Card card){
        repo.save(card);
    }

    public Card get(int cardId){ return repo.findById(cardId).get(); }

    public Card findByCardNumber(String cardNumber) {return repo.findByCardNumber(cardNumber);}

    public List<Card> findByClientId(int clientId) { return repo.findByClientId(clientId);};

    public void delete(int id){
        repo.deleteById(id);
    }


}
