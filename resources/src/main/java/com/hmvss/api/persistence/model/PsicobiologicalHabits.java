package com.hmvss.api.persistence.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * Clase que representa un habito PsicoBiologico de un paciente en una historia medica
 */
@Entity
@Table(name = "piscobiological_habits")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PsicobiologicalHabits {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, length = 15)
    private Long id;

    @Column(name = "habit",  length = 200)
    private String habit;

    @Column(name = "frequency")
    private Integer frequency;

    @Column(name = "frequency_rate",  length = 200)
    private String frequencyRate;

    @Column(name = "quantity", length = 200)
    private Integer quantity;

    @Column(name = "quantity_rate", length = 200)
    private Integer quantityRate;

    @ManyToOne
    @JoinColumn(name = "patients_id")
    private Patients patient;

}
