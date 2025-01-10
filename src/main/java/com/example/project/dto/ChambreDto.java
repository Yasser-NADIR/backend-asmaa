package com.example.project.dto;

import com.example.project.entities.Statut;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ChambreDto {
    private Integer taille;
    private String equipement;
    private Statut disponibilite;
    private Double montant;
}
