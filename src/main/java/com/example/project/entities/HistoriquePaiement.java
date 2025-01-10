package com.example.project.entities;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Table(name = "historique_paiements")
@Entity
@Data
public class HistoriquePaiement {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false)
    private Double montantPaye;

    @Column(nullable = false)
    @CreationTimestamp
    private Date datePayement;
}
