package com.hmvss.api.persistence.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "vital_signals")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class VitalSignals {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "temperature")
    private Double temperature;

    @Column(name = "rhythm_pulse")
    private String rhythmPulse;

    @Column(name = "oxygen_saturation")
    private Double oxygenSaturation;

    @Column(name = "respiratory_rate")
    private Integer respiratoryRate;

    @Column(name = "systolic_blood_pressure")
    private Integer systolicBloodPressure;

    @Column(name = "diastolic_blood_pressure")
    private Integer diastolicBloodPressure;


    @ManyToOne
    @JoinColumn(name = "medical_record_id")
    private MedicalRecord medicalRecord;

    @ManyToOne
    @JoinColumn(name = "medical_consultation_id")
    private MedicalConsultation medicalConsultation;

    @ManyToOne
    @JoinColumn(name = "treatment_cycle_id")
    private TreatmentCycle treatmentCycle;



}
