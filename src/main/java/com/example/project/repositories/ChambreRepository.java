package com.example.project.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.project.entities.Chambre;
import com.example.project.entities.Statut;

public interface ChambreRepository extends JpaRepository<Chambre, Integer>{
    List<Chambre> findByTailleOrEquipementOrDisponibiliteOrMontant(
        Integer taille, 
        String equipement, 
        Statut disponibilite, 
        Double montant);
}
