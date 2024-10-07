package com.hmvss.api.persistence.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "Physical_exam")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PhysicalExam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 15)
    private double weight;

    @Column(name = "name", length = 15)
    private double height;

    @Column(name = "sistolicBloodPressure")
    private Integer sistolicBloodPressure;

    @Column(name = "diastolicBloodPressure")
    private Integer diastolicBloodPressure;

    @Column(name = "name", length = 15)
    private Integer heartRate;

    @Column(name = "name", length = 15)
    private Integer oxygenSaturation;

    @OneToOne(cascade = CascadeType.ALL)
    private PhysicalGeneralConditions physicalGeneralConditions;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "physicalExam")
    private List<BodyPart> bodyParts;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patients patient;

}
