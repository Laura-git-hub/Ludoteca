package com.ccsw.tutorial.client;

import com.ccsw.tutorial.client.model.Client;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ClientRepository extends CrudRepository<Client, Long> {
    Optional<Client> findByName(String name);
}