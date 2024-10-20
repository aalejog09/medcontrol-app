package com.hmvss.auth.persistence.model;

import com.hmvss.auth.util.converters.BooleanToSmallintConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;


/**
 * Clase persistente para identificar las funciones que pueden ser implementadas por el usuario dentro de la aplicaci�n.
 * @author LPinto
 *
 */

@Entity
@Table(name = "function")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Function {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "description")
	private String description;

	@Column(name = "creation_date")
	private Date creationDate;

	@ManyToMany(mappedBy = "functions",fetch = FetchType.EAGER)
	private Set<Role> roles;

	@Column(name = "enabled")
	@Convert(converter = BooleanToSmallintConverter.class)
	private boolean enabled;



}
