package com.example.project.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.project.dto.ResidentDto;
import com.example.project.entities.Paiement;
import com.example.project.services.PaiementService;

@RequestMapping("/paiements")
@RestController
public class PaiementController {

    private PaiementService paiementService;

    public PaiementController(PaiementService paiementService) {
        this.paiementService = paiementService;
    }

    @GetMapping("/hisotrique_paiement")
    public List<Paiement> getHistoriquePaimenet(@RequestBody ResidentDto residentDto){
        return this.paiementService.getHistoriquePaiemenet(residentDto);
    }
}
