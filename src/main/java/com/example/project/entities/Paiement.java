package com.example.project.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Table(name = "Paiement")
@Entity
@Data
public class Paiement {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false)
    private Double montantTotal;

    @OneToMany(fetch = FetchType.EAGER)
    private List<HistoriquePaiement> historiquePaiement = new ArrayList<>();

    @ManyToOne
    private Chambre chambre;

    @CreationTimestamp
    private Date CreatedDate;
}
