package com.memetov.sciforceJavaTaskJson.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.memetov.sciforceJavaTaskJson.domain.JsonMergeHypermediaControl;
import com.memetov.sciforceJavaTaskJson.domain.Product;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Stream;

/**
 * Default implementation of {@link JsonMergingService}.
 */
@Service
public class JsonMergingServiceImpl implements JsonMergingService {

    private final ObjectMapper mapper;

    public JsonMergingServiceImpl(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public JsonMergeHypermediaControl mergeJsons(JsonMergeHypermediaControl control) throws IOException {
        Stream<Product> streamFromUrl = Arrays.stream(mapper.readValue(control.getLink(), Product[].class));
        Stream<Product> streamFromFile = Arrays.stream(mapper.readValue(new File(control.getFilePath()), Product[].class));
        Stream.concat(streamFromUrl,streamFromFile).forEach(product -> injectJsonToMap(product,control.getProductMap()));
        return control;
    }

    private void injectJsonToMap(Product product, Map<UUID, Product> productMap) {
        if (productMap.containsKey(product.getProductUuid())){
            Product existedProduct = productMap.get(product.getProductUuid());
            existedProduct.setAmount(existedProduct.getAmount() + product.getAmount());
        } else {
            productMap.put(product.getProductUuid(), product);
        }
    }
}
