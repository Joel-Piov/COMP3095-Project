package com.assignment2.COMP3095.services;

import com.assignment2.COMP3095.models.Support;
import com.assignment2.COMP3095.repo.SupportRepo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

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

    public List<Support> findMessagesByClientId(int clientId){
        return repo.findMessagesByClientId(clientId);
    }

}