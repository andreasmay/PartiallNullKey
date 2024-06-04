package de.mayandreas.demo.partiallnullkey.domain;

import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Embeddable
@Data
public class CompositeKey implements Serializable {

    private int integerKeyPart;

    private LocalDateTime localDateTimeKeyPart;
}
