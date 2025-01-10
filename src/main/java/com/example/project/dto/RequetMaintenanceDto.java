package com.example.project.dto;

import lombok.Data;

@Data
public class RequetMaintenanceDto {
    private String residentEmail;
    private String title;
    private String description;
    private Integer idTechnicien;
    private Integer idChambre;
}
