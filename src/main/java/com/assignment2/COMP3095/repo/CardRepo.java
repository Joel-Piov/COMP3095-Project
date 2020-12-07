/*********************************************************************************
 * Project: COMP3095 - Assignment 3
 * Assignment: Assignment 3
 * Author(s):       Joel Piovesan     Rachel Titco
 * Student Number:  101221909         101214347
 * Date: 06/12/2020
 * Description: Interface where the project may interact with the
 * Credit Card database through functions
 *********************************************************************************/

package com.assignment2.COMP3095.repo;

import com.assignment2.COMP3095.models.Card;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CardRepo extends CrudRepository<Card, Integer>
{
    Card findByCardNumber(String cardNumber);
    List<Card> findByClientId(int clientId);
}
