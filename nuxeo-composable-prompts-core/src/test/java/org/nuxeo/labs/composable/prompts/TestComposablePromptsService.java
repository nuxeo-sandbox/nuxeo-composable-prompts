package org.nuxeo.labs.composable.prompts;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.nuxeo.ecm.core.test.DefaultRepositoryInit;
import org.nuxeo.ecm.core.test.annotations.Granularity;
import org.nuxeo.ecm.core.test.annotations.RepositoryConfig;
import org.nuxeo.labs.composable.prompts.model.Run;
import org.nuxeo.labs.composable.prompts.model.RunResult;
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

    @Test
    public void testRunExecution() {
        RunResult result = composablePromptsService.runExecution(
                new Run(System.getProperty("composablePromptsInteractionId"), "{\"text\":\"Hello\"}",
                        new Run.Configuration(System.getProperty("composablePromptsEnvironmentId"), "")));
        Assert.assertNotNull(result);
        System.out.println(result);
    }

}
