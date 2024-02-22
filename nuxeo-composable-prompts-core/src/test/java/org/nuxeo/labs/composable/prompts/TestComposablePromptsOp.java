package org.nuxeo.labs.composable.prompts;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.nuxeo.ecm.automation.AutomationService;
import org.nuxeo.ecm.automation.OperationContext;
import org.nuxeo.ecm.automation.OperationException;
import org.nuxeo.ecm.core.api.CoreSession;
import org.nuxeo.ecm.core.api.DocumentModel;
import org.nuxeo.ecm.core.test.DefaultRepositoryInit;
import org.nuxeo.ecm.core.test.annotations.Granularity;
import org.nuxeo.ecm.core.test.annotations.RepositoryConfig;
import org.nuxeo.labs.composable.prompts.automation.ComposablePromptsOp;
import org.nuxeo.runtime.test.runner.Features;
import org.nuxeo.runtime.test.runner.FeaturesRunner;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

@RunWith(FeaturesRunner.class)
@Features(TestComposablePromptsFeature.class)
@RepositoryConfig(init = DefaultRepositoryInit.class, cleanup = Granularity.METHOD)
public class TestComposablePromptsOp {

    @Inject
    protected CoreSession session;

    @Inject
    protected AutomationService automationService;

    @Test
    public void shouldCallTheOperation() throws OperationException {
        OperationContext ctx = new OperationContext(session);
        DocumentModel doc = (DocumentModel) automationService.run(ctx, ComposablePromptsOp.ID);
        assertEquals("/", doc.getPathAsString());
    }

    @Test
    public void shouldCallWithParameters() throws OperationException {
        final String path = "/default-domain";
        OperationContext ctx = new OperationContext(session);
        Map<String, Object> params = new HashMap<>();
        params.put("path", path);
        DocumentModel doc = (DocumentModel) automationService.run(ctx, ComposablePromptsOp.ID, params);
        assertEquals(path, doc.getPathAsString());
    }
}
