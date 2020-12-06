package com.assignment2.COMP3095.repo;


import com.assignment2.COMP3095.models.Support;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SupportRepo extends CrudRepository<Support, Integer> {

    public List<Support> findMessagesByClientId(int clientId);
}
