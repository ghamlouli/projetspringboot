package tn.esprit.autoloc.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Reservation;
import tn.esprit.autoloc.service.IReservationService;

import java.util.List;

@RestController
@RequestMapping("/reservations")
@RequiredArgsConstructor
@Tag(name = "Réservations", description = "Gestion des réservations de véhicules")
public class ReservationController {

    private final IReservationService reservationService;

    @Operation(summary = "Lister les reservations")
    @ApiResponse(responseCode = "200", description = "Liste retournée")
    @GetMapping
    public List<Reservation> findAll() {
        return reservationService.findAll();
    }

    @Operation(summary = "Consulter un réservation")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Réservation trouvé(e)"),
            @ApiResponse(responseCode = "404", description = "Réservation introuvable")})
    @GetMapping("/{id}")
    public Reservation findById(@Parameter(description = "Identifiant") @PathVariable Long id) {
        return reservationService.findById(id);
    }

    @Operation(summary = "Créer un réservation")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Réservation créé(e)"),
            @ApiResponse(responseCode = "400", description = "Données invalides")})
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Reservation create(@RequestBody Reservation entite) {
        return reservationService.create(entite);
    }

    @Operation(summary = "Modifier un réservation")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Réservation modifié(e)"),
            @ApiResponse(responseCode = "404", description = "Réservation introuvable"),
            @ApiResponse(responseCode = "400", description = "Données invalides")})
    @PutMapping("/{id}")
    public Reservation update(@Parameter(description = "Identifiant") @PathVariable Long id,
                      @RequestBody Reservation entite) {
        return reservationService.update(id, entite);
    }

    @Operation(summary = "Supprimer un réservation")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Réservation supprimé(e)"),
            @ApiResponse(responseCode = "404", description = "Réservation introuvable")})
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@Parameter(description = "Identifiant") @PathVariable Long id) {
        reservationService.deleteById(id);
    }
}
