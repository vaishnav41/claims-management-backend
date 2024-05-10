package com.cognizant.mapper;

import com.cognizant.dto.ClaimTypesDTO;
import com.cognizant.entities.ClaimTypes;
import org.springframework.stereotype.Component;

@Component
public class ClaimTypesMapper {
    public ClaimTypesDTO toClaimTypesDto(ClaimTypes claimType) {
        ClaimTypesDTO claimTypesDTO = new ClaimTypesDTO();
        claimTypesDTO.setClaimTypesId(claimType.getClaimTypesId());
        claimTypesDTO.setType(claimType.getType());
        return claimTypesDTO;
    }
}
