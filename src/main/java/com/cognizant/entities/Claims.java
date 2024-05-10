package com.cognizant.entities;

import com.cognizant.utility.ClaimStatus;
import com.cognizant.utility.ClaimantIDProofType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "Claims")
public class Claims {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Claims_Id")
    private int claimsId;

    @Column(name = "Policy_Id")
    private int policyId;

    @Column(name = "User_Name")
    private String userName;

    @Column(name = "Subscription_Id")
    private int subscriptionId;

    @Column(name = "Claim_Date")
    private LocalDate claimDate;

    @ManyToOne
    @JoinColumn(name = "Claim_Types_Id")
    private ClaimTypes claimType;

    @Column(name = "Claim_Summary")
    private String claimSummary;

    @Column(name = "Claim_Details")
    private String claimDetails;

    @Column(name = "Claim_Status")
    @Enumerated(EnumType.STRING)
    private ClaimStatus claimStatus;

    @Column(name = "Is_Raised_By_Policy_Holder")
    private boolean isRaisedByPolicyHolder;

    @Column(name = "Claimant_Full_Name")
    private String claimantFullName;

    @Column(name = "Claimant_Date_Of_Birth")
    private LocalDate claimantDateOfBirth;

    @Column(name = "Claimant_Address")
    private String claimantAddress;

    @Column(name = "Claimant_ID_Proof_Type")
    @Enumerated(EnumType.STRING)
    private ClaimantIDProofType claimantIDProofType;

    @Column(name = "Claimant_ID_Proof_Number")
    private String claimantIDProofNumber;

    @Column(name = "Response_ETA")
    private LocalDate responseETA;
}
