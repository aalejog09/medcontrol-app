package com.hmvss.api.persistence.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "lab_orders")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class LabOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, length = 15)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "date", nullable = false)
    private LocalDate date;


    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patients patient;

    @ManyToMany
    @JoinTable(
        name = "lab_order_laboratories",
        joinColumns = @JoinColumn(name = "lab_order_id"),
        inverseJoinColumns = @JoinColumn(name = "laboratory_id")
    )
    private List<Laboratory> laboratories;

}
