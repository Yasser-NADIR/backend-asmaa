package com.example.project.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.project.entities.Role;
import com.example.project.entities.RoleEnum;

public interface RoleRepository extends JpaRepository<Role, Integer>{
    Optional<Role> findByName(RoleEnum name);

}
