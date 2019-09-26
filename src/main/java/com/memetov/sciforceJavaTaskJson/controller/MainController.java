package com.memetov.sciforceJavaTaskJson.controller;

import com.memetov.sciforceJavaTaskJson.domain.ErrorBlock;
import com.memetov.sciforceJavaTaskJson.domain.JsonMergeHypermediaControl;
import com.memetov.sciforceJavaTaskJson.domain.ValidationError;
import com.memetov.sciforceJavaTaskJson.service.JsonMergingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static com.memetov.sciforceJavaTaskJson.domain.JsonMergeHypermediaControl.MIME_TYPE;

@RestController
public class MainController {

    private final JsonMergingService jsonMergingService;

    public MainController(JsonMergingService jsonMergingService) {
        this.jsonMergingService = jsonMergingService;
    }

    @PostMapping(value = "mergeJson", consumes = {MIME_TYPE},
            produces = {MIME_TYPE})
    public @ResponseBody
    ResponseEntity<JsonMergeHypermediaControl> mergeJsons(@RequestBody @Valid JsonMergeHypermediaControl request, Errors errors) {
        if (errors.hasErrors()) {
            return constructResponseWithErrors(request, errors);
        } else {
            return processRequest(request);
        }
    }

    private ResponseEntity<JsonMergeHypermediaControl> processRequest(JsonMergeHypermediaControl control) {
        try {
            JsonMergeHypermediaControl response = jsonMergingService.mergeJsons(control);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (IOException e) {
            String errorMessage = String.format("An error ({0}) occurred during the request processing: {1}.", e.getClass(), e.getMessage());
            ErrorBlock errorBlock = new ErrorBlock(errorMessage);
            control.setErrorBlock(errorBlock);
            return new ResponseEntity<>(control, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private ResponseEntity<JsonMergeHypermediaControl> constructResponseWithErrors(JsonMergeHypermediaControl control, Errors errors) {
        List<ValidationError> validationErrors = errors.getFieldErrors().stream()
                .map(error -> new ValidationError(error.getField(), error.getDefaultMessage()))
                .collect(Collectors.toList());
        ErrorBlock errorBlock = new ErrorBlock("The request is not valid", validationErrors);
        control.setErrorBlock(errorBlock);
        return new ResponseEntity<>(control, HttpStatus.BAD_REQUEST);
    }
}