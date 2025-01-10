package com.example.project.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "residents")
@Data
public class Resident{
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false)
    private String CNE;

    @Column(nullable = false)
    private String telephone;
    
    @OneToMany(fetch = FetchType.EAGER)
    private List<Paiement> Paiements = new ArrayList<>();

    @OneToMany(mappedBy = "resident", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<RequetMaintenance> maintenanceRequets = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "chambre_id")
    private Chambre chambre;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
