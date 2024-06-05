package de.mayandreas.demo.partiallnullkey;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class LocalDateTimeWrapper implements Serializable {

    private final LocalDateTime value;

    public LocalDateTimeWrapper(LocalDateTime value) {
        this.value = value;
    }

    public LocalDateTimeWrapper(Timestamp value) {
        this.value = value == null ? null : value.toLocalDateTime();
    }

    public LocalDateTime getValue() {
        return value;
    }

    public Timestamp getTimestamp() {
        return value == null ? null : Timestamp.valueOf(value);
    }
}
