package org.nuxeo.labs.composable.prompts.service;

import org.nuxeo.labs.composable.prompts.model.embedding.EmbeddingRequest;
import org.nuxeo.labs.composable.prompts.model.execution.RunRequest;

public interface ComposablePromptsService {

    /**
     *
     * @param runRequest The run parameters
     * @return The API response as a json string
     */
    String runExecution(RunRequest runRequest);

    /**
     *
     * @param embeddingRequest the request parameters
     * @return The API response as a json string
     */
    String getEmbeddings(EmbeddingRequest embeddingRequest);
}
