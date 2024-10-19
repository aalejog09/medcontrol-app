package com.hmvss.api.persistence.model;

import com.hmvss.api.util.converters.BooleanToSmallintConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


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
	private int id;

	@Column(name = "description")
	private String description;

	@Column(name = "creation_date")
	private Date creationDate;

	@Column(columnDefinition = "smallint", nullable = false)
	@Convert(converter = BooleanToSmallintConverter.class)
	private boolean enabled;

}
