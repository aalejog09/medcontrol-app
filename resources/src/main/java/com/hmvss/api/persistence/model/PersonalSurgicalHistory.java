package com.hmvss.api.persistence.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;


/**
 * Clase que representa un antecedente quirurgico o personal
 */
@Entity
@Table(name = "personal_surgical_history")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PersonalSurgicalHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, length = 15)
    private Long id;

    @Column(name = "disease_name",  length = 200)
    private String diseaseName;

    @Column(name = "intervention_date")
    private Date interventionDate;

    @Column(name = "diagnosis",  length = 200)
    private String diagnosis;

    @Column(name = "treatment", length = 200)
    private String treatment;

    @ManyToOne
    @JoinColumn(name = "patients_id")
    private Patients patient;
}
