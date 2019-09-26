package com.memetov.sciforceJavaTaskJson.domain;

import java.util.ArrayList;
import java.util.List;

public class ErrorBlock {

    private String message;

    private List<ValidationError> validationErrors;

    public ErrorBlock() {
        this("");
    }

    public ErrorBlock(String message) {
        this(message, new ArrayList<>());
    }

    public ErrorBlock(String message, List<ValidationError> validationErrors) {
        this.message = message;
        this.validationErrors = validationErrors;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ValidationError> getValidationErrors() {
        return validationErrors;
    }

    public void addValidationError(ValidationError error) {
        validationErrors.add(error);
    }

    public void addValidationError(String fieldName, String message) {
        addValidationError(new ValidationError(fieldName, message));
    }
}
