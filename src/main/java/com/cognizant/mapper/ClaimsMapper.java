package com.cognizant.mapper;

import com.cognizant.dto.ClaimsDTO;
import com.cognizant.entities.ClaimTypes;
import com.cognizant.entities.Claims;
import com.cognizant.repositories.ClaimTypesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Optional;

@Component
public class ClaimsMapper {

    @Autowired
    ClaimTypesRepository claimTypesRepository;

    public Claims toClaims(ClaimsDTO claimsDTO) {
        Claims claim = new Claims();

        claim.setPolicyId(claimsDTO.getPolicyId());
        claim.setUserName(claimsDTO.getUserName());
        claim.setSubscriptionId(claimsDTO.getSubscriptionId());

        Optional<ClaimTypes> optionalClaimTypes = claimTypesRepository.findById(claimsDTO.getClaimTypeId());
        ClaimTypes claimType = optionalClaimTypes.get();

        claim.setClaimType(claimType);

        claim.setClaimSummary(claimsDTO.getClaimSummary());
        claim.setClaimDetails(claimsDTO.getClaimDetails());
        claim.setRaisedByPolicyHolder(claimsDTO.isRaisedByPolicyHolder());
        claim.setClaimantFullName(claimsDTO.getClaimantFullName());
        claim.setClaimantDateOfBirth(claimsDTO.getClaimantDateOfBirth());
        claim.setClaimantAddress(claimsDTO.getClaimantAddress());
        claim.setClaimantIDProofType(claimsDTO.getClaimantIDProofType());
        claim.setClaimantIDProofNumber(claimsDTO.getClaimantIDProofNumber());

        return claim;
    }

    public ClaimsDTO toClaimsDto(Claims claim) {
        ClaimsDTO claimsDTO = new ClaimsDTO();

        claimsDTO.setClaimsId(claim.getClaimsId());
        claimsDTO.setPolicyId(claim.getPolicyId());
        claimsDTO.setUserName(claim.getUserName());
        claimsDTO.setSubscriptionId(claim.getSubscriptionId());
        claimsDTO.setClaimDate(claim.getClaimDate());
        claimsDTO.setClaimTypeId(claim.getClaimType().getClaimTypesId());
        claimsDTO.setClaimSummary(claim.getClaimSummary());
        claimsDTO.setClaimDetails(claim.getClaimDetails());
        claimsDTO.setClaimStatus(claim.getClaimStatus());
        claimsDTO.setRaisedByPolicyHolder(claim.isRaisedByPolicyHolder());
        claimsDTO.setClaimantFullName(claim.getClaimantFullName());
        claimsDTO.setClaimantDateOfBirth(claim.getClaimantDateOfBirth());
        claimsDTO.setClaimantAddress(claim.getClaimantAddress());
        claimsDTO.setClaimantIDProofType(claim.getClaimantIDProofType());
        claimsDTO.setClaimantIDProofNumber(claim.getClaimantIDProofNumber());
        claimsDTO.setResponseETA(claim.getResponseETA());

        return claimsDTO;
    }
}
