package com.cognizant.controller;

import com.cognizant.dto.ClaimResponsesDTO;
import com.cognizant.dto.ProcessRequestDTO;
import com.cognizant.service.ClaimResponsesService;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ClaimResponsesControllerTest {

    @InjectMocks
    ClaimResponsesController claimResponsesController;

    @Mock
    ClaimResponsesService claimResponsesService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testProcessClaimRequest() throws Exception {
        ProcessRequestDTO processRequestDTO = new ProcessRequestDTO();
        ClaimResponsesDTO claimResponsesDTO = new ClaimResponsesDTO();

        when(claimResponsesService.processClaimRequest(1,processRequestDTO)).thenReturn(claimResponsesDTO);

        ResponseEntity<?> response = claimResponsesController.processClaimRequest(1, processRequestDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(claimResponsesDTO, response.getBody());

    }

    @Test
    void testProcessClaimRequestException() throws Exception {
        ProcessRequestDTO processRequestDTO = new ProcessRequestDTO();
        ClaimResponsesDTO claimResponsesDTO = new ClaimResponsesDTO();

        when(claimResponsesService.processClaimRequest(1,processRequestDTO)).thenThrow(new RuntimeException());

        ResponseEntity<?> response = claimResponsesController.processClaimRequest(1, processRequestDTO);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("error occurred", response.getBody());
    }

    @Test
    void testHandleValidationExceptions() {
        MethodArgumentNotValidException ex = mock(MethodArgumentNotValidException.class);
        BindingResult bindingResult = mock(BindingResult.class);
        ObjectError error1 = new ObjectError("object1", "message1");
        ObjectError error2 = new ObjectError("object2", "message2");

        when(ex.getBindingResult()).thenReturn(bindingResult);
        when(bindingResult.getAllErrors()).thenReturn(Arrays.asList(error1, error2));

        ResponseEntity<?> response = claimResponsesController.handleValidationExceptions(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(Arrays.asList("message1", "message2"), response.getBody());
    }
}
