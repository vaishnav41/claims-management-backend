package com.cognizant.service;

import com.cognizant.dto.ClaimsDTO;

import java.util.List;

public interface ClaimsService {
    String addNewClaimRequest(ClaimsDTO claimsDTO) throws Exception;

    List<ClaimsDTO> fetchAllNewClaimRequests();

    ClaimsDTO fetchClaimRequestById(int claimId);
}
