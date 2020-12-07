/*********************************************************************************
 * Project: COMP3095 - Assignment 3
 * Assignment: Assignment 3
 * Author(s):       Joel Piovesan     Rachel Titco
 * Student Number:  101221909         101214347
 * Date: 06/12/2020
 * Description: class that provides functions with which to access the Support messages
 * database and retrieve data by calling its corresponding interface
 *********************************************************************************/

package com.assignment2.COMP3095.services;

import com.assignment2.COMP3095.models.Support;
import com.assignment2.COMP3095.repo.SupportRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupportService {


    @Autowired
    private SupportRepo repo;

    public List<Support> listAll(){
        return (List<Support>) repo.findAll();
    }

    public void save(Support support){
        repo.save(support);
    }

    public Support get(int id){
        return repo.findById(id).get();
    }

    public void delete(int id){
        repo.deleteById(id);
    }

    public List<Support> findMessagesByClientId(int clientId){ return repo.findMessagesByClientId(clientId); }
    public List<Support> findMessagesByAdminId(int adminId){ return repo.findMessagesByAdminId(adminId); }

}
