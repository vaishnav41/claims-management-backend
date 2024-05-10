package com.cognizant.services;

import com.cognizant.dto.ClaimsDTO;
import com.cognizant.entities.ClaimResponses;
import com.cognizant.entities.ClaimTypes;
import com.cognizant.entities.Claims;
import com.cognizant.exception.*;
import com.cognizant.mapper.ClaimsMapper;
import com.cognizant.repositories.ClaimResponsesRepository;
import com.cognizant.repositories.ClaimsRepository;
import com.cognizant.service.ClaimsServiceImpl;
import com.cognizant.utility.ClaimStatus;
import com.cognizant.utility.ClaimantIDProofType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ClaimsServiceImplTest {

    @Mock
    ClaimsRepository claimsRepository;
    @Mock
    ClaimResponsesRepository claimResponsesRepository;
    @Mock
    ClaimsMapper claimsMapper;
    @InjectMocks
    ClaimsServiceImpl claimsService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void fetchAllNewClaimRequests_Positive() {
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

        ClaimsDTO claimsDTO = new ClaimsDTO();
        claimsDTO.setClaimsId(1);
        claimsDTO.setPolicyId(2);
        claimsDTO.setUserName("user name");
        claimsDTO.setSubscriptionId(3);
        claimsDTO.setClaimDate(LocalDate.now());
        claimsDTO.setClaimTypeId(1);
        claimsDTO.setClaimSummary("summary");
        claimsDTO.setClaimDetails("details");
        claimsDTO.setClaimStatus(ClaimStatus.New);
        claimsDTO.setRaisedByPolicyHolder(false);
        claimsDTO.setClaimantFullName("claimant name");
        claimsDTO.setClaimantDateOfBirth(LocalDate.of(1989, 03, 29));
        claimsDTO.setClaimantAddress("address");
        claimsDTO.setClaimantIDProofType(ClaimantIDProofType.Passport);
        claimsDTO.setClaimantIDProofNumber("id number");
        claimsDTO.setResponseETA(LocalDate.now().plusDays(7));

        List<Claims> claimsList = new ArrayList<>();
        claimsList.add(claims);

        List<ClaimsDTO> claimsDTOList = new ArrayList<>();
        claimsDTOList.add(claimsDTO);

        when(claimsRepository.findAllByClaimStatus(ClaimStatus.New)).thenReturn(claimsList);
        when(claimsMapper.toClaimsDto(claims)).thenReturn(claimsDTO);

        List<ClaimsDTO> actualClaimDtoList = claimsService.fetchAllNewClaimRequests();

        assertEquals(actualClaimDtoList, claimsDTOList);
    }

    @Test
    void fetchAllNewClaimRequests_NoData() {
        when(claimsRepository.findAllByClaimStatus(ClaimStatus.New)).thenReturn(Collections.emptyList());

        List<ClaimsDTO> actualClaimsDtoList = claimsService.fetchAllNewClaimRequests();

        assertTrue(actualClaimsDtoList.isEmpty());
    }

    @Test
    void fetchClaimRequestById_Positive() {
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

        ClaimsDTO claimsDTO = new ClaimsDTO();
        claimsDTO.setClaimsId(1);
        claimsDTO.setPolicyId(2);
        claimsDTO.setUserName("user name");
        claimsDTO.setSubscriptionId(3);
        claimsDTO.setClaimDate(LocalDate.now());
        claimsDTO.setClaimTypeId(1);
        claimsDTO.setClaimSummary("summary");
        claimsDTO.setClaimDetails("details");
        claimsDTO.setClaimStatus(ClaimStatus.New);
        claimsDTO.setRaisedByPolicyHolder(false);
        claimsDTO.setClaimantFullName("claimant name");
        claimsDTO.setClaimantDateOfBirth(LocalDate.of(1989, 03, 29));
        claimsDTO.setClaimantAddress("address");
        claimsDTO.setClaimantIDProofType(ClaimantIDProofType.Passport);
        claimsDTO.setClaimantIDProofNumber("id number");
        claimsDTO.setResponseETA(LocalDate.now().plusDays(7));

        when(claimsRepository.findById(1)).thenReturn(Optional.of(claims));
        when(claimsMapper.toClaimsDto(claims)).thenReturn(claimsDTO);

        ClaimsDTO actualClaimsDto = claimsService.fetchClaimRequestById(1);

        assertEquals(actualClaimsDto, claimsDTO);
    }

    @Test
    void fetchClaimRequestById_Negative() {
        when(claimsRepository.findById(1)).thenReturn(Optional.empty());

        ClaimsDTO actualClaimsDto = claimsService.fetchClaimRequestById(2);

        assertNull(actualClaimsDto);
    }


    @Test
    void addNewClaimRequest_Positive() {
        try {
            ClaimsDTO claimsDTO = new ClaimsDTO();
            claimsDTO.setClaimsId(1);
            claimsDTO.setPolicyId(2);
            claimsDTO.setUserName("user name");
            claimsDTO.setSubscriptionId(3);
            claimsDTO.setClaimDate(LocalDate.now());
            claimsDTO.setClaimTypeId(1);
            claimsDTO.setClaimSummary("summary");
            claimsDTO.setClaimDetails("details");
            claimsDTO.setRaisedByPolicyHolder(false);
            claimsDTO.setClaimantFullName("claimant name");
            claimsDTO.setClaimantDateOfBirth(LocalDate.of(1989, 03, 29));
            claimsDTO.setClaimantAddress("address");
            claimsDTO.setClaimantIDProofType(ClaimantIDProofType.Passport);
            claimsDTO.setClaimantIDProofNumber("id number");
            claimsDTO.setResponseETA(LocalDate.now().plusDays(7));

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
            claims.setRaisedByPolicyHolder(false);
            claims.setClaimantFullName("claimant name");
            claims.setClaimantDateOfBirth(LocalDate.of(1989, 03, 29));
            claims.setClaimantAddress("address");
            claims.setClaimantIDProofType(ClaimantIDProofType.Passport);
            claims.setClaimantIDProofNumber("id number");
            claims.setResponseETA(LocalDate.now().plusDays(7));

            when(claimsRepository.findAllByPolicyIdAndClaimStatus(claimsDTO.getPolicyId(), ClaimStatus.Approved)).thenReturn(new ArrayList<>());
            when(claimsRepository.findAllByPolicyIdAndClaimStatus(claimsDTO.getPolicyId(), ClaimStatus.New)).thenReturn(new ArrayList<>());
            when(claimsMapper.toClaims(claimsDTO)).thenReturn(claims);

            String result = claimsService.addNewClaimRequest(claimsDTO);

            assertEquals("Success", result);
            verify(claimsRepository, times(1)).save(any(Claims.class));
            verify(claimResponsesRepository, times(1)).save(any(ClaimResponses.class));
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void addNewClaimRequest_Positive_ClaimTypeId2() {
        try {
            ClaimsDTO claimsDTO = new ClaimsDTO();
            claimsDTO.setClaimsId(1);
            claimsDTO.setPolicyId(2);
            claimsDTO.setUserName("user name");
            claimsDTO.setSubscriptionId(3);
            claimsDTO.setClaimDate(LocalDate.now());
            claimsDTO.setClaimTypeId(2);
            claimsDTO.setClaimSummary("summary");
            claimsDTO.setClaimDetails("details");
            claimsDTO.setRaisedByPolicyHolder(false);
            claimsDTO.setClaimantFullName("claimant name");
            claimsDTO.setClaimantDateOfBirth(LocalDate.of(1989, 03, 29));
            claimsDTO.setClaimantAddress("address");
            claimsDTO.setClaimantIDProofType(ClaimantIDProofType.Passport);
            claimsDTO.setClaimantIDProofNumber("id number");
            claimsDTO.setResponseETA(LocalDate.now().plusDays(7));

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
            claims.setRaisedByPolicyHolder(false);
            claims.setClaimantFullName("claimant name");
            claims.setClaimantDateOfBirth(LocalDate.of(1989, 03, 29));
            claims.setClaimantAddress("address");
            claims.setClaimantIDProofType(ClaimantIDProofType.Passport);
            claims.setClaimantIDProofNumber("id number");
            claims.setResponseETA(LocalDate.now().plusDays(7));

            when(claimsRepository.findAllByPolicyIdAndClaimStatus(claimsDTO.getPolicyId(), ClaimStatus.Approved)).thenReturn(new ArrayList<>());
            when(claimsRepository.findAllByPolicyIdAndClaimStatus(claimsDTO.getPolicyId(), ClaimStatus.New)).thenReturn(new ArrayList<>());
            when(claimsMapper.toClaims(claimsDTO)).thenReturn(claims);

            String result = claimsService.addNewClaimRequest(claimsDTO);

            assertEquals("Success", result);
            verify(claimsRepository, times(1)).save(any(Claims.class));
            verify(claimResponsesRepository, times(1)).save(any(ClaimResponses.class));
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void addNewClaimRequest_Positive_ClaimTypeId3() {
        try {
            ClaimsDTO claimsDTO = new ClaimsDTO();
            claimsDTO.setClaimsId(1);
            claimsDTO.setPolicyId(2);
            claimsDTO.setUserName("user name");
            claimsDTO.setSubscriptionId(3);
            claimsDTO.setClaimDate(LocalDate.now());
            claimsDTO.setClaimTypeId(3);
            claimsDTO.setClaimSummary("summary");
            claimsDTO.setClaimDetails("details");
            claimsDTO.setRaisedByPolicyHolder(false);
            claimsDTO.setClaimantFullName("claimant name");
            claimsDTO.setClaimantDateOfBirth(LocalDate.of(1989, 03, 29));
            claimsDTO.setClaimantAddress("address");
            claimsDTO.setClaimantIDProofType(ClaimantIDProofType.Passport);
            claimsDTO.setClaimantIDProofNumber("id number");
            claimsDTO.setResponseETA(LocalDate.now().plusDays(7));

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
            claims.setRaisedByPolicyHolder(false);
            claims.setClaimantFullName("claimant name");
            claims.setClaimantDateOfBirth(LocalDate.of(1989, 03, 29));
            claims.setClaimantAddress("address");
            claims.setClaimantIDProofType(ClaimantIDProofType.Passport);
            claims.setClaimantIDProofNumber("id number");
            claims.setResponseETA(LocalDate.now().plusDays(7));

            when(claimsRepository.findAllByPolicyIdAndClaimStatus(claimsDTO.getPolicyId(), ClaimStatus.Approved)).thenReturn(new ArrayList<>());
            when(claimsRepository.findAllByPolicyIdAndClaimStatus(claimsDTO.getPolicyId(), ClaimStatus.New)).thenReturn(new ArrayList<>());
            when(claimsMapper.toClaims(claimsDTO)).thenReturn(claims);

            String result = claimsService.addNewClaimRequest(claimsDTO);

            assertEquals("Success", result);
            verify(claimsRepository, times(1)).save(any(Claims.class));
            verify(claimResponsesRepository, times(1)).save(any(ClaimResponses.class));
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void addClaimRequest_PolicyAlreadyClaimedException() {

        ClaimsDTO claimDto = new ClaimsDTO();
        claimDto.setPolicyId(1);

        Claims claim = new Claims();
        when(claimsRepository.findAllByPolicyIdAndClaimStatus(claimDto.getPolicyId(), ClaimStatus.Approved)).thenReturn(Collections.singletonList(claim));

        assertThrows(PolicyAlreadyClaimedException.class, () -> claimsService.addNewClaimRequest(claimDto));
    }

    @Test
    void addClaimRequest_NewClaimExistException() {

        ClaimsDTO claimDto = new ClaimsDTO();
        claimDto.setPolicyId(1);

        Claims claim = new Claims();
        when(claimsRepository.findAllByPolicyIdAndClaimStatus(claimDto.getPolicyId(), ClaimStatus.Approved)).thenReturn(new ArrayList<>());
        when(claimsRepository.findAllByPolicyIdAndClaimStatus(claimDto.getPolicyId(), ClaimStatus.New)).thenReturn(Collections.singletonList(claim));

        assertThrows(NewClaimExistException.class, () -> claimsService.addNewClaimRequest(claimDto));
    }

    @Test
    void addClaimRequest_DeathClaimRaisedByPolicyHolderException(){
        ClaimsDTO claimDto = new ClaimsDTO();
        claimDto.setPolicyId(1);
        claimDto.setClaimTypeId(2);
        claimDto.setRaisedByPolicyHolder(true);

        Claims claim = new Claims();
        when(claimsRepository.findAllByPolicyIdAndClaimStatus(claimDto.getPolicyId(), ClaimStatus.Approved)).thenReturn(new ArrayList<>());
        when(claimsRepository.findAllByPolicyIdAndClaimStatus(claimDto.getPolicyId(), ClaimStatus.New)).thenReturn(new ArrayList<>());

        assertThrows(DeathClaimRaisedByPolicyHolderException.class, () -> claimsService.addNewClaimRequest(claimDto));

    }


    @Test
    void addClaimRequest_ClaimantDetailsNotProvidedException(){
        ClaimsDTO claimDto = new ClaimsDTO();
        claimDto.setPolicyId(1);
        claimDto.setClaimTypeId(2);
        claimDto.setRaisedByPolicyHolder(false);

        Claims claim = new Claims();
        when(claimsRepository.findAllByPolicyIdAndClaimStatus(claimDto.getPolicyId(), ClaimStatus.Approved)).thenReturn(new ArrayList<>());
        when(claimsRepository.findAllByPolicyIdAndClaimStatus(claimDto.getPolicyId(), ClaimStatus.New)).thenReturn(new ArrayList<>());

        assertThrows(ClaimantDetailsNotProvidedException.class, () -> claimsService.addNewClaimRequest(claimDto));

    }

    @Test
    void addClaimRequest_InvalidClaimTypeException(){
        ClaimsDTO claimDto = new ClaimsDTO();
        claimDto.setPolicyId(1);
        claimDto.setClaimTypeId(4);
        claimDto.setRaisedByPolicyHolder(false);

        Claims claim = new Claims();

        when(claimsRepository.findAllByPolicyIdAndClaimStatus(claimDto.getPolicyId(), ClaimStatus.Approved)).thenReturn(new ArrayList<>());
        when(claimsRepository.findAllByPolicyIdAndClaimStatus(claimDto.getPolicyId(), ClaimStatus.New)).thenReturn(new ArrayList<>());
        when(claimsMapper.toClaims(claimDto)).thenReturn(claim);

        assertThrows(InvalidClaimTypeException.class, () -> claimsService.addNewClaimRequest(claimDto));

    }
}