package org.nuxeo.labs.composable.prompts.automation;

import org.nuxeo.ecm.automation.core.annotations.Context;
import org.nuxeo.ecm.automation.core.annotations.Operation;
import org.nuxeo.ecm.automation.core.annotations.OperationMethod;
import org.nuxeo.ecm.automation.core.annotations.Param;
import org.nuxeo.ecm.core.api.Blob;
import org.nuxeo.ecm.core.api.impl.blob.StringBlob;
import org.nuxeo.labs.composable.prompts.model.embedding.EmbeddingRequest;
import org.nuxeo.labs.composable.prompts.service.ComposablePromptsService;

@Operation(id = ComposablePromptsEmbeddingOp.ID, category = "ComposablePrompts", label = "Get Embedding",
        description = "Call the Composable Prompts service to get the Embedding")
public class ComposablePromptsEmbeddingOp {

    public static final String ID = "ComposablePrompts.GetEmbedding";

    @Param(name = "environmentId", required = true)
    protected String environmentId;

    @Param(name = "textInput", required = true)
    protected String input;

    @Context
    ComposablePromptsService cp;

    @OperationMethod
    public Blob run() {
        EmbeddingRequest request = new EmbeddingRequest(input, environmentId);
        String result = cp.getEmbeddings(request);
        return new StringBlob(result, "application/json");
    }
}
