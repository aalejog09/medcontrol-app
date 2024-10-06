package com.hmvss.api.persistence.model;

import com.hmvss.api.util.converters.BooleanToSmallintConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "medical_consultation")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class MedicalConsultation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, length = 15)
    private Long id;

    @Column(name = "code", nullable = false, length = 200)
    private String code;

    @Column(name = "reason", nullable = false, length = 200)
    private String reason;

    @Column(name = "diagnosis", nullable = false, length = 200)
    private String diagnosis;

    @Column(name = "treatment", nullable = false, length = 200)
    private String treatment;

    @Column(name = "registry_date", nullable = false)
    private Date registryDate;

    @Column(name="first_consult",columnDefinition = "smallint", nullable = false)
    @Convert(converter = BooleanToSmallintConverter.class)
    private boolean firstConsult;

    @ManyToOne
    @JoinColumn(name = "specialist_id")
    Specialist specialist;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patients patient;

    @OneToOne
    @JoinColumn(name = "vital_signals_id")
    private VitalSignals vitalSignals;

    //TODO
    // Examen fisico. 

}