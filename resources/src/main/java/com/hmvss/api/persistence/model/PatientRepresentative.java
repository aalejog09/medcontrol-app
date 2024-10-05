package com.hmvss.api.persistence.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;


@Entity
@Table(name = "patients_representative")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PatientRepresentative {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, length = 15)
    private Long id;

    @Column(name = "family_relationship", length = 30)
    private String familyRelationship;

    @ManyToOne
    @JoinColumn(name = "personal_data", nullable = false)
    private PersonalData personalData;

    @ManyToMany(mappedBy = "representatives")
    private Set<Patients> patients;

}
