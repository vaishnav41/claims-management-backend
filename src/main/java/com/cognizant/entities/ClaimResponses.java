package com.cognizant.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "Claim_Responses")
public class ClaimResponses {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Claim_Responses_Id")
    private int claimResponsesId;

    @Column(name = "Response_Date")
    private LocalDate responseDate;

    @Column(name = "Response_Details")
    private String responseDetails;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Claims_Id")
    private Claims claim;
}
