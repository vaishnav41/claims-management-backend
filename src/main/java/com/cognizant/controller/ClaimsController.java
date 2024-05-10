package com.cognizant.controller;

import com.cognizant.dto.ClaimsDTO;
import com.cognizant.service.ClaimsService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Vaishnav
 *
 * This controller class provides RESTful API endpoints for managing claim requests.
 */
@Slf4j
@CrossOrigin(origins = "http://localhost:4200/")
@RestController
@RequestMapping("/api")
public class ClaimsController {

    private ClaimsService claimsService;

    public ClaimsController(ClaimsService claimsService) {
        this.claimsService = claimsService;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        List<String> errorMessages = bindingResult.getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .toList();
        return new ResponseEntity<>(errorMessages, HttpStatus.BAD_REQUEST);
    }

    /**
     * This method is used to create a new claim request.
     */
    @Operation(description = "This api is used to create a new claim request")
    @PostMapping("/claims")
    public ResponseEntity<?> createClaimRequest(@Valid @RequestBody ClaimsDTO claimsDTO) {
        try {
            claimsService.addNewClaimRequest(claimsDTO);
            log.info("Successfully created claim request");
            return new ResponseEntity<>("Created Claim Request", HttpStatus.OK);
        } catch (Exception e) {
            log.info("Claim request creation failed");
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * This method is used to fetch all new claim requests.
     */
    @Operation(description = "This API is used to fetch all new claim requests")
    @GetMapping("/claims")
    public ResponseEntity<?> getNewClaimRequests() {
        List<ClaimsDTO> responseList = claimsService.fetchAllNewClaimRequests();
        log.info("Successfully got all new claim requests");
        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    /**
     * This method is used to fetch a claim request by its ID.
     */
    @Operation(description = "This API is used to fetch a claim request by id")
    @GetMapping("/claims/{claimsId}")
    public ResponseEntity<?> getClaimRequestById(@PathVariable int claimsId) {
        ClaimsDTO claimEntity = claimsService.fetchClaimRequestById(claimsId);
        if (claimEntity != null) {
            log.info("Successfully got claim request by id");
            return new ResponseEntity<>(claimEntity, HttpStatus.OK);
        } else {
            log.info("Claim request by id failed");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}