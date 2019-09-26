package com.memetov.sciforceJavaTaskJson.controller;

import com.memetov.sciforceJavaTaskJson.domain.JsonMergeHypermediaControl;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.MalformedURLException;

@RunWith(SpringRunner.class)
@SpringBootTest
class MainControllerTest {
    @MockBean
    private JsonMergeHypermediaControl request;

    @Test
    void mergeJsons() throws MalformedURLException {

    }
}