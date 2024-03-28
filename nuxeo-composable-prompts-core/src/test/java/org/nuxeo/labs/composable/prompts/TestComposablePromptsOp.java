package org.nuxeo.labs.composable.prompts;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.nuxeo.ecm.automation.AutomationService;
import org.nuxeo.ecm.automation.OperationContext;
import org.nuxeo.ecm.automation.OperationException;
import org.nuxeo.ecm.core.api.Blob;
import org.nuxeo.ecm.core.api.CoreSession;
import org.nuxeo.ecm.core.api.NuxeoException;
import org.nuxeo.ecm.core.test.DefaultRepositoryInit;
import org.nuxeo.ecm.core.test.annotations.Granularity;
import org.nuxeo.ecm.core.test.annotations.RepositoryConfig;
import org.nuxeo.labs.composable.prompts.automation.ComposablePromptsOp;
import org.nuxeo.runtime.test.runner.Features;
import org.nuxeo.runtime.test.runner.FeaturesRunner;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

@RunWith(FeaturesRunner.class)
@Features(TestComposablePromptsFeature.class)
@RepositoryConfig(init = DefaultRepositoryInit.class, cleanup = Granularity.METHOD)
public class TestComposablePromptsOp {

    @Inject
    protected CoreSession session;

    @Inject
    protected AutomationService automationService;

    @Test
    public void testSuccess() throws OperationException {
        OperationContext ctx = new OperationContext(session);
        Map<String, Object> params = new HashMap<>();
        params.put("interactionId",System.getProperty("composablePromptsInteractionId"));
        params.put("environmentId",System.getProperty("composablePromptsEnvironmentId"));
        params.put("modelId",System.getProperty("composablePromptsModelId"));
        params.put("interactionInput","{\"text\":\"Hello\"}");
        Blob json = (Blob) automationService.run(ctx, ComposablePromptsOp.ID, params);
        Assert.assertNotNull(json);
    }

    @Test(expected = NuxeoException.class)
    public void testFailure() throws OperationException {
        OperationContext ctx = new OperationContext(session);
        Map<String, Object> params = new HashMap<>();
        params.put("interactionId",System.getProperty("composablePromptsInteractionId"));
        params.put("environmentId",System.getProperty("composablePromptsEnvironmentId"));
        params.put("modelId","gpt2000");
        params.put("interactionInput","{\"text\":\"Hello\"}");
        Blob json = (Blob) automationService.run(ctx, ComposablePromptsOp.ID, params);
        Assert.assertNotNull(json);
    }
}
