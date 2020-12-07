/*********************************************************************************
 * Project: COMP3095 - Assignment 3
 * Assignment: Assignment 3
 * Author(s):       Joel Piovesan     Rachel Titco
 * Student Number:  101221909         101214347
 * Date: 06/12/2020
 * Description: Interface where the project may interact with the
 * database (called Clients) that contains all the system users through functions
 *********************************************************************************/

package com.assignment2.COMP3095.repo;

import com.assignment2.COMP3095.models.Client;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ClientRepo extends CrudRepository<Client, Integer>
{
    Client findByEmail(String email);
    List<Client> findByRole(String role);
}
