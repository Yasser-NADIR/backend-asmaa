package com.example.project.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.project.entities.Resident;
import com.example.project.entities.User;

public interface ResidentRepository extends JpaRepository<Resident, Integer>{
    Optional<Resident> findByUser(User user);
}
