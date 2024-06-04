package de.mayandreas.demo.partiallnullkey.repository;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.slf4j.helpers.MessageFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static de.mayandreas.demo.partiallnullkey.GenerateHelper.generateEntityAWithNullKeyPart;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@DataJpaTest
class EntityARepositoryTest {

    @Autowired
    private EntityARepository repo;

    @Test
    public void showProblem() {
        // given
        var anzahl = 5;
        repo.saveAll(generateEntityAWithNullKeyPart(anzahl));

        var entities = repo.findAll();

        assertEquals(anzahl, entities.size());
        assertAll(() -> assertEquals(anzahl, entities.size(),
                        MessageFormatter.format("Size should be {}", anzahl).getMessage()),
                () -> entities.forEach(entity -> assertNotNull(entity, "Entity should not be null")));
    }

}