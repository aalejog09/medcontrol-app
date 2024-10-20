package com.hmvss.api.persistence.model;

import com.hmvss.api.util.converters.BooleanToSmallintConverter;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Data
@Entity
@Table(name = "role")
public class Role implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "int4")
    private Long Id;

    @Column(name = "name", unique = true)
    private String roleName;

    @Column(name = "description")
    private String description;

    @Column(name = "creation_date")
    private Date creationDate;

    @Column(columnDefinition = "smallint")
    @Convert(converter = BooleanToSmallintConverter.class)
    private boolean enabled;

    @OneToMany(mappedBy = "role", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<RoleFunction> roleFunctions;

}