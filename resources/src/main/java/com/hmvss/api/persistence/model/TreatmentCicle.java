package com.hmvss.api.persistence.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "treatment_cicle")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TreatmentCicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, length = 15)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "medical_record_id", nullable = false)
    private MedicalRecord medicalRecord;

    @Column(name = "cicle_number", nullable = false)
    private Integer cicleNumber;

    @Column(name = "treatment", nullable = false)
    private String treatment;

    @Column(name = "treatmentRate", nullable = false)
    private String treatmentRate;

    @Column(name = "treatmentTime", nullable = false)
    private String treatmentTime;

    @Column(name = "observations")
    private String observations;

    @Column(name = "registry_date")
    private Date registryDate;

    @Column(name = "treatment_cicle_date")
    private Date treatmentCicleDate;

    @OneToMany(mappedBy = "treatmentCicle", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<VitalSignals> vitalSignals;

}
