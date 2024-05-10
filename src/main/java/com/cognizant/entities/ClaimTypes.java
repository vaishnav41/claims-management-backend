package com.cognizant.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "Claim_Types")
public class ClaimTypes {

    @Id
    @Column(name = "Claim_Types_Id")
    private int claimTypesId;
    @Column(name = "Type")
    private String type;
}
