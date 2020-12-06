/*********************************************************************************
 * Project: < project name … >
 * Assignment: Assignment 2
 * Author(s):       Joel Piovesan     Rachel Titco
 * Student Number:  101221909         101214347
 * Date: 07/11/2020
 * Description: A class that calls the ClientRepo interface, to access the H2 database entries
 *********************************************************************************/

package com.assignment2.COMP3095.services;

import com.assignment2.COMP3095.models.Client;
import com.assignment2.COMP3095.repo.ClientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    @Autowired
    private ClientRepo repo;

    public List<Client> listAll(){
        return (List<Client>) repo.findAll();
    }

    public List<Client> findByRole(String role){ return (List<Client>) repo.findByRole(role);}

    public void save(Client client){
        repo.save(client);
    }

    public Client get(int id){
        return repo.findById(id).get();
    }
    
    public void delete(int id){
        repo.deleteById(id);
    }


    public Client findByEmail(String email){
        return repo.findByEmail(email);
    }

    public String findPassword(String email) { return findByEmail(email).getPassword();}
}
