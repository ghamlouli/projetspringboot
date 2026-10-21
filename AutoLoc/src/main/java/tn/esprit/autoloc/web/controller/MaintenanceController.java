package tn.esprit.autoloc.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Maintenance;
import tn.esprit.autoloc.service.IMaintenanceService;

import java.util.List;

@RestController
@RequestMapping("/maintenances")
@RequiredArgsConstructor
@Tag(name = "Maintenances", description = "Gestion des maintenances des véhicules")
public class MaintenanceController {

    private final IMaintenanceService maintenanceService;

    @Operation(summary = "Lister les maintenances")
    @ApiResponse(responseCode = "200", description = "Liste retournée")
    @GetMapping
    public List<Maintenance> findAll() {
        return maintenanceService.findAll();
    }

    @Operation(summary = "Consulter un maintenance")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Maintenance trouvé(e)"),
            @ApiResponse(responseCode = "404", description = "Maintenance introuvable")})
    @GetMapping("/{id}")
    public Maintenance findById(@Parameter(description = "Identifiant") @PathVariable Long id) {
        return maintenanceService.findById(id);
    }

    @Operation(summary = "Créer un maintenance")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Maintenance créé(e)"),
            @ApiResponse(responseCode = "400", description = "Données invalides")})
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Maintenance create(@RequestBody Maintenance entite) {
        return maintenanceService.create(entite);
    }

    @Operation(summary = "Modifier un maintenance")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Maintenance modifié(e)"),
            @ApiResponse(responseCode = "404", description = "Maintenance introuvable"),
            @ApiResponse(responseCode = "400", description = "Données invalides")})
    @PutMapping("/{id}")
    public Maintenance update(@Parameter(description = "Identifiant") @PathVariable Long id,
                      @RequestBody Maintenance entite) {
        return maintenanceService.update(id, entite);
    }

    @Operation(summary = "Supprimer un maintenance")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Maintenance supprimé(e)"),
            @ApiResponse(responseCode = "404", description = "Maintenance introuvable")})
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@Parameter(description = "Identifiant") @PathVariable Long id) {
        maintenanceService.deleteById(id);
    }
}
