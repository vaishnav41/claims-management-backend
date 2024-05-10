package com.cognizant.service;

import com.cognizant.dto.ClaimTypesDTO;
import com.cognizant.entities.ClaimTypes;
import com.cognizant.mapper.ClaimTypesMapper;
import com.cognizant.repositories.ClaimTypesRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Vaishnav
 *
 * This service class includes operations for fetching all claim types.
 */
@Slf4j
@Service
public class ClaimTypesServiceImpl implements ClaimTypesService {

    private ClaimTypesRepository claimTypesRepository;
    private ClaimTypesMapper claimTypesMapper;

    public ClaimTypesServiceImpl(ClaimTypesRepository claimTypesRepository, ClaimTypesMapper claimTypesMapper) {
        this.claimTypesRepository = claimTypesRepository;
        this.claimTypesMapper = claimTypesMapper;
    }

    /**
     * This method is used to fetch all claim types.
     */
    public List<ClaimTypesDTO> fetchAllClaimTypes() {
        Iterable<ClaimTypes> iterableOfClaimTypes = claimTypesRepository.findAll();
        Iterator<ClaimTypes> claimTypesIterator = iterableOfClaimTypes.iterator();
        List<ClaimTypesDTO> claimTypesList = new ArrayList<>();
        while (claimTypesIterator.hasNext()) {
            ClaimTypes claimType = claimTypesIterator.next();
            ClaimTypesDTO claimTypesDTO = claimTypesMapper.toClaimTypesDto(claimType);
            claimTypesList.add(claimTypesDTO);
        }
        log.info("Successfully fetched all claim types");
        return claimTypesList;
    }
}
