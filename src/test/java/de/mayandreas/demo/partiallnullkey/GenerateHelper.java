package de.mayandreas.demo.partiallnullkey;

import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;

import de.mayandreas.demo.partiallnullkey.domain.CompositeKey;
import de.mayandreas.demo.partiallnullkey.domain.EntityA;

public class GenerateHelper {
    public static List<EntityA> generateEntityAWithNullKeyPart(int anzahl) {
        return IntStream.range(0, anzahl).mapToObj(i ->
            {
                // ID
                var key = new CompositeKey();
                key.setNotNullKeyPart(UUID.randomUUID());

                // Entity
                var entity = new EntityA();
                entity.setKey(key);
                entity.setUuid(UUID.randomUUID());
                return entity;
            }).toList();
    }
}
