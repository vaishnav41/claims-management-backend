package com.cognizant.dto;

import com.cognizant.utility.ClaimStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProcessRequestDTO {
    private ClaimStatus claimStatus;
    @NotBlank
    @Size(min = 50, max = 500)
    private String responseDetails;
}
