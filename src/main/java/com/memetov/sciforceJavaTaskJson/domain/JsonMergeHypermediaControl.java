package com.memetov.sciforceJavaTaskJson.domain;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Describes a request from the user
 */
public class JsonMergeHypermediaControl {
    public static final String MIME_TYPE = "application/json;json-merging-control;version=1.0";

    @NotNull
    private URL link;

    @NotEmpty
    private String filePath;

    private Map<UUID, Product> productMap = new HashMap<>();

    private ErrorBlock errorBlock;

    public URL getLink() {
        return link;
    }

    public void setLink(URL link) {
        this.link = link;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Map<UUID, Product> getProductMap() {
        return productMap;
    }

    public void setProductMap(Map<UUID, Product> productMap) {
        this.productMap = productMap;
    }

    public ErrorBlock getErrorBlock() {
        return errorBlock;
    }

    public void setErrorBlock(ErrorBlock errorBlock) {
        this.errorBlock = errorBlock;
    }

}
