package com.memetov.SciforceJavaTaskJson.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.memetov.SciforceJavaTaskJson.domain.MyRequest;
import com.memetov.SciforceJavaTaskJson.domain.Product;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("path")
public class MainController {

    @PostMapping
    public ResponseEntity<List<Product>> getJson(RequestEntity<MyRequest> request) throws IOException {
       ObjectMapper mapper = new ObjectMapper();
       List<Product> product = Arrays.stream(mapper.readValue(new URL(url), Product[].class)).collect(Collectors.toList());
       List<Product> products = Arrays.stream(mapper.readValue(new File(path), Product[].class)).collect(Collectors.toList());
       product.addAll(products);
       return product;
   }

}
