package com.assignment2.COMP3095.repo;

import com.assignment2.COMP3095.models.Card;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CardRepo extends CrudRepository<Card, Integer>
{
    Card findByCardNumber(String cardNumber);
    List<Card> findByClientId(int clientId);
}
