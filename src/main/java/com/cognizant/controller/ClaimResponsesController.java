package com.cognizant.controller;

import com.cognizant.dto.ProcessRequestDTO;
import com.cognizant.service.ClaimResponsesService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
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
 * <p>
 * This controller class provides RESTful API endpoints for processing claim requests.
 */
@Slf4j
@CrossOrigin(origins = "http://localhost:4200/")
@RestController
@RequestMapping("/api")
public class ClaimResponsesController {
    private ClaimResponsesService claimResponsesService;

    public ClaimResponsesController(ClaimResponsesService claimResponsesService) {
        this.claimResponsesService = claimResponsesService;
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
     * This method is used to process a claim request.
     */
    @Operation(description = "This API is used to process a claim request")
    @PutMapping("/claims/{claimId}/process")
    public ResponseEntity<Object> processClaimRequest(@PathVariable int claimId, @Valid @RequestBody ProcessRequestDTO processRequestDTO) {
        try {
            log.info("Successfully processed claim request");
            return new ResponseEntity<>(claimResponsesService.processClaimRequest(claimId, processRequestDTO), HttpStatus.OK);
        } catch (Exception e) {
            log.info("Claim request processing failed");
            return new ResponseEntity<>("error occurred", HttpStatus.BAD_REQUEST);
        }
    }
}
