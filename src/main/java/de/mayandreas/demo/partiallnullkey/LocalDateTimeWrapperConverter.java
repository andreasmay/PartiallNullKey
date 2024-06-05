package de.mayandreas.demo.partiallnullkey;

import java.sql.Timestamp;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Converter(autoApply = true)
public class LocalDateTimeWrapperConverter implements AttributeConverter<LocalDateTimeWrapper, Timestamp> {

    @Override
    public Timestamp convertToDatabaseColumn(LocalDateTimeWrapper attribute) {
        if (attribute == null) {
            return null;
        }
        return attribute.getTimestamp();
    }

    @Override
    public LocalDateTimeWrapper convertToEntityAttribute(Timestamp dbData) {
        return new LocalDateTimeWrapper(dbData);

    }
}
