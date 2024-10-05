package com.hmvss.api.persistence.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "specialist_data")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Specialist{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, length = 15)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "personal_data_id", referencedColumnName = "id")
    private PersonalData personalData;

    @Column(name = "medical_college_code", nullable = false, length = 200)
    private String MedicalCollegeCode;

    @Column(name = "mpps_code", nullable = false, length = 200)
    private String mppsCode;

    @Column(name = "speciality", nullable = false, length = 200)
    private String speciality;

    @OneToMany(mappedBy = "specialist", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MedicalConsultation> medicalConsultations;


}
