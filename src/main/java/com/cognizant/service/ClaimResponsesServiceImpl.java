package com.cognizant.service;

import com.cognizant.dto.ClaimResponsesDTO;
import com.cognizant.dto.ProcessRequestDTO;
import com.cognizant.entities.ClaimResponses;
import com.cognizant.entities.Claims;
import com.cognizant.exception.ClaimIdNotFoundException;
import com.cognizant.mapper.ClaimResponseMapper;
import com.cognizant.repositories.ClaimResponsesRepository;
import com.cognizant.repositories.ClaimsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

/**
 * @author Vaishnav
 *
 * This service class includes operations for processing a specific claim request by its ID.
 */
@Slf4j
@Service
public class ClaimResponsesServiceImpl implements ClaimResponsesService {

    private ClaimsRepository claimsRepository;

    private ClaimResponsesRepository claimResponsesRepository;

    private ClaimResponseMapper claimResponseMapper;

    public ClaimResponsesServiceImpl(ClaimsRepository claimsRepository, ClaimResponsesRepository claimResponsesRepository, ClaimResponseMapper claimResponseMapper) {
        this.claimsRepository = claimsRepository;
        this.claimResponsesRepository = claimResponsesRepository;
        this.claimResponseMapper = claimResponseMapper;
    }

    /**
     * This method is used to process a claim request by its ID.
     */
    @Override
    public ClaimResponsesDTO processClaimRequest(int claimId, ProcessRequestDTO processRequestDTO) throws ClaimIdNotFoundException {
        Optional<Claims> optionalClaims = claimsRepository.findById(claimId);
        if (optionalClaims.isEmpty()) {
            log.info("Thrown ClaimIdNotFoundException");
            throw new ClaimIdNotFoundException("Claim Id not found");
        }
        Claims claim = optionalClaims.get();

        Optional<ClaimResponses> optionalClaimResponses = claimResponsesRepository.findByClaim(claim);
        if (optionalClaimResponses.isEmpty()) {
            log.info("Thrown ClaimIdNotFoundException");
            throw new ClaimIdNotFoundException("Claim id not found");
        }

        ClaimResponses claimResponse = optionalClaimResponses.get();
        claimResponse.getClaim().setClaimStatus(processRequestDTO.getClaimStatus());
        claimResponse.setResponseDate(LocalDate.now());
        claimResponse.setResponseDetails(processRequestDTO.getResponseDetails());
        claimResponsesRepository.save(claimResponse);

        log.info("Successfully processed claim request");
        return claimResponseMapper.toClaimResponseDto(claimResponse);
    }
}
