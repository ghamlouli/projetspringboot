package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Paiement;

import java.util.List;

/**
 * Service en lecture seule : un paiement est créé, modifié ou supprimé
 * uniquement à travers son Contrat (composition, cascade = ALL).
 */
public interface IPaiementService {

    Paiement findById(Long id);

    List<Paiement> findAll();
}
