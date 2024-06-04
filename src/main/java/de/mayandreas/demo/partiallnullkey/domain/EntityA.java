package de.mayandreas.demo.partiallnullkey.domain;

import java.util.UUID;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class EntityA {
    @EmbeddedId
    private CompositeKey key;

    private UUID uuid;
}
