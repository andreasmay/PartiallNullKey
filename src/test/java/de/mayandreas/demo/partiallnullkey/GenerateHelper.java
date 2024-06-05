package de.mayandreas.demo.partiallnullkey;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import de.mayandreas.demo.partiallnullkey.domain.CompositeKey;
import de.mayandreas.demo.partiallnullkey.domain.EntityA;

public class GenerateHelper {
    public static Stream<EntityA> generateEntityAWithNullKeyPart(boolean withNull, int anzahl) {
        return IntStream.range(0, anzahl).mapToObj(i ->
            {
                // ID
                var nullKeyPart = withNull ? null : LocalDateTime.now();
                var key = new CompositeKey(UUID.randomUUID(), nullKeyPart);

                // Entity
                var entity = new EntityA();
                entity.setKey(key);
                entity.setUuid(UUID.randomUUID());
                return entity;
            });
    }
}
