package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Equipement;

import java.util.List;

public interface IEquipementService {

    Equipement create(Equipement entite);

    Equipement findById(Long id);

    List<Equipement> findAll();

    Equipement update(Long id, Equipement entite);

    void deleteById(Long id);
}
