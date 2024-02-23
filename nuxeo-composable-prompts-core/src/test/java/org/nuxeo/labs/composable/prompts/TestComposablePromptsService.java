package org.nuxeo.labs.composable.prompts;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.nuxeo.ecm.core.api.NuxeoException;
import org.nuxeo.ecm.core.test.DefaultRepositoryInit;
import org.nuxeo.ecm.core.test.annotations.Granularity;
import org.nuxeo.ecm.core.test.annotations.RepositoryConfig;
import org.nuxeo.labs.composable.prompts.model.RunRequest;
import org.nuxeo.labs.composable.prompts.service.ComposablePromptsService;
import org.nuxeo.runtime.test.runner.Features;
import org.nuxeo.runtime.test.runner.FeaturesRunner;

import javax.inject.Inject;

@RunWith(FeaturesRunner.class)
@Features(TestComposablePromptsFeature.class)
@RepositoryConfig(init = DefaultRepositoryInit.class, cleanup = Granularity.METHOD)
public class TestComposablePromptsService {

    @Inject
    protected ComposablePromptsService composablePromptsService;

    @Test(expected = NuxeoException.class)
    public void testRunExecution() {
        composablePromptsService.runExecution(
                new RunRequest(System.getProperty("composablePromptsInteractionId"), "{\"text\":\"Hello\"}",
                        new RunRequest.Configuration(System.getProperty("composablePromptsEnvironmentId"), "")));
    }

}
