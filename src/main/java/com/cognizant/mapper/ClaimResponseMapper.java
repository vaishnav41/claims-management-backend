package com.cognizant.mapper;

import com.cognizant.dto.ClaimResponsesDTO;
import com.cognizant.entities.ClaimResponses;
import org.springframework.stereotype.Component;

@Component
public class ClaimResponseMapper {
    public ClaimResponsesDTO toClaimResponseDto(ClaimResponses claimResponse) {
        ClaimResponsesDTO claimResponsesDTO = new ClaimResponsesDTO();
        claimResponsesDTO.setClaimResponsesId(claimResponse.getClaimResponsesId());
        claimResponsesDTO.setResponseDate(claimResponse.getResponseDate());
        claimResponsesDTO.setResponseDetails(claimResponse.getResponseDetails());
        claimResponsesDTO.setClaimId(claimResponse.getClaim().getClaimsId());
        return claimResponsesDTO;
    }
}
