package com.assignment2.COMP3095.repo;

import com.assignment2.COMP3095.models.Client;
import org.springframework.data.repository.CrudRepository;

public interface ClientRepo extends CrudRepository<Client, Integer>
{
    Client findByEmail(String email);
}
