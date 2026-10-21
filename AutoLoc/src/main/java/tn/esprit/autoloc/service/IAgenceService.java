package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Agence;

import java.util.List;

public interface IAgenceService {

    Agence create(Agence entite);

    Agence findById(Long id);

    List<Agence> findAll();

    Agence update(Long id, Agence entite);

    void deleteById(Long id);
}
