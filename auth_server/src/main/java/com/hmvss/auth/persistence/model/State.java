package com.hmvss.auth.persistence.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase persistente para representar la informacion de un estado.
 */
@Entity
@Table(name = "state")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class State {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, length = 15)
    private Long id;

    @Column(name = "stateName", nullable = false, length = 250)
    private String stateName;

    @Column(name = "iso_3166_2_code", nullable = false, length = 4)
    private String iso3166Code;

}
