//package com.cognizant.controller;
//
//public class ClaimTypesControllerTest {
//}
//package com.cognizant.controller;
//
//import com.cognizant.service.ClaimTypesServiceImpl;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//
//import java.util.Arrays;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.when;
//
//public class ClaimTypesControllerTest {
//
//    @InjectMocks
//    ClaimTypesController claimTypesController;
//
//    @Mock
//    ClaimTypesServiceImpl claimTypesService;
//
//    @BeforeEach
//    public void init() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    public void testGetAllClaimTypes() {
//        // Arrange
//        List<String> expectedClaimTypes = Arrays.asList("Type1", "Type2", "Type3");
//        when(claimTypesService.fetchAllClaimTypes()).thenReturn(expectedClaimTypes);
//
//        // Act
//        ResponseEntity<?> response = claimTypesController.getAllClaimTypes();
//
//        // Assert
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals(expectedClaimTypes, response.getBody());
//    }
//}

package com.cognizant.controller;

import com.cognizant.dto.ClaimTypesDTO;
import com.cognizant.entities.ClaimTypes;
import com.cognizant.service.ClaimTypesServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class ClaimTypesControllerTest {

    @InjectMocks
    ClaimTypesController claimTypesController;

    @Mock
    ClaimTypesServiceImpl claimTypesService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllClaimTypes() {
        List<ClaimTypesDTO> expected = Arrays.asList(new ClaimTypesDTO(), new ClaimTypesDTO(), new ClaimTypesDTO());

        when(claimTypesService.fetchAllClaimTypes()).thenReturn(expected);

        ResponseEntity<?> response = claimTypesController.getAllClaimTypes();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expected, response.getBody());
    }
}

