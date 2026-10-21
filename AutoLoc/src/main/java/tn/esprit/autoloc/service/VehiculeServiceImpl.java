package tn.esprit.autoloc.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.exception.ResourceNotFoundException;
import tn.esprit.autoloc.repository.IVehiculeRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VehiculeServiceImpl implements IVehiculeService {

    private final IVehiculeRepository vehiculeRepository;

    @Override
    public Vehicule create(Vehicule entite) {
        if (entite.getIdVehicule() != null) {
            throw new IllegalArgumentException(
                    "Un nouveau véhicule ne doit pas avoir d'identifiant");
        }
        valider(entite);
        return vehiculeRepository.save(entite);
    }

    @Override
    public Vehicule findById(Long id) {
        return vehiculeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vehicule", id));
    }

    @Override
    public List<Vehicule> findAll() {
        return vehiculeRepository.findAll();
    }

    @Override
    public Vehicule update(Long id, Vehicule entite) {
        Vehicule existant = findById(id);
        valider(entite);
        existant.setImmatriculation(entite.getImmatriculation());
        existant.setMarque(entite.getMarque());
        existant.setModele(entite.getModele());
        existant.setCategorie(entite.getCategorie());
        existant.setTarifJournalier(entite.getTarifJournalier());
        existant.setStatut(entite.getStatut());
        if (entite.getAgence() != null) {
            existant.setAgence(entite.getAgence());
        }
        if (entite.getEquipements() != null && !entite.getEquipements().isEmpty()) {
            existant.getEquipements().clear();
            existant.getEquipements().addAll(entite.getEquipements());
        }
        return vehiculeRepository.save(existant);
    }

    @Override
    public void deleteById(Long id) {
        if (!vehiculeRepository.existsById(id)) {
            throw new ResourceNotFoundException("Vehicule", id);
        }
        vehiculeRepository.deleteById(id);
    }

    private void valider(Vehicule entite) {
        if (isBlank(entite.getImmatriculation())) {
            throw new IllegalArgumentException("L'immatriculation du véhicule est obligatoire");
        }
        if (isBlank(entite.getMarque())) {
            throw new IllegalArgumentException("La marque du véhicule est obligatoire");
        }
        if (isBlank(entite.getModele())) {
            throw new IllegalArgumentException("Le modèle du véhicule est obligatoire");
        }
        if (entite.getCategorie() == null) {
            throw new IllegalArgumentException("La catégorie du véhicule est obligatoire");
        }
        if (entite.getStatut() == null) {
            throw new IllegalArgumentException("Le statut du véhicule est obligatoire");
        }
        if (entite.getTarifJournalier() == null) {
            throw new IllegalArgumentException("Le tarif journalier est obligatoire");
        }
        if (entite.getTarifJournalier() != null && entite.getTarifJournalier().signum() < 0) {
            throw new IllegalArgumentException("Le tarif journalier ne peut pas être négatif");
        }
    }

    private static boolean isBlank(String valeur) {
        return valeur == null || valeur.isBlank();
    }
}
