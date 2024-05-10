package com.cognizant.repositories.test;

import com.cognizant.ClaimsManagementApplication;
import com.cognizant.entities.ClaimResponses;
import com.cognizant.entities.ClaimTypes;
import com.cognizant.entities.Claims;
import com.cognizant.repositories.ClaimResponsesRepository;
import com.cognizant.utility.ClaimStatus;
import com.cognizant.utility.ClaimantIDProofType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@ContextConfiguration(classes = ClaimsManagementApplication.class)
class ClaimResponsesRepositoryTest {

    @Autowired
    private ClaimResponsesRepository claimResponsesRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    void testFindAllPositive() {

        ClaimTypes claimTypes = new ClaimTypes();
        claimTypes.setClaimTypesId(100);
        claimTypes.setType("test");
        entityManager.persist(claimTypes);

        Claims claims = new Claims();
        claims.setPolicyId(200);
        claims.setUserName("John");
        claims.setSubscriptionId(456);
        claims.setClaimDate(LocalDate.now());
        claims.setClaimSummary("summary");
        claims.setClaimDetails("details");
        claims.setClaimStatus(ClaimStatus.New);
        claims.setRaisedByPolicyHolder(true);
        claims.setClaimantFullName("John Honai");
        claims.setClaimantDateOfBirth(LocalDate.now().minusYears(5));
        claims.setClaimantAddress("Aanappara");
        claims.setClaimantIDProofType(ClaimantIDProofType.Passport);
        claims.setClaimantIDProofNumber("idnumber");
        claims.setResponseETA(LocalDate.now().plusYears(1));

        claims.setClaimType(claimTypes);

        entityManager.persist(claims);

        ClaimResponses claimResponses = new ClaimResponses();
        claimResponses.setResponseDate(LocalDate.now());
        claimResponses.setResponseDetails("details");

        claimResponses.setClaim(claims);

        entityManager.persist(claimResponses);
        Iterable<ClaimResponses> it = claimResponsesRepository.findAll();
        assertTrue(it.iterator().hasNext());
    }

    @Test
    void testFindAllNegative() {
        Iterable<ClaimResponses> it = claimResponsesRepository.findAll();
        assertTrue(!it.iterator().hasNext());
    }

    @Test
    void testFindByIdPositive() {

        ClaimTypes claimTypes = new ClaimTypes();
        claimTypes.setClaimTypesId(100);
        claimTypes.setType("test");
        entityManager.persist(claimTypes);

        Claims claims = new Claims();
        claims.setPolicyId(200);
        claims.setUserName("John");
        claims.setSubscriptionId(456);
        claims.setClaimDate(LocalDate.now());
        claims.setClaimSummary("summary");
        claims.setClaimDetails("details");
        claims.setClaimStatus(ClaimStatus.New);
        claims.setRaisedByPolicyHolder(true);
        claims.setClaimantFullName("John Honai");
        claims.setClaimantDateOfBirth(LocalDate.now().minusYears(5));
        claims.setClaimantAddress("Aanappara");
        claims.setClaimantIDProofType(ClaimantIDProofType.Passport);
        claims.setClaimantIDProofNumber("idnumber");
        claims.setResponseETA(LocalDate.now().plusYears(1));

        claims.setClaimType(claimTypes);

        entityManager.persist(claims);

        ClaimResponses claimResponses = new ClaimResponses();
        claimResponses.setResponseDate(LocalDate.now());
        claimResponses.setResponseDetails("details");

        claimResponses.setClaim(claims);

        int id = entityManager.persist(claimResponses).getClaimResponsesId();
        Optional<ClaimResponses> op = claimResponsesRepository.findById(id);
        assertTrue(op.isPresent());
    }

    @Test
    void testFindByIdNegative() {
        Optional<ClaimResponses> op = claimResponsesRepository.findById(123);
        assertTrue(op.isEmpty());
    }

    @Test
    void testSavePositive() {


        ClaimTypes claimTypes = new ClaimTypes();
        claimTypes.setClaimTypesId(100);
        claimTypes.setType("test");
        entityManager.persist(claimTypes);

        Claims claims = new Claims();
        claims.setPolicyId(200);
        claims.setUserName("John");
        claims.setSubscriptionId(456);
        claims.setClaimDate(LocalDate.now());
        claims.setClaimSummary("summary");
        claims.setClaimDetails("details");
        claims.setClaimStatus(ClaimStatus.New);
        claims.setRaisedByPolicyHolder(true);
        claims.setClaimantFullName("John Honai");
        claims.setClaimantDateOfBirth(LocalDate.now().minusYears(5));
        claims.setClaimantAddress("Aanappara");
        claims.setClaimantIDProofType(ClaimantIDProofType.Passport);
        claims.setClaimantIDProofNumber("idnumber");
        claims.setResponseETA(LocalDate.now().plusYears(1));

        claims.setClaimType(claimTypes);

        entityManager.persist(claims);

        ClaimResponses claimResponses = new ClaimResponses();
        claimResponses.setResponseDate(LocalDate.now());
        claimResponses.setResponseDetails("details");

        claimResponses.setClaim(claims);

        entityManager.persist(claimResponses);
        int id = claimResponsesRepository.save(claimResponses).getClaimResponsesId();
        Optional<ClaimResponses> op = claimResponsesRepository.findById(id);
        assertTrue(op.isPresent());
    }

    @Test
    void testDeletePositive() {
        ClaimTypes claimTypes = new ClaimTypes();
        claimTypes.setClaimTypesId(100);
        claimTypes.setType("test");
        entityManager.persist(claimTypes);

        Claims claims = new Claims();
        claims.setPolicyId(200);
        claims.setUserName("John");
        claims.setSubscriptionId(456);
        claims.setClaimDate(LocalDate.now());
        claims.setClaimSummary("summary");
        claims.setClaimDetails("details");
        claims.setClaimStatus(ClaimStatus.New);
        claims.setRaisedByPolicyHolder(true);
        claims.setClaimantFullName("John Honai");
        claims.setClaimantDateOfBirth(LocalDate.now().minusYears(5));
        claims.setClaimantAddress("Aanappara");
        claims.setClaimantIDProofType(ClaimantIDProofType.Passport);
        claims.setClaimantIDProofNumber("idnumber");
        claims.setResponseETA(LocalDate.now().plusYears(1));

        claims.setClaimType(claimTypes);

        entityManager.persist(claims);

        ClaimResponses claimResponses = new ClaimResponses();
        claimResponses.setResponseDate(LocalDate.now());
        claimResponses.setResponseDetails("details");

        claimResponses.setClaim(claims);

        int id = entityManager.persist(claimResponses).getClaimResponsesId();
        claimResponsesRepository.delete(claimResponses);
        Optional<ClaimResponses> op = claimResponsesRepository.findById(id);
        assertTrue(op.isEmpty());
    }
}
