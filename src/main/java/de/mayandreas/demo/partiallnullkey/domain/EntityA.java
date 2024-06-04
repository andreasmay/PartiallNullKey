package de.mayandreas.demo.partiallnullkey.domain;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;

@Entity
public class EntityA {
    @EmbeddedId
    private CompositeKey key;

    private String valueString;

    private Integer valueInteger;
}
