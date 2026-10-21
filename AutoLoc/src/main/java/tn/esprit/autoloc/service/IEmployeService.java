package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Employe;

import java.util.List;

public interface IEmployeService {

    Employe create(Employe entite);

    Employe findById(Long id);

    List<Employe> findAll();

    Employe update(Long id, Employe entite);

    void deleteById(Long id);
}
