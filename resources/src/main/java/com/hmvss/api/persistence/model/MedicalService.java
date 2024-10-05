package com.hmvss.api.persistence.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Clase persistente que representa un servicio medico dentro del hospital.
 */

@Entity
@Table(name = "medical_service")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class MedicalService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 200)
    private String name;

    @OneToOne
    @JoinColumn(name = "responsible_user_id")
    private User responsibleUser;

    @OneToMany(mappedBy = "historyMedicalService", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MedicalRecord> medicalRecords;

    @OneToMany(mappedBy = "referedMedicalService", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MedicalRecord> referredMedicalRecords;





}