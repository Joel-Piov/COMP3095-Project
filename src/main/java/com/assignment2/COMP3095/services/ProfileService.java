/*********************************************************************************
 * Project: COMP3095 - Assignment 3
 * Assignment: Assignment 3
 * Author(s):       Joel Piovesan     Rachel Titco
 * Student Number:  101221909         101214347
 * Date: 06/12/2020
 * Description: class that provides functions with which to access the Profiles
 * database and retrieve data by calling its corresponding interface
 *********************************************************************************/

package com.assignment2.COMP3095.services;

import com.assignment2.COMP3095.models.Profile;
import com.assignment2.COMP3095.repo.ProfileRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileService {
    @Autowired
    private ProfileRepo repo;

    public List<Profile> listAll(){
        return (List<Profile>) repo.findAll();
    }

    public void save(Profile profile){
        repo.save(profile);
    }

    public Profile get(int id){
        return repo.findById(id).get();
    }

    public void delete(int id){
        repo.deleteById(id);
    }

    public Profile findByPostalCode(String postalCode){ return repo.findByPostalCode(postalCode);}

    public Profile findBillingPrefByClientId(int clientId){return repo.findBillingPrefByClientId(clientId);}

    public Profile findShippingPrefByClientId(int clientId){return repo.findShippingPrefByClientId(clientId);}

    public List<Profile> findProfileByClientId(int clientId){
        return repo.findProfileByClientId(clientId);
    }

    public Profile findByClientId(int clientId){return repo.findByClientId(clientId);}
}
