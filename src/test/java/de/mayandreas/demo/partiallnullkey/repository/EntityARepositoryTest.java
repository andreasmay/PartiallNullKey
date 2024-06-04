package de.mayandreas.demo.partiallnullkey.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class EntityARepositoryTest {

    @Autowired
    private EntityARepository repo;

    @BeforeEach
    void setup(){
        repo.save()
    }

    @Test
    public void showProblemTest(){

    }
}