package de.mayandreas.demo.partiallnullkey.repository;

import de.mayandreas.demo.partiallnullkey.domain.CompositeKey;
import de.mayandreas.demo.partiallnullkey.domain.EntityA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntityARepository extends JpaRepository<EntityA, CompositeKey> {}
