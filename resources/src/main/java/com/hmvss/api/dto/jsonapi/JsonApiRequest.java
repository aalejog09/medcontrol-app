package com.hmvss.api.dto.jsonapi;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JsonApiRequest<T> implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;
	
	private JsonApiRequestData data;

	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public class JsonApiRequestData implements Serializable {
		@Serial
		private static final long serialVersionUID = 1L;
		private String type;
		private Integer id;
		private T attributes;
	}
}
