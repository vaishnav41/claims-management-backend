package com.cognizant.repositories.test;

import com.cognizant.ClaimsManagementApplication;
import com.cognizant.entities.ClaimTypes;
import com.cognizant.entities.Claims;
import com.cognizant.repositories.ClaimsRepository;
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
class ClaimsRepositoryTest {

    @Autowired
    public ClaimsRepository claimsRepository;

    @Autowired
    public TestEntityManager entityManager;

    @Test
    void testFindAllPositive() {

        ClaimTypes claimTypes = new ClaimTypes();
        claimTypes.setClaimTypesId(100);
        claimTypes.setType("test");
        entityManager.persist(claimTypes);

        Claims claims = new Claims();
        claims.setPolicyId(200);
        claims.setUserName("username");
        claims.setSubscriptionId(456);
        claims.setClaimDate(LocalDate.now());
        claims.setClaimSummary("summary");
        claims.setClaimDetails("details");
        claims.setClaimStatus(ClaimStatus.New);
        claims.setRaisedByPolicyHolder(true);
        claims.setClaimantFullName("fullname");
        claims.setClaimantDateOfBirth(LocalDate.now().minusYears(5));
        claims.setClaimantAddress("address");
        claims.setClaimantIDProofType(ClaimantIDProofType.Passport);
        claims.setClaimantIDProofNumber("idnumber");
        claims.setResponseETA(LocalDate.now().plusYears(1));

        claims.setClaimType(claimTypes);

        entityManager.persist(claims);
        Iterable<Claims> it = claimsRepository.findAll();
        assertTrue(it.iterator().hasNext());
    }

    @Test
    void testFindAllNegative() {
        Iterable<Claims> it = claimsRepository.findAll();
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

        int id = entityManager.persist(claims).getClaimsId();
        Optional<Claims> op = claimsRepository.findById(id);
        assertTrue(op.isPresent());
    }

    @Test
    void testFindByIdNegative() {
        Optional<Claims> op = claimsRepository.findById(111);
        assertTrue(!op.isPresent());
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

        int id = claimsRepository.save(claims).getClaimsId();
        Optional<Claims> op = claimsRepository.findById(id);
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

        int id = entityManager.persist(claims).getClaimsId();
        claimsRepository.delete(claims);
        Optional<Claims> op = claimsRepository.findById(id);
        assertTrue(!op.isPresent());
    }


}