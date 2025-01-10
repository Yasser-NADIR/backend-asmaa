package com.example.project.services;

import java.util.Comparator;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.project.dto.PaiementDto;
import com.example.project.dto.RequetMaintenanceDto;
import com.example.project.dto.ResidentDto;
import com.example.project.entities.Chambre;
import com.example.project.entities.HistoriquePaiement;
import com.example.project.entities.Paiement;
import com.example.project.entities.RequetMaintenance;
import com.example.project.entities.Resident;
import com.example.project.entities.Role;
import com.example.project.entities.RoleEnum;
import com.example.project.entities.StatutRequetMaintenance;
import com.example.project.entities.User;
import com.example.project.repositories.ChambreRepository;
import com.example.project.repositories.HisotriquePaiementRepository;
import com.example.project.repositories.PaiementRepository;
import com.example.project.repositories.RequetMaintenanceRepository;
import com.example.project.repositories.ResidentRepository;
import com.example.project.repositories.RoleRepository;
import com.example.project.repositories.UserRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ResidentService {
    private ResidentRepository residentRepository;
    private RoleRepository roleRepository;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private ChambreRepository chambreRepository;
    private PaiementRepository paiementRepository;
    private HisotriquePaiementRepository hisotriquePaiementRepository;
    private RequetMaintenanceRepository requetMaintenanceRepository;

    ResidentService(
        ResidentRepository residentRepository, 
        RoleRepository roleRepository, 
        UserRepository userRepository,
        PasswordEncoder passwordEncoder,
        ChambreRepository chambreRepository,
        PaiementRepository paiementRepository,
        HisotriquePaiementRepository hisotriquePaiementRepository,
        RequetMaintenanceRepository requetMaintenanceRepository){
        this.residentRepository = residentRepository;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.chambreRepository = chambreRepository;
        this.paiementRepository = paiementRepository;
        this.hisotriquePaiementRepository = hisotriquePaiementRepository;
        this.requetMaintenanceRepository = requetMaintenanceRepository;
    }

    private Boolean validateWithRegex(String claim, String regex){
        Pattern pattern = Pattern.compile(regex);
        if (claim == null || claim.isEmpty()) {
            return false;
        }
        return pattern.matcher(claim).matches();
    }

    private Boolean isEmailValid(String email){
        String emailRegex = "^[\\w.-]+@[a-zA-Z\\d.-]+\\.[a-zA-Z]{2,6}$";
        return validateWithRegex(email, emailRegex);
    }

    private Boolean isTelephoneValid(String telephone){
       String phoneRegex = "^(0[67]\\d{8}|\\+212[67]\\d{8})$";
       return validateWithRegex(telephone, phoneRegex);
    }

    private Boolean validationInscription(ResidentDto residentDto){
        return isEmailValid(residentDto.getEmail()) && isTelephoneValid(residentDto.getTelephone());
    }

    public Boolean inscriptionResident(ResidentDto residentDto){
        System.out.println(residentDto);
        try{
            if(! validationInscription(residentDto)) return false;
            
            Role role = this.roleRepository.findByName(RoleEnum.RESIDENT).get();

            User user = new User();
            user.setEmail(residentDto.getEmail());
            user.setFullName(residentDto.getFullName());
            user.setPassword(this.passwordEncoder.encode(residentDto.getPassword()));
            user.setRole(role);
            System.out.println(user);

            this.userRepository.save(user);
            System.out.println("after saving user");

            // Resident resident = new Resident();System.out.println("a");
            // resident.setCNE(residentDto.getCne());System.out.println("aa");
            // resident.setTelephone(residentDto.getTelephone());System.out.println("aaa");
            // resident.setUser(user);System.out.println("aaaa");
            // this.residentRepository.save(resident);System.out.println("aaaaa");
            // System.out.println(resident);
            return true;
        }catch(Exception e){
            System.out.println("problima");
            System.out.println(e.getMessage());
            return false;
        }
    }

    public Resident updateResident(ResidentDto residentDto) {
        User user = this.userRepository.findByEmail(residentDto.getEmail()).get();
        user.setFullName(residentDto.getFullName());
        this.userRepository.save(user);

        Resident resident = this.residentRepository.findByUser(user).get();
        resident.setCNE(residentDto.getCne());
        resident.setTelephone(residentDto.getTelephone());
        return this.residentRepository.save(resident);
    }

    public List<Resident> getAllResidents() {
        return this.residentRepository.findAll();
    }

    public Resident getResidentByEmail(String email){
        User user = this.userRepository.findByEmail(email).get();
        return this.residentRepository.findByUser(user).get();
    }

    public Resident setChambre(Integer id, ResidentDto residentDto) {
        Chambre chambre = this.chambreRepository.findById(id).get();
        
        Paiement paiement = new Paiement();
        paiement.setMontantTotal(chambre.getMontant());
        paiement.setChambre(chambre);
        this.paiementRepository.save(paiement);

        Resident resident = this.getResidentByEmail(residentDto.getEmail());
        resident.setChambre(chambre);
        resident.getPaiements().add(paiement);
        return this.residentRepository.save(resident);
    }

    private Paiement getCurrentPaiement(Resident resident){
        List<Paiement> paiements = resident.getPaiements();
        return paiements.stream()
        .sorted(Comparator.comparingInt(Paiement::getId))
        .collect(Collectors.toList()).get(paiements.size()-1);
    }

    public Resident addPaiment(PaiementDto paiementDto) {
        Resident resident = this.getResidentByEmail(paiementDto.getResidentEmail());
        Paiement paiement = this.getCurrentPaiement(resident);
        HistoriquePaiement historiquePaiement = new HistoriquePaiement();

        historiquePaiement.setMontantPaye(paiementDto.getMontantPaye());
        this.hisotriquePaiementRepository.save(historiquePaiement);

        paiement.getHistoriquePaiement().add(historiquePaiement);
        this.paiementRepository.save(paiement);

        return resident;
    }

    public Resident addRequetMaintenance(RequetMaintenanceDto requetMaintenanceDto) {
        Resident resident = this.getResidentByEmail(requetMaintenanceDto.getResidentEmail());
        RequetMaintenance requetMaintenance = new RequetMaintenance();

        requetMaintenance.setTitle(requetMaintenanceDto.getTitle());
        requetMaintenance.setDescription(requetMaintenanceDto.getDescription());
        requetMaintenance.setResident(resident);
        requetMaintenance.setChambre(resident.getChambre());
        requetMaintenance.setStatutRequetMaintenance(StatutRequetMaintenance.CREATED);
        this.requetMaintenanceRepository.save(requetMaintenance);

        resident.getMaintenanceRequets().add(requetMaintenance);

        return this.residentRepository.save(resident);
    }
}
