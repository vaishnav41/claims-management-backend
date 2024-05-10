package com.cognizant.repositories;

import com.cognizant.entities.ClaimTypes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClaimTypesRepository extends JpaRepository<ClaimTypes, Integer> {
}