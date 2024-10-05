package com.hmvss.api.persistence.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
@Entity
@Table(name = "medical_records")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class MedicalRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, length = 15)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "patients_id")
    private Patients patient;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User createdBy;

    @Column(name="reason", nullable = false, length = 30)
    private String reason;

    @Column(name="actual_disease", nullable = false, length = 30)
    private String actualDisease;

    @Column(name="previous_diagnosis", nullable = false, length = 30)
    private String previousDiagnosis;

    @Column(name="previous_treatment", nullable = false, length = 30)
    private String previousTreatment;

    @Column(name = "registry_date", nullable = false)
    private Date registryDate;

    @ManyToOne
    @JoinColumn(name = "medical_service_id")
    private MedicalService historyMedicalService;

    @ManyToOne
    @JoinColumn(name = "refered_medical_service_id")
    private MedicalService referedMedicalService;

    @OneToMany(mappedBy = "medicalRecord", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MedicalConsultation> medicalConsultations;


}
