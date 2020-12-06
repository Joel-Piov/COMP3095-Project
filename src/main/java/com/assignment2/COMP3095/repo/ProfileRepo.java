package com.assignment2.COMP3095.repo;

import com.assignment2.COMP3095.models.Profile;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProfileRepo extends CrudRepository<Profile, Integer> {
    Profile findByPostalCode(String postalCode);

    List<Profile> findProfileByClientId(int clientId);

}
