package com.cognizant.repositories;

import com.cognizant.entities.ClaimResponses;
import com.cognizant.entities.Claims;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClaimResponsesRepository extends JpaRepository<ClaimResponses, Integer> {
    Optional<ClaimResponses> findByClaim(Claims claim);
}