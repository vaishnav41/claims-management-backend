package com.cognizant.repositories;

import com.cognizant.entities.Claims;
import com.cognizant.utility.ClaimStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClaimsRepository extends JpaRepository<Claims, Integer> {
    Iterable<Claims> findAllByClaimStatus(ClaimStatus claimStatus);
    Iterable<Claims> findAllByPolicyIdAndClaimStatus(int policyId, ClaimStatus claimStatus);
}
