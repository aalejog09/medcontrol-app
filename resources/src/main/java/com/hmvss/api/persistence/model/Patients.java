package com.hmvss.api.persistence.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;


@Entity
@Table(name = "patients_data")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Patients {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, length = 15)
    private Long id;

    @Column(name = "patient_type", length = 30)
    private String patientType;

    @Column(name = "patient_code", length = 30)
    private String patientCode;

    @Column(name = "patient_observation", length = 30)
    private String observation;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "personal_data_id", referencedColumnName = "id")
    private PersonalData personalData;

    @ManyToMany
    @JoinTable(
            name = "patients_data_representative",
            joinColumns = @JoinColumn(name = "patients_data_id"),
            inverseJoinColumns = @JoinColumn(name = "patients_representative_id")
    )
    private Set<PatientRepresentative> representatives;


    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PersonalSurgicalHistory> personalSurgicalHistoryList;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FamiliarSurgicalHistory> familiarSurgicalHistory;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PsicobiologicalHabits> psicobiologicalHabits;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MedicalRecord> medicalRecords;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ExaminationStudies> examinationStudies;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LabOrder> labOrders;

}
