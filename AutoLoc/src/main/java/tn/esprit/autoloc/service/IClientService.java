package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Client;

import java.util.List;

public interface IClientService {

    Client create(Client entite);

    Client findById(Long id);

    List<Client> findAll();

    Client update(Long id, Client entite);

    void deleteById(Long id);
}
