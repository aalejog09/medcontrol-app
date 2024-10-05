package com.hmvss.auth.util.converters;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Converter(autoApply = true)
public class LocalDateTimeAttributeConverter implements AttributeConverter<LocalDateTime, String> {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    @Override
    public String convertToDatabaseColumn(LocalDateTime locDateTime) {
        return (locDateTime == null ? null : locDateTime.format(formatter));
    }

    @Override
    public LocalDateTime convertToEntityAttribute(String sqlDateTime) {
        return (sqlDateTime == null ? null : LocalDateTime.parse(sqlDateTime, formatter));
    }
}
