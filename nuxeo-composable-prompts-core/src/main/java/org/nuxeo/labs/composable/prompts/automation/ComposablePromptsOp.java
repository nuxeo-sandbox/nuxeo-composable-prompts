package org.nuxeo.labs.composable.prompts.automation;

import org.nuxeo.ecm.automation.core.annotations.Context;
import org.nuxeo.ecm.automation.core.annotations.Operation;
import org.nuxeo.ecm.automation.core.annotations.OperationMethod;
import org.nuxeo.ecm.automation.core.annotations.Param;
import org.nuxeo.ecm.core.api.Blob;
import org.nuxeo.ecm.core.api.impl.blob.StringBlob;
import org.nuxeo.labs.composable.prompts.model.RunRequest;
import org.nuxeo.labs.composable.prompts.service.ComposablePromptsService;

import java.util.List;

/**
 *
 */
@Operation(id = ComposablePromptsOp.ID, category = "ComposablePrompts", label = "Execute Composable Prompts Interaction",
        description = "Call the Composable Prompts service to run an interaction")
public class ComposablePromptsOp {

    public static final String ID = "ComposablePrompts.ExecInteraction";

    @Param(name = "interactionId", required = true)
    protected String interactionId;

    @Param(name = "environmentId", required = true)
    protected String environmentId;

    @Param(name = "modelId", required = false)
    protected String modelId;

    @Param(name = "interactionInput", required = true)
    protected String input;

    @Param(name = "temperature", required = false)
    protected String temperatureStr;

    @Param(name = "max_tokens", required = false)
    protected String max_tokensStr;

    @Param(name = "tags", required = false)
    protected List<String> tags;

    @Context
    ComposablePromptsService cp;

    @OperationMethod
    public Blob run() {

        double temperature = temperatureStr != null ? Double.parseDouble(temperatureStr) : 0.0;
        long max_tokens = max_tokensStr != null ? Long.parseLong(max_tokensStr) : 0;

        RunRequest request = new RunRequest(interactionId, input,
                new RunRequest.Configuration(environmentId, modelId, temperature, max_tokens), tags);
        String result = cp.runExecution(request);
        return new StringBlob(result, "application/json");
    }
}
