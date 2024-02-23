package org.nuxeo.labs.composable.prompts.automation;

import org.nuxeo.ecm.automation.core.annotations.Context;
import org.nuxeo.ecm.automation.core.annotations.Operation;
import org.nuxeo.ecm.automation.core.annotations.OperationMethod;
import org.nuxeo.ecm.automation.core.annotations.Param;
import org.nuxeo.ecm.core.api.Blob;
import org.nuxeo.ecm.core.api.impl.blob.StringBlob;
import org.nuxeo.labs.composable.prompts.model.RunRequest;
import org.nuxeo.labs.composable.prompts.service.ComposablePromptsService;

/**
 *
 */
@Operation(id=ComposablePromptsOp.ID, category="ComposablePrompts", label="Composable Prompts Call", description="Describe here what your operation does.")
public class ComposablePromptsOp {

    public static final String ID = "ComposablePrompts.Run";

    @Param(name = "interactionId", required = true)
    protected String interactionId;

    @Param(name = "environmentId", required = true)
    protected String environmentId;

    @Param(name = "modelId", required = true)
    protected String modelId;

    @Param(name = "input", required = true)
    protected String input;

    @Context
    ComposablePromptsService cp;

    @OperationMethod
    public Blob run() {
        RunRequest request = new RunRequest(interactionId, input, new RunRequest.Configuration(environmentId, modelId));
        String result = cp.runExecution(request);
        return new StringBlob(result,"application/json");
    }
}
