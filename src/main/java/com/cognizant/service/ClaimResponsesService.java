package com.cognizant.service;

import com.cognizant.dto.ClaimResponsesDTO;
import com.cognizant.dto.ProcessRequestDTO;
import com.cognizant.exception.ClaimIdNotFoundException;

public interface ClaimResponsesService {
    ClaimResponsesDTO processClaimRequest(int claimId, ProcessRequestDTO processRequestDTO) throws ClaimIdNotFoundException;
}