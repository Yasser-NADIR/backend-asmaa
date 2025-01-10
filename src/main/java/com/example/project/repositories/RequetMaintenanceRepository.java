package com.example.project.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.project.entities.Chambre;
import com.example.project.entities.RequetMaintenance;
import com.example.project.entities.Resident;

public interface RequetMaintenanceRepository extends JpaRepository<RequetMaintenance, Integer>{
    List<RequetMaintenance> findByResident(Resident resident);
    List<RequetMaintenance> findByChambre(Chambre chambre);
}
