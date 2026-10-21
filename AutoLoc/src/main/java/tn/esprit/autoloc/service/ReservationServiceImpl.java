package tn.esprit.autoloc.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.domain.Reservation;
import tn.esprit.autoloc.domain.StatutReservation;
import tn.esprit.autoloc.exception.ResourceNotFoundException;
import tn.esprit.autoloc.repository.IReservationRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements IReservationService {

    private final IReservationRepository reservationRepository;

    @Override
    public Reservation create(Reservation entite) {
        if (entite.getIdReservation() != null) {
            throw new IllegalArgumentException(
                    "Un nouveau réservation ne doit pas avoir d'identifiant");
        }
        if (entite.getStatut() == null) {
            entite.setStatut(StatutReservation.EN_ATTENTE);
        }
        valider(entite);
        return reservationRepository.save(entite);
    }

    @Override
    public Reservation findById(Long id) {
        return reservationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reservation", id));
    }

    @Override
    public List<Reservation> findAll() {
        return reservationRepository.findAll();
    }

    @Override
    public Reservation update(Long id, Reservation entite) {
        Reservation existant = findById(id);
        valider(entite);
        existant.setDateDebut(entite.getDateDebut());
        existant.setDateFin(entite.getDateFin());
        if (entite.getStatut() != null) {
            existant.setStatut(entite.getStatut());
        }
        if (entite.getClient() != null) {
            existant.setClient(entite.getClient());
        }
        if (entite.getVehicule() != null) {
            existant.setVehicule(entite.getVehicule());
        }
        return reservationRepository.save(existant);
    }

    @Override
    public void deleteById(Long id) {
        if (!reservationRepository.existsById(id)) {
            throw new ResourceNotFoundException("Reservation", id);
        }
        reservationRepository.deleteById(id);
    }

    private void valider(Reservation entite) {
        if (entite.getDateDebut() == null || entite.getDateFin() == null) {
            throw new IllegalArgumentException("Les dates de début et de fin de la réservation sont obligatoires");
        }
        if (entite.getDateDebut() != null && entite.getDateFin() != null && entite.getDateFin().isBefore(entite.getDateDebut())) {
            throw new IllegalArgumentException("La date de fin ne peut pas être antérieure à la date de début");
        }
    }
}
