package com.example.project.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Table(name = "maintenace_requets")
@Entity
@Data
public class RequetMaintenance {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    // @Column(nullable = false)
    private StatutRequetMaintenance statutRequetMaintenance;

    @ManyToOne
    @JoinColumn(name = "Chambre_id")
    @JsonIgnore
    private Chambre chambre;

    @ManyToOne
    @JoinColumn(name = "resident_id")
    @JsonIgnore
    private Resident resident;

    @ManyToOne
    @JoinColumn(name = "technicien_id")
    private Technicien technicien;
}
