package com.hmvss.api.persistence.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;


@Entity
@Table(name = "personal_data")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PersonalData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, length = 15)
    private Long id;

    @Column(nullable = false, length = 30)
    private String names;

    @Column(length = 60)
    private String lastnames;

    @Column(length = 30)
    private String sex;

    @Column(name = "born_date")
    private Date bornDate;

    @Column(name = "civil_state" ,length = 30)
    private String civilState;

    @Column(name = "dni", length = 30, unique = true)
    private String identificationDocumentNumber;

    @Column(name = "profession", length = 100)
    private String profession;

    @Column(name = "education_level", length= 30)
    private String educationLevel;

    @Column(name = "occupation", length= 30)
    private String occupation;

    @Column(name = "nationality", length= 30)
    private String nationality;

    @ManyToOne
    @JoinColumn(name = "location_id", nullable = false)
    private Location location;

    @ManyToOne
    @JoinColumn(name = "contact_id", nullable = false)
    private Contact contact;

    @Column(name = "registry_date", nullable = false)
    private Date registryDate;


}
