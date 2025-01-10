package com.example.project.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.project.dto.ChambreDto;
import com.example.project.entities.Chambre;
import com.example.project.repositories.ChambreRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ChambreService {

    private ChambreRepository chambreRepository;

    ChambreService(ChambreRepository chambreRepository){
        this.chambreRepository = chambreRepository;
    }


    public Chambre createChambre(ChambreDto chambreDto){
        Chambre chambre = new Chambre();

        chambre.setTaille(chambreDto.getTaille());
        chambre.setEquipement(chambreDto.getEquipement());
        chambre.setDisponibilite(chambreDto.getDisponibilite());
        chambre.setMontant(chambreDto.getMontant());

        return chambreRepository.save(chambre);
    }


    public Chambre updateChambre(Integer id, ChambreDto chambreDto) {
        Chambre chambre = chambreRepository.findById(id).get();

        chambre.setTaille(chambreDto.getTaille());
        chambre.setEquipement(chambreDto.getEquipement());
        chambre.setDisponibilite(chambreDto.getDisponibilite());
        chambre.setMontant(chambreDto.getMontant());

        return chambreRepository.save(chambre);
    }


    public void deleteChambre(Integer id) {
        this.chambreRepository.deleteById(id);
    }


    public List<Chambre> getAllChambre() {
        return this.chambreRepository.findAll();
    }

    public List<Chambre> searchChambre(ChambreDto chambreDto){
        System.out.println(chambreDto.toString());
        return this.chambreRepository
        .findByTailleOrEquipementOrDisponibiliteOrMontant(
            chambreDto.getTaille(), 
            chambreDto.getEquipement(), 
            chambreDto.getDisponibilite(), 
            chambreDto.getMontant());
    }


	public Integer getCapaciteHebergement() {
		return this.chambreRepository
        .findAll()
        .stream()
        .mapToInt(Chambre::getTaille)
        .sum();
	}
}
