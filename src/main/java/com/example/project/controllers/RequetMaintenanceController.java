package com.example.project.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.project.dto.RequetMaintenanceDto;
import com.example.project.entities.RequetMaintenance;
import com.example.project.services.RequetMaintenanceService;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

@RequestMapping("requet_maintenance")
@RestController
public class RequetMaintenanceController {
    private RequetMaintenanceService requetMaintenanceService;

    public RequetMaintenanceController(RequetMaintenanceService requetMaintenanceService) {
        this.requetMaintenanceService = requetMaintenanceService;
    }

    @GetMapping("/list")
    public List<RequetMaintenance> getAllRequetMaintenance(){
        return this.requetMaintenanceService.getAllRequetMaintenance();
    }

    @GetMapping("/requet_by_resident")
    public List<RequetMaintenance> getRequetByResident(@RequestBody RequetMaintenanceDto requetMaintenanceDto) {
        return this.requetMaintenanceService.getRequetByResident(requetMaintenanceDto);
    }

    @GetMapping("/requet_by_chambre")
    public List<RequetMaintenance> getRequetByChambre(@RequestBody RequetMaintenanceDto requetMaintenanceDto) {
        return this.requetMaintenanceService.getRequetByChambre(requetMaintenanceDto);
    }
    
    

    @PutMapping("/assign_requet_technicien/{id}")
    public RequetMaintenance updateRequetMaintenance(
        @PathVariable Integer idRequetMaintenance, 
        @RequestBody RequetMaintenanceDto requetMaintenanceDto) {
        return this.requetMaintenanceService.AssignRequetToTechnicien(idRequetMaintenance, requetMaintenanceDto);
    }
    
}
