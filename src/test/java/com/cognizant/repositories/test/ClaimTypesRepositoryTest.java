package com.cognizant.repositories.test;

import com.cognizant.ClaimsManagementApplication;
import com.cognizant.entities.ClaimTypes;
import com.cognizant.repositories.ClaimTypesRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ContextConfiguration(classes = ClaimsManagementApplication.class)
class ClaimTypesRepositoryTest {

    @Autowired
    private ClaimTypesRepository claimTypesRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    void testFindAllPositive() {
        ClaimTypes claimTypes = new ClaimTypes();
        claimTypes.setClaimTypesId(100);
        claimTypes.setType("test");
        entityManager.persist(claimTypes);
        Iterable<ClaimTypes> it = claimTypesRepository.findAll();
        assertTrue(it.iterator().hasNext());
    }

    @Test
    void testFindAllNegative() {
        Iterable<ClaimTypes> it = claimTypesRepository.findAll();
        assertTrue(it.iterator().hasNext());
    }

    @Test
    void testFindByIdPositive() {
        ClaimTypes claimTypes = new ClaimTypes();
        claimTypes.setClaimTypesId(100);
        claimTypes.setType("test");
        int id = entityManager.persist(claimTypes).getClaimTypesId();
        Optional<ClaimTypes> op = claimTypesRepository.findById(100);
        assertTrue(op.isPresent());
    }
    @Test
    void testFindByIdNegative() {
        ClaimTypes claimTypes = new ClaimTypes();
        claimTypes.setClaimTypesId(1);
        claimTypes.setType("test");
        entityManager.persist(claimTypes);
        Optional<ClaimTypes> op = claimTypesRepository.findById(2);
        assertTrue(op.isPresent());
    }

    @Test
    void testSavePositive() {
        ClaimTypes claimTypes = new ClaimTypes();
        claimTypes.setClaimTypesId(1);
        claimTypes.setType("test");

        entityManager.persist(claimTypes);

        int id = claimTypesRepository.save(claimTypes).getClaimTypesId();
        Optional<ClaimTypes> op = claimTypesRepository.findById(id);
        assertTrue(op.isPresent());
    }

    @Test
    void testDeletePositive() {
        ClaimTypes claimTypes = new ClaimTypes();
        claimTypes.setClaimTypesId(1);
        claimTypes.setType("test");
        int id = entityManager.persist(claimTypes).getClaimTypesId();
        claimTypesRepository.delete(claimTypes);
        Optional<ClaimTypes> op = claimTypesRepository.findById(1);
        assertTrue(!op.isPresent());
    }

    @Test
    void testCountPositive()
    {
        long actualCount=claimTypesRepository.count();
        long expectedCount=3;
        assertEquals(actualCount,expectedCount);
    }
}
