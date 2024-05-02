package org.nuxeo.labs.composable.prompts.model.embedding;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EmbeddingResponse {

    double[] values;

    public EmbeddingResponse(){}

    public EmbeddingResponse(double[] values) {
        this.values = values;
    }
}
