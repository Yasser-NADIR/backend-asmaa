package com.example.project.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.project.dto.PaiementDto;
import com.example.project.dto.RequetMaintenanceDto;
import com.example.project.dto.ResidentDto;
import com.example.project.entities.Resident;
import com.example.project.services.ResidentService;

@CrossOrigin(origins = "*")
@RequestMapping("/residents")
@RestController
public class ResidentController {

    private ResidentService residentService;

    ResidentController(ResidentService residentService){
        this.residentService = residentService;
    }

    @GetMapping("/list")
    public List<Resident> getAllResidents(){
        return this.residentService.getAllResidents();
    }

    @PostMapping("/register")
    public Boolean inscriptionResident(@RequestBody ResidentDto ResidentDto){
        return this.residentService.inscriptionResident(ResidentDto);
    }

    @PutMapping
    public Resident updateResident(@RequestBody ResidentDto ResidentDto){
        return this.residentService.updateResident(ResidentDto);
    }

    @PutMapping("/set_chambre/{id}")
    public Resident setChambre(@PathVariable Integer id, @RequestBody ResidentDto residentDto){
        return this.residentService.setChambre(id, residentDto);
    }

    @PutMapping("/add_paiement")
    public Resident addPaiment(@RequestBody PaiementDto paiementDto){
        return this.residentService.addPaiment(paiementDto);
    }

    @PostMapping("/add_requet_maintenance")
    public Resident addRequetMaintenance(@RequestBody RequetMaintenanceDto requetMaintenanceDto){
        return this.residentService.addRequetMaintenance(requetMaintenanceDto);
    }

}
