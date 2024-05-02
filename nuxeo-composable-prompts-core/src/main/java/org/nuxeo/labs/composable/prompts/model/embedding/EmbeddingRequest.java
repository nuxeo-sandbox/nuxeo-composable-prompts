package org.nuxeo.labs.composable.prompts.model.embedding;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EmbeddingRequest {

    public String content;

    @JsonIgnore
    public String environmentId;

    public EmbeddingRequest() {};

    public EmbeddingRequest(String content) {
        this.content = content;
    }

    public EmbeddingRequest(String content, String environmentId) {
        this.content = content;
        this.environmentId = environmentId;
    }
}
