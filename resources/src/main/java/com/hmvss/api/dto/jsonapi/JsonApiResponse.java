package com.hmvss.api.dto.jsonapi;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class JsonApiResponse<T> {

	public static class Object<T> {
		private DataJson<T> data;
		public Object(T attributes, String id, String type){
			if(attributes == null){
				this.data = null;
			}else{
				this.data = new DataJson<>(attributes, id, type);
			}
		}
		public DataJson<T> getData() {
			return data;
		}

		public void setData(DataJson<T> data) {
			this.data = data;
		}

		public static class DataJson<T> {
			private T attributes;
			private String id;
			private String type;

			public DataJson(T attributes, String id, String type) {
				this.attributes = attributes;
				this.id = id;
				this.type = type;
			}

			public DataJson(T attributes, String type) {
				this.attributes = attributes;
				this.type = type;
			}

			public T getAttributes() {
				return attributes;
			}

			public void setAttributes(T attributes) {
				this.attributes = attributes;
			}

			public String getId() {
				return id;
			}

			public void setId(String id) {
				this.id = id;
			}

			public String getType() {
				return type;
			}

			public void setType(String type) {
				this.type = type;
			}
		}
	}


	public static class List<T> {
		private java.util.List<Object.DataJson<T>> data;

		@SuppressWarnings("unchecked")
		public List( T data, String type, String id){
			java.util.List<Object.DataJson<T>> dataList = new ArrayList<>();

			java.util.List<T> list = (java.util.List<T>) data;

			for (T element : list) {
				Map<String, java.lang.Object> obj = this.convertObjectToMap(element);
				Object.DataJson<T> json = new Object.DataJson<T>(element, obj.get(id).toString(), type);
				dataList.add(json);
			}
			this.data = dataList;
		}

		@SuppressWarnings("unchecked")
		public List( T data, String type ){
			java.util.List<Object.DataJson<T>> dataList = new ArrayList<>();

			java.util.List<T> list = (java.util.List<T>) data;

			for (T element : list) {
				Map<String, java.lang.Object> obj = this.convertObjectToMap(element);
				Object.DataJson<T> json = new Object.DataJson<T>(element, type);
				dataList.add(json);
			}
			this.data = dataList;
		}

		public Map<String, java.lang.Object> convertObjectToMap(java.lang.Object object){
			Map<String, java.lang.Object> dataReturn = new HashMap<>();
			Class<?> classObject = object.getClass();
			Field[] fields = classObject.getDeclaredFields();
			for (Field field : fields) {
				try {
					field.setAccessible(true);
					String nameField = field.getName();
					java.lang.Object valueField = field.get(object);
					dataReturn.put(nameField, valueField);
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
			return dataReturn;
		}


	}

}
