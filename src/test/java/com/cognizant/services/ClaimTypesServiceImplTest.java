package com.cognizant.services;

import com.cognizant.dto.ClaimTypesDTO;
import com.cognizant.entities.ClaimTypes;
import com.cognizant.mapper.ClaimTypesMapper;
import com.cognizant.repositories.ClaimTypesRepository;
import com.cognizant.service.ClaimTypesServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

class ClaimTypesServiceImplTest {

    @Mock
    private ClaimTypesRepository claimTypesRepository;

    @Mock
    private ClaimTypesMapper claimTypesMapper;

    @InjectMocks
    private ClaimTypesServiceImpl claimTypesService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void fetchAllClaimTypes_Positive() {
        ClaimTypes claimType1 = new ClaimTypes();
        claimType1.setClaimTypesId(1);
        claimType1.setType("type1");

        ClaimTypes claimType2 = new ClaimTypes();
        claimType2.setClaimTypesId(2);
        claimType2.setType("type2");

        ClaimTypesDTO claimTypesDTO1 = new ClaimTypesDTO();
        claimTypesDTO1.setClaimTypesId(1);
        claimTypesDTO1.setType("type1");

        ClaimTypesDTO claimTypesDTO2 = new ClaimTypesDTO();
        claimTypesDTO2.setClaimTypesId(2);
        claimTypesDTO2.setType("type2");

        when(claimTypesRepository.findAll()).thenReturn(Arrays.asList(claimType1, claimType2));
        when(claimTypesMapper.toClaimTypesDto(claimType1)).thenReturn(claimTypesDTO1);
        when(claimTypesMapper.toClaimTypesDto(claimType2)).thenReturn(claimTypesDTO2);

        List<ClaimTypesDTO> expectedClaimTypesDTO = Arrays.asList(claimTypesDTO1, claimTypesDTO2);

        List<ClaimTypesDTO> actualClaimTypesDTO = claimTypesService.fetchAllClaimTypes();

        assertEquals(expectedClaimTypesDTO, actualClaimTypesDTO);
    }

    @Test
    void fetchAllClaimTypes_NoDataFound() {
        when(claimTypesRepository.findAll()).thenReturn(Collections.emptyList());

        List<ClaimTypesDTO> actualClaimTypes = claimTypesService.fetchAllClaimTypes();

        assertTrue(actualClaimTypes.isEmpty());
    }
}