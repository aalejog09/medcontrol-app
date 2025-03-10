package com.hmvss.auth.persistence.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "contact_data")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, length = 15)
    private Long id;

    @Column(name= "phone_number_principal", length = 30)
    private String principalPhone;

    @Column(name= "additional_phone_number", length = 30)
    private String additionalPhone;

    @Column(name= "email_principal", length = 45,unique = true)
    private String email;

    @Column(name= "additional_email", length = 45)
    private String additionalEmail;

}
