package com.cognizant.exception;

public class PolicyAlreadyClaimedException extends Exception {
    public PolicyAlreadyClaimedException(String message) {
        super(message);
    }
}