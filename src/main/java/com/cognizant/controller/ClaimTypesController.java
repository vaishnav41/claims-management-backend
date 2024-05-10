package com.cognizant.controller;

import com.cognizant.service.ClaimTypesServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Vaishnav
 *
 * This controller class provides RESTful API endpoints for managing claim types requests.
 */
@Slf4j
@CrossOrigin(origins = "http://localhost:4200/")
@RestController
@RequestMapping("/api")
public class ClaimTypesController {

    ClaimTypesServiceImpl claimTypesService;

    public ClaimTypesController(ClaimTypesServiceImpl claimTypesService) {
        this.claimTypesService = claimTypesService;
    }

    /**
     * This method is used to fetch all claim types.
     */
    @Operation(description = "This API is used to fetch all claim types")
    @GetMapping("/claims/types")
    public ResponseEntity<?> getAllClaimTypes() {
        log.info("Successfully got all claim types");
        return new ResponseEntity<>(claimTypesService.fetchAllClaimTypes(), HttpStatus.OK);
    }
}