package org.nuxeo.labs.composable.prompts.service;

import org.nuxeo.labs.composable.prompts.model.embedding.EmbeddingRequest;
import org.nuxeo.labs.composable.prompts.model.execution.RunRequest;

public interface ComposablePromptsService {

    String runExecution(RunRequest runRequest);

    String getEmbeddings(EmbeddingRequest embeddingRequest);
}
