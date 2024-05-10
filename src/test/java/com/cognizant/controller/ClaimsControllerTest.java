package com.cognizant.controller;

import com.cognizant.dto.ClaimsDTO;
import com.cognizant.service.ClaimsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.mock;

class ClaimsControllerTest {

    @InjectMocks
    ClaimsController claimsController;

    @Mock
    ClaimsService claimsService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getNewClaimRequests_Positive() {
        ClaimsDTO claim1 = new ClaimsDTO();
        ClaimsDTO claim2 = new ClaimsDTO();
        List<ClaimsDTO> expectedClaims = Arrays.asList(claim1, claim2);
        when(claimsService.fetchAllNewClaimRequests()).thenReturn(expectedClaims);

        ResponseEntity<?> response = claimsController.getNewClaimRequests();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedClaims, response.getBody());
    }


    @Test
    void createClaimRequest_Positive() throws Exception {

        ClaimsDTO claimsDTO = new ClaimsDTO();
        claimsDTO.setPolicyId(3);

        when(claimsService.addNewClaimRequest(claimsDTO)).thenReturn("Success");

        ResponseEntity<?> response = claimsController.createClaimRequest(claimsDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Created Claim Request",response.getBody());

    }


    @Test
    void getClaimRequestsById_Positive() {
        ClaimsDTO claim1 = new ClaimsDTO();
        claim1.setClaimsId(1);
        ClaimsDTO expectedClaims = claim1;
        when(claimsService.fetchClaimRequestById(1)).thenReturn(claim1);

        ResponseEntity<?> response = claimsController.getClaimRequestById(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedClaims, response.getBody());
    }

    @Test
    void getClaimRequestsById_Negative() {
        ClaimsDTO claim1 = new ClaimsDTO();
        claim1.setClaimsId(1);
        ClaimsDTO expectedClaims = claim1;
        when(claimsService.fetchClaimRequestById(1)).thenReturn(null);

        ResponseEntity<?> response = claimsController.getClaimRequestById(1);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void testHandleValidationExceptions() {
        MethodArgumentNotValidException ex = mock(MethodArgumentNotValidException.class);
        BindingResult bindingResult = mock(BindingResult.class);
        ObjectError error1 = new ObjectError("object1", "message1");
        ObjectError error2 = new ObjectError("object2", "message2");

        when(ex.getBindingResult()).thenReturn(bindingResult);
        when(bindingResult.getAllErrors()).thenReturn(Arrays.asList(error1, error2));

        ResponseEntity<?> response = claimsController.handleValidationExceptions(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(Arrays.asList("message1", "message2"), response.getBody());
    }

}
