package com.cognizant.dto;

import com.cognizant.utility.ClaimStatus;
import com.cognizant.utility.ClaimantIDProofType;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClaimsDTO {

    private int claimsId;
    @NotNull(message = "{com.cognizant.dto.ClaimsDTO.policyId.error}")
    private Integer policyId;
    @NotBlank(message = "{com.cognizant.dto.ClaimsDTO.userName.error}")
    private String userName;
    @NotNull(message = "{com.cognizant.dto.ClaimsDTO.subscriptionId.error}")
    private Integer subscriptionId;
    private LocalDate claimDate;
    @NotNull(message = "{com.cognizant.dto.ClaimsDTO.claimTypeId.error}")
    private Integer claimTypeId;
    @NotBlank(message = "{com.cognizant.dto.ClaimsDTO.claimSummary.error}")
    private String claimSummary;
    @NotBlank(message = "{com.cognizant.dto.ClaimsDTO.claimDetails.error}")
    private String claimDetails;
    private ClaimStatus claimStatus;
    @NotNull(message = "{com.cognizant.dto.ClaimsDTO.isRaisedByPolicyHolder.error}")
    private boolean isRaisedByPolicyHolder;
    private String claimantFullName;
    private LocalDate claimantDateOfBirth;
    private String claimantAddress;
    private ClaimantIDProofType claimantIDProofType;
    private String claimantIDProofNumber;
    @Future
    private LocalDate responseETA;
}