package de.mayandreas.demo.partiallnullkey.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import de.mayandreas.demo.partiallnullkey.LocalDateTimeWrapper;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@EqualsAndHashCode
public class CompositeKey implements Serializable {

    private UUID notNullKeyPart;

    private LocalDateTimeWrapper nullKeyPart;

    public CompositeKey(UUID notNullKeyPart, LocalDateTime nullKeyPart) {
        this.notNullKeyPart = notNullKeyPart;
        this.nullKeyPart = new LocalDateTimeWrapper(nullKeyPart);
    }
}
