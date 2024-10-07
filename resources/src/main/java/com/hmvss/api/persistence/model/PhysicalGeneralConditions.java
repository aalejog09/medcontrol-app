package com.hmvss.api.persistence.model;

import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@Entity
@Table(name = "physical_general_condition")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PhysicalGeneralConditions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "id")
    private Long id;

    @Column(name= "state", length = 30)
    private String state;

    @Column(name= "hydration", length = 30)
    private String hydration;

    @Column(name= "breathing", length = 30)
    private String breathing;

    @Column(name= "position", length = 30)
    private String position;

    @Column(name= "temperature", length = 30)
    private String temperature;

    @Column(name= "capillaryFilling", length = 30)
    private String capillaryFilling;

}
