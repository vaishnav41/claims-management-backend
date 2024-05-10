package com.cognizant.service;

import com.cognizant.dto.ClaimTypesDTO;

import java.util.List;

public interface ClaimTypesService {
    List<ClaimTypesDTO> fetchAllClaimTypes();
}