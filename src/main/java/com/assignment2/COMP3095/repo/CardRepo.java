package com.assignment2.COMP3095.repo;

import com.assignment2.COMP3095.models.Card;
import org.springframework.data.repository.CrudRepository;

public interface CardRepo extends CrudRepository<Card, Integer>
{
    Card findByCardNumber(String cardNumber);

}
