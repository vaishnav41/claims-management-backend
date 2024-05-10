package com.cognizant.services;

import com.cognizant.dto.ClaimResponsesDTO;
import com.cognizant.dto.ProcessRequestDTO;
import com.cognizant.entities.ClaimResponses;
import com.cognizant.entities.ClaimTypes;
import com.cognizant.entities.Claims;
import com.cognizant.exception.ClaimIdNotFoundException;
import com.cognizant.mapper.ClaimResponseMapper;
import com.cognizant.repositories.ClaimResponsesRepository;
import com.cognizant.repositories.ClaimsRepository;
import com.cognizant.service.ClaimResponsesServiceImpl;
import com.cognizant.utility.ClaimStatus;
import com.cognizant.utility.ClaimantIDProofType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class ClaimResponsesServiceImplTest {
    @Mock
    ClaimsRepository claimsRepository;
    @Mock
    ClaimResponsesRepository claimResponsesRepository;
    @Mock
    ClaimResponseMapper claimResponseMapper;
    @InjectMocks
    ClaimResponsesServiceImpl claimResponsesService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void processClaimRequest_Approve() {
        try {
            int claimId = 1;

            ClaimTypes claimTypes = new ClaimTypes();
            claimTypes.setClaimTypesId(1);
            claimTypes.setType("type");

            Claims claims = new Claims();
            claims.setClaimsId(1);
            claims.setPolicyId(2);
            claims.setUserName("user name");
            claims.setSubscriptionId(3);
            claims.setClaimDate(LocalDate.now());
            claims.setClaimType(claimTypes);
            claims.setClaimSummary("summary");
            claims.setClaimDetails("details");
            claims.setClaimStatus(ClaimStatus.New);
            claims.setRaisedByPolicyHolder(false);
            claims.setClaimantFullName("claimant name");
            claims.setClaimantDateOfBirth(LocalDate.of(1989, 03, 29));
            claims.setClaimantAddress("address");
            claims.setClaimantIDProofType(ClaimantIDProofType.Passport);
            claims.setClaimantIDProofNumber("id number");
            claims.setResponseETA(LocalDate.now().plusDays(7));

            ClaimResponses claimResponses = new ClaimResponses();
            claimResponses.setClaimResponsesId(1);
            claimResponses.setResponseDate(LocalDate.now());
            claimResponses.setResponseDetails("This is a new claim response details");
            claimResponses.setClaim(claims);

            ClaimResponsesDTO claimResponsesDto = new ClaimResponsesDTO();
            claimResponsesDto.setClaimResponsesId(1);
            claimResponsesDto.setResponseDate(LocalDate.now());
            claimResponsesDto.setResponseDetails("This is a new claim response details");
            claimResponsesDto.setClaimId(1);

            ProcessRequestDTO processRequestDTO = new ProcessRequestDTO();
            processRequestDTO.setClaimStatus(ClaimStatus.Approved);
            processRequestDTO.setResponseDetails("This claim request is approved by policy manager and the status is changed");

            when(claimsRepository.findById(1)).thenReturn(Optional.of(claims));
            when(claimResponsesRepository.findByClaim(any(Claims.class))).thenReturn(Optional.of(claimResponses));
            when(claimResponseMapper.toClaimResponseDto(claimResponses)).thenReturn(claimResponsesDto);

            ClaimResponsesDTO actualClaimResponseDto = claimResponsesService.processClaimRequest(claimId, processRequestDTO);

            assertEquals(claimResponsesDto, actualClaimResponseDto);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void processClaimRequest_ClaimIdNotFoundException() {

        int claimId = 2;

        ClaimTypes claimTypes = new ClaimTypes();
        claimTypes.setClaimTypesId(1);
        claimTypes.setType("type");

        Claims claims = new Claims();
        claims.setClaimsId(1);
        claims.setPolicyId(2);
        claims.setUserName("user name");
        claims.setSubscriptionId(3);
        claims.setClaimDate(LocalDate.now());
        claims.setClaimType(claimTypes);
        claims.setClaimSummary("summary");
        claims.setClaimDetails("details");
        claims.setClaimStatus(ClaimStatus.New);
        claims.setRaisedByPolicyHolder(false);
        claims.setClaimantFullName("claimant name");
        claims.setClaimantDateOfBirth(LocalDate.of(1989, 03, 29));
        claims.setClaimantAddress("address");
        claims.setClaimantIDProofType(ClaimantIDProofType.Passport);
        claims.setClaimantIDProofNumber("id number");
        claims.setResponseETA(LocalDate.now().plusDays(7));

        ClaimResponses claimResponses = new ClaimResponses();
        claimResponses.setClaimResponsesId(1);
        claimResponses.setResponseDate(LocalDate.now());
        claimResponses.setResponseDetails("This is a new claim response details");
        claimResponses.setClaim(claims);

        ClaimResponsesDTO claimResponsesDto = new ClaimResponsesDTO();
        claimResponsesDto.setClaimResponsesId(1);
        claimResponsesDto.setResponseDate(LocalDate.now());
        claimResponsesDto.setResponseDetails("This is a new claim response details");
        claimResponsesDto.setClaimId(1);

        ProcessRequestDTO processRequestDTO = new ProcessRequestDTO();
        processRequestDTO.setClaimStatus(ClaimStatus.Approved);
        processRequestDTO.setResponseDetails("This claim request is approved by policy manager and the status is changed");

        when(claimsRepository.findById(1)).thenReturn(Optional.of(claims));
        when(claimResponsesRepository.findByClaim(any(Claims.class))).thenReturn(Optional.of(claimResponses));
        when(claimResponseMapper.toClaimResponseDto(claimResponses)).thenReturn(claimResponsesDto);

        assertThrows(ClaimIdNotFoundException.class, () -> claimResponsesService.processClaimRequest(claimId, processRequestDTO));
    }
}
