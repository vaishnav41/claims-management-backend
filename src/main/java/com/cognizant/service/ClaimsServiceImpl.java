package com.cognizant.service;

import com.cognizant.dto.ClaimsDTO;
import com.cognizant.entities.ClaimResponses;
import com.cognizant.entities.Claims;
import com.cognizant.exception.*;
import com.cognizant.mapper.ClaimsMapper;
import com.cognizant.repositories.ClaimResponsesRepository;
import com.cognizant.repositories.ClaimsRepository;
import com.cognizant.utility.ClaimStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Vaishnav
 *
 * This service class includes operations for adding a new claim request, fetching all new claim requests,
 * and fetching a specific claim request by its ID.
 *
 */
@Slf4j
@Service("claimServiceImpl")
public class ClaimsServiceImpl implements ClaimsService {

    ClaimsRepository claimsRepository;
    ClaimResponsesRepository claimResponsesRepository;
    ClaimsMapper claimsMapper;

    public ClaimsServiceImpl(ClaimsRepository claimsRepository, ClaimResponsesRepository claimResponsesRepository, ClaimsMapper claimsMapper) {
        this.claimsRepository = claimsRepository;
        this.claimResponsesRepository = claimResponsesRepository;
        this.claimsMapper = claimsMapper;
    }

    /**
     * This method is used to add a claim request.
     */
    @Override
    public String addNewClaimRequest(ClaimsDTO claimsDTO) throws Exception {
        Iterable<Claims> claimsApproved = claimsRepository.findAllByPolicyIdAndClaimStatus(claimsDTO.getPolicyId(), ClaimStatus.Approved);
        if (claimsApproved.iterator().hasNext()) {
            log.info("Throws PolicyAlreadyClaimedException");
            throw new PolicyAlreadyClaimedException("This policy already claimed");
        }

        Iterable<Claims> claimsByPolicyId = claimsRepository.findAllByPolicyIdAndClaimStatus(claimsDTO.getPolicyId(), ClaimStatus.New);
        if (claimsByPolicyId.iterator().hasNext()) {
            log.info("Throws NewClaimExistException");
            throw new NewClaimExistException("Claim request already exist for this policy");
        }

        if (claimsDTO.getClaimTypeId() == 2) {
            if (claimsDTO.isRaisedByPolicyHolder()) {
                log.info("Throws DeathClaimRaisedByPolicyHolderException");
                throw new DeathClaimRaisedByPolicyHolderException("Death claim cannot be requested by policy holder");
            }

            if (claimsDTO.getClaimantFullName() == null || claimsDTO.getClaimantDateOfBirth() == null || claimsDTO.getClaimantAddress() == null || claimsDTO.getClaimantIDProofType() == null || claimsDTO.getClaimantIDProofNumber() == null) {
                log.info("Throws ClaimantDetailsNotProvidedException");
                throw new ClaimantDetailsNotProvidedException("Add claimant details");
            }
        }

        Claims claim = claimsMapper.toClaims(claimsDTO);

        claim.setClaimDate(LocalDate.now());
        claim.setClaimStatus(ClaimStatus.New);

        switch (claimsDTO.getClaimTypeId()) {
            case 1 -> claim.setResponseETA(claim.getClaimDate().plusDays(7));
            case 2 -> claim.setResponseETA(claim.getClaimDate().plusDays(30));
            case 3 -> claim.setResponseETA(claim.getClaimDate().plusDays(15));
            default -> throw new InvalidClaimTypeException("Claim Type should be 1, 2, or 3");
        }
        claimsRepository.save(claim);

        ClaimResponses claimResponse = new ClaimResponses();
        claimResponse.setResponseDetails("Processing of this Claim has not done. Current status is New");
        claimResponse.setClaim(claim);
        claimResponse.setResponseDate(LocalDate.now());
        claimResponsesRepository.save(claimResponse);

        log.info("Successfully added claim request");
        return "Success";
    }

    /**
     * This method is used to fetch all new claim requests.
     */
    @Override
    public List<ClaimsDTO> fetchAllNewClaimRequests() {
        Iterable<Claims> iterableOfClaims = claimsRepository.findAllByClaimStatus(ClaimStatus.New);
        List<ClaimsDTO> claimsList = new ArrayList<>();
        for (Claims claim : iterableOfClaims) {
            ClaimsDTO claimsDTO = claimsMapper.toClaimsDto(claim);
            claimsList.add(claimsDTO);
        }
        log.info("Successfully fetched all claim requests");
        return claimsList;
    }

    /**
     * This method is used to fetch a claim request by id.
     */
    @Override
    public ClaimsDTO fetchClaimRequestById(int claimId) {
        Optional<Claims> optionalOfClaims = claimsRepository.findById(claimId);
        if (optionalOfClaims.isPresent()) {
            Claims claim = optionalOfClaims.get();
            log.info("Successfully fetched claim request by id");
            return claimsMapper.toClaimsDto(claim);
        } else {
            log.info("Fetch claim request by id failed");
            return null;
        }
    }
}
