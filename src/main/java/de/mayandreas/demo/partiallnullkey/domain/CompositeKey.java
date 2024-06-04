package de.mayandreas.demo.partiallnullkey.domain;

import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Embeddable
@Data
public class CompositeKey implements Serializable {

    private UUID notNullKeyPart;

    private LocalDateTime nullKeyPart;
}
