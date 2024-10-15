package com.hmvss.api.persistence.model;

import com.hmvss.api.util.converters.BooleanToSmallintConverter;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "users")
@NoArgsConstructor
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, length = 15)
    private Long id;

    @Column(nullable = false, length = 30)
    private String username;

    @Column(length = 60)
    private String password;

    @Column(columnDefinition = "smallint", nullable = false)
    @Convert(converter = BooleanToSmallintConverter.class)
    private boolean enabled;

    @Column(columnDefinition = "smallint")
    @Convert(converter = BooleanToSmallintConverter.class)
    private boolean locked;

    @Column(columnDefinition = "smallint")
    @Convert(converter = BooleanToSmallintConverter.class)
    private boolean expired;

    @Column(columnDefinition = "smallint")
    @Convert(converter = BooleanToSmallintConverter.class)
    private boolean credentialExpired;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "personal_data_id", referencedColumnName = "id")
    private PersonalData personalData;


}
