package com.ccsw.tutorial.client;

import com.ccsw.tutorial.client.model.Client;
import com.ccsw.tutorial.client.model.ClientDto;

import java.util.List;

public interface ClientService {
    //Recupera un Cliente a partir de su id

    Client get(Long id);

    //Metodo para recuperar todas los Clients
    List<Client> findAll();

    //Metodo para crear o actualizar un Client
    void save(Long id, ClientDto dto);

    //Metodo para borrar un client
    void delete(Long id) throws Exception;

}
