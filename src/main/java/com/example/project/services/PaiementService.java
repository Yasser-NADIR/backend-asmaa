package com.example.project.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.project.dto.ResidentDto;
import com.example.project.entities.Paiement;
import com.example.project.entities.Resident;
import com.example.project.entities.User;
import com.example.project.repositories.ResidentRepository;
import com.example.project.repositories.UserRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class PaiementService {
    private ResidentRepository residentRepository;
    private UserRepository userRepository;

    public PaiementService(ResidentRepository residentRepository, UserRepository userRepository) {
        this.residentRepository = residentRepository;
        this.userRepository = userRepository;
    }

    public List<Paiement> getHistoriquePaiemenet(ResidentDto residentDto){
        try {
            System.out.println("test");
            System.out.println(residentDto);
            User user = this.userRepository.findByEmail(residentDto.getEmail()).get();
            Resident resident = this.residentRepository.findByUser(user).get();
            return resident.getPaiements();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

}
