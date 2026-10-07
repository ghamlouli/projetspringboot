package tn.esprit.autoloc.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "agence")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Agence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAgence;

    @Column(nullable = false, length = 100)
    private String nom;

    @Column(nullable = false, length = 50)
    private String ville;

    @Column(length = 150)
    private String adresse;

    @Column(length = 20)
    private String telephone;

    // OneToMany bidirectionnel : une agence possède plusieurs véhicules
    @OneToMany(mappedBy = "agence")
    private Set<Vehicule> vehicules = new HashSet<>();

    // OneToMany bidirectionnel : une agence emploie plusieurs employés
    @OneToMany(mappedBy = "agence")
    private Set<Employe> employes = new HashSet<>();
}
