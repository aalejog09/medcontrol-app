package com.hmvss.auth.persistence.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "location_data")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, length = 15)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "city_id", nullable = false)
    private City city;

    @Column(name = "housing", nullable = false, length = 200)
    private String housing;

    @Column(name = "additional_location_info", nullable = false, length = 200)
    private String additionalInfo;

}
