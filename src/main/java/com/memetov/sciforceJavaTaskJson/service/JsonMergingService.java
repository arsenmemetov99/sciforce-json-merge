package com.memetov.sciforceJavaTaskJson.service;

import com.fasterxml.jackson.core.JsonParseException;
import com.memetov.sciforceJavaTaskJson.domain.JsonMergeHypermediaControl;

import java.io.IOException;

/**
 *
 */
public interface JsonMergingService {

    /**
     *
     * @param control
     * @return
     * @throws IOException
     * @throws JsonParseException
     */
    JsonMergeHypermediaControl mergeJsons(JsonMergeHypermediaControl control ) throws IOException;
}
