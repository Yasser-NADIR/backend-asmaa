package com.example.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.project.entities.Paiement;

public interface PaiementRepository extends JpaRepository<Paiement, Integer>{
    
}
