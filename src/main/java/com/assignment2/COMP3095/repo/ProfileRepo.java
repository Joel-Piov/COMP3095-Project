/*********************************************************************************
 * Project: COMP3095 - Assignment 3
 * Assignment: Assignment 3
 * Author(s):       Joel Piovesan     Rachel Titco
 * Student Number:  101221909         101214347
 * Date: 06/12/2020
 * Description: Interface where the project may interact with the database
 * containing all user profiles via functions
 *********************************************************************************/

package com.assignment2.COMP3095.repo;

import com.assignment2.COMP3095.models.Profile;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProfileRepo extends CrudRepository<Profile, Integer> {
    Profile findByPostalCode(String postalCode);

    List<Profile> findProfileByClientId(int clientId);

    Profile findBillingPrefByClientId(int clientId);

    Profile findShippingPrefByClientId(int clientId);

    Profile findByClientId(int clientId);

}
