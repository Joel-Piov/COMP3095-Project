package com.assignment2.COMP3095.repo;

import com.assignment2.COMP3095.models.Client;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ClientRepo extends CrudRepository<Client, Integer>
{
    Client findByEmail(String email);
    List<Client> findByRole(String role);
}
