package com.example.project.entities;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Table(name = "chambres")
@Entity
@Data
public class Chambre {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    @Column(nullable = false)
    private Integer taille;
    
    @Column(nullable = false)
    private String equipement;
    
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Statut disponibilite;

    @Column(nullable = false)
    private Double montant;

    @OneToMany(mappedBy = "chambre", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Resident> residents = new ArrayList<>();

    @OneToMany(mappedBy = "chambre", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<RequetMaintenance> requetMaintenances = new ArrayList<>();
}
