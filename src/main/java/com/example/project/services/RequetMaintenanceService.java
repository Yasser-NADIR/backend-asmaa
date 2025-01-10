package com.example.project.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.project.dto.RequetMaintenanceDto;
import com.example.project.entities.Chambre;
import com.example.project.entities.RequetMaintenance;
import com.example.project.entities.Resident;
import com.example.project.entities.StatutRequetMaintenance;
import com.example.project.entities.Technicien;
import com.example.project.repositories.ChambreRepository;
import com.example.project.repositories.RequetMaintenanceRepository;
import com.example.project.repositories.ResidentRepository;
import com.example.project.repositories.TechnicienRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class RequetMaintenanceService {
    private RequetMaintenanceRepository requetMaintenanceRepository;
    private TechnicienRepository technicienRepository;
    private ResidentService residentService;
    private ChambreRepository chambreRepository;

    public RequetMaintenanceService(
        RequetMaintenanceRepository requetMaintenanceRepository,
        TechnicienRepository technicienRepository,
        ResidentRepository residentRepository,
        ResidentService residentService,
        ChambreRepository chambreRepository) {
        this.requetMaintenanceRepository = requetMaintenanceRepository;
        this.technicienRepository = technicienRepository;
        this.residentService = residentService;
        this.chambreRepository = chambreRepository;
    }

    public List<RequetMaintenance> getAllRequetMaintenance() {
        return this.requetMaintenanceRepository.findAll();
    }

    public RequetMaintenance AssignRequetToTechnicien(
                Integer idRequetMaintenance,
                RequetMaintenanceDto requetMaintenanceDto) {
        RequetMaintenance requetMaintenance = this.requetMaintenanceRepository
                                                    .findById(idRequetMaintenance)
                                                    .get();
        Technicien technicien = this.technicienRepository.findById(requetMaintenanceDto.getIdTechnicien()).get();

        requetMaintenance.setTechnicien(technicien);
        requetMaintenance.setStatutRequetMaintenance(StatutRequetMaintenance.ASSIGNED);
        this.requetMaintenanceRepository.save(requetMaintenance);

        technicien.getRequetMaintenances().add(requetMaintenance);
        this.technicienRepository.save(technicien);
        return requetMaintenance;
    }

    public List<RequetMaintenance> getRequetByResident(RequetMaintenanceDto requetMaintenanceDto) {
        Resident resident = this.residentService.getResidentByEmail(requetMaintenanceDto.getResidentEmail());
        return this.requetMaintenanceRepository.findByResident(resident);
    }

    public List<RequetMaintenance> getRequetByChambre(RequetMaintenanceDto requetMaintenanceDto) {
        Chambre chambre = this.chambreRepository.findById(requetMaintenanceDto.getIdChambre()).get();
        return this.requetMaintenanceRepository.findByChambre(chambre);
    }

}
