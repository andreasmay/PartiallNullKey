package de.mayandreas.demo.partiallnullkey.repository;

import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Stream;

import de.mayandreas.demo.partiallnullkey.domain.EntityA;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.slf4j.helpers.MessageFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static de.mayandreas.demo.partiallnullkey.GenerateHelper.generateEntityAWithNullKeyPart;
import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@DataJpaTest
class EntityARepositoryTest {

    @Autowired
    private EntityARepository repo;

    @Nested
    class FindAllTests {
        @Test
        public void pkNullShouldBeFound() {
            // given
            var anzahl = 5;
            repo.saveAll(generateEntityAWithNullKeyPart(true, anzahl).toList());

            // when
            var entities = repo.findAll();

            // then
            assertAll(() -> assertEquals(anzahl, entities.size(),
                            MessageFormatter.format("Size should be {}", anzahl).getMessage()),
                    () -> entities.forEach(entity -> assertNotNull(entity, "Entity should not be null")));
        }

        @Test
        public void pkNotNullShouldBeFound() {
            // given
            var anzahl = 5;
            repo.saveAll(generateEntityAWithNullKeyPart(false, anzahl).toList());

            // when
            var entities = repo.findAll();

            // then
            assertAll(() -> assertEquals(anzahl, entities.size(),
                            MessageFormatter.format("Size should be {}", anzahl).getMessage()),
                    () -> entities.forEach(entity -> assertNotNull(entity, "Entity should not be null")));
        }

        @Test
        public void pkMixedShouldBeFound() {
            // given
            var anzahl = 2;
            repo.saveAll(generateEntityAWithNullKeyPart(false, anzahl).toList());
            repo.saveAll(generateEntityAWithNullKeyPart(true, anzahl).toList());

            // when
            var entities = repo.findAll();

            // then
            assertAll(() -> assertEquals(anzahl * 2, entities.size(),
                            MessageFormatter.format("Size should be {}", anzahl * 2).getMessage()),
                    () -> entities.forEach(entity -> assertNotNull(entity, "Entity should not be null")));
        }
    }

    @Nested
    class FindById {
        @Test
        public void pkNullShouldBeFound() {
            // given
            Stream<EntityA> withNull = generateEntityAWithNullKeyPart(false, 1);
            Stream<EntityA> withOutNull = generateEntityAWithNullKeyPart(true, 1);

            var givenEntities = Stream.concat(withNull, withOutNull).collect(toList());
            repo.saveAll(givenEntities);

            var entityToFind = givenEntities.stream().filter(nullKeyPartIsNull()).findFirst().orElseThrow();

            // when
            var entity = repo.findById(entityToFind.getKey());

            //then
            assertAll(() -> assertTrue(entity.isPresent(), "Entity should be present"),
                    () -> assertEquals(entityToFind, entity.orElseThrow(), "Entity should be equal"));
        }

        @Test
        public void pkNotNullShouldBeFound() {
            // given
            Stream<EntityA> withNull = generateEntityAWithNullKeyPart(false, 1);
            Stream<EntityA> withOutNull = generateEntityAWithNullKeyPart(true, 1);
            var givenEntities = Stream.concat(withNull, withOutNull).collect(toList());
            repo.saveAll(givenEntities);

            var entityToFind = givenEntities.stream().filter(nullKeyPartIsNonNull()).findFirst().orElseThrow();

            // when
            var entity = repo.findById(entityToFind.getKey());

            // then
            assertAll(() -> assertTrue(entity.isPresent(), "Entity should be present"),
                    () -> assertEquals(entityToFind, entity.orElseThrow(), "Entity should be equal"));
        }
    }

    @Nested
    class DeleteTests {
        @Test
        public void pkNullShouldBeDeleted() {
            // given
            var withNull = generateEntityAWithNullKeyPart(true, 1);
            var withOutNull = generateEntityAWithNullKeyPart(false, 1);
            var givenEntities = Stream.concat(withNull, withOutNull).collect(toList());
            repo.saveAll(givenEntities);

            var entityToDelete = givenEntities.stream().filter(nullKeyPartIsNull()).findFirst().orElseThrow();

            // when
            repo.delete(entityToDelete);

            // then
            var entity = repo.findById(entityToDelete.getKey());
            assertFalse(entity.isPresent(), "Entity should not be present");
        }

        @Test
        public void pkNotNullShouldBeDeleted() {
            // given
            var withNull = generateEntityAWithNullKeyPart(true, 1);
            var withOutNull = generateEntityAWithNullKeyPart(false, 1);
            var givenEntities = Stream.concat(withNull, withOutNull).collect(toList());
            repo.saveAll(givenEntities);

            var entityToDelete = givenEntities.stream().filter(nullKeyPartIsNonNull()).findFirst().orElseThrow();
            // when
            repo.delete(entityToDelete);

            // then
            var entity = repo.findById(entityToDelete.getKey());
            assertFalse(entity.isPresent(), "Entity should not be present");
        }
    }

    private static Predicate<EntityA> nullKeyPartIsNull() {
        return e -> Objects.isNull(e.getKey().getNullKeyPart().getValue());
    }

    private static Predicate<EntityA> nullKeyPartIsNonNull() {
        return e -> Objects.nonNull(e.getKey().getNullKeyPart().getValue());
    }
}