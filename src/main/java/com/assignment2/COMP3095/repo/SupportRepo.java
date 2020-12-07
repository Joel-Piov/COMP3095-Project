/*********************************************************************************
 * Project: COMP3095 - Assignment 3
 * Assignment: Assignment 3
 * Author(s):       Joel Piovesan     Rachel Titco
 * Student Number:  101221909         101214347
 * Date: 06/12/2020
 * Description: Interface where the project may interact with the Support messages
 * database through functions. Search for messages by Client ID or Admin ID
 *********************************************************************************/

package com.assignment2.COMP3095.repo;


import com.assignment2.COMP3095.models.Client;
import com.assignment2.COMP3095.models.Support;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SupportRepo extends CrudRepository<Support, Integer> {

    List<Support> findMessagesByClientId(int clientId);
    List<Support> findMessagesByAdminId(int adminId);
}
