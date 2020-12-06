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

    public List<Profile> findProfileByClientId(int clientId){
        return findProfileByClientId(clientId);
    }
}
