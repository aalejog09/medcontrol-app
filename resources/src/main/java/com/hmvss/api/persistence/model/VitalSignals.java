package com.hmvss.api.persistence.model;

import jakarta.persistence.*;

@Entity
@Table(name = "vital_signals")
public class VitalSignals {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "treatment_cicle", nullable = false)
    private TreatmentCicle treatmentCicle;

    @Column(name = "temperature")
    private Double temperature;

    @Column(name = "rhythm_pulse")
    private String rhythmPulse;

    @Column(name = "rhythm_strength")
    private String rhythmStrength;

    @Column(name = "respiratory_rate")
    private Integer respiratoryRate;

    @Column(name = "systolic_blood_pressure")
    private Integer systolicBloodPressure;

    @Column(name = "diastolic_blood_pressure")
    private Integer diastolicBloodPressure;

}
