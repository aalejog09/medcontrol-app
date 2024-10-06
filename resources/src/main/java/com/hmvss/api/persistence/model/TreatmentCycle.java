package com.hmvss.api.persistence.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "treatment_cycle")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TreatmentCycle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, length = 15)
    private Long id;

    @Column(name = "cycle_number", nullable = false)
    private Integer cycleNumber;

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

    @Column(name = "treatment_cycle_date")
    private Date treatmentCicleDate;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patients patient;

    @OneToOne
    @JoinColumn(name = "vital_signals_id")
    private VitalSignals vitalSignals;

}
