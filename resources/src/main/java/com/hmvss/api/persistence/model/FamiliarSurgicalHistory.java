package com.hmvss.api.persistence.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



/**
 * Clase que representa un antecedente quirurgico o personal
 */
@Entity
@Table(name = "familiar_surgical_history")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class FamiliarSurgicalHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, length = 15)
    private Long id;

    @Column(name = "disease_name",  length = 200)
    private String diseaseName;

    @Column(name = "family_relationship",  length = 200)
    private String familyRelationship;

    @ManyToOne
    @JoinColumn(name = "patients_id")
    private Patients patient;
}
