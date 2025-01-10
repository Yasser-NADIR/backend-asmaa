package com.example.project.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.project.dto.ChambreDto;
import com.example.project.entities.Chambre;
import com.example.project.services.ChambreService;


@RequestMapping("/chambres")
@RestController
public class ChambreController {
    private ChambreService chambreService;

    ChambreController(ChambreService chambreService){
        this.chambreService = chambreService;
    }

    @GetMapping("/list")
    public List<Chambre> getAllChambre(){
        return this.chambreService.getAllChambre();
    }

    @GetMapping("/search")
    public List<Chambre> searchChambre(@RequestBody ChambreDto chambre){
        return this.chambreService.searchChambre(chambre);
    }

    @GetMapping("/capacite_hebergement")
    public Integer capaciteHebergement(){
        return this.chambreService.getCapaciteHebergement();
    }

    @PostMapping("/create")
    public Chambre createChambre(@RequestBody ChambreDto chamber){
        System.out.println("somthig");
        System.out.println(chamber);
        return this.chambreService.createChambre(chamber);
    }

    @PutMapping("/{id}")
    public Chambre updatChambre(@PathVariable Integer id, @RequestBody ChambreDto chambre){
        return this.chambreService.updateChambre(id, chambre);
    }

    @DeleteMapping("/{id}")
    public void deleteChambre(@PathVariable Integer id){
        this.chambreService.deleteChambre(id);
    }
}
