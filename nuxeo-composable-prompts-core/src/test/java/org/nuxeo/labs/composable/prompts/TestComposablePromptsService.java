package org.nuxeo.labs.composable.prompts;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.nuxeo.ecm.core.test.DefaultRepositoryInit;
import org.nuxeo.ecm.core.test.annotations.Granularity;
import org.nuxeo.ecm.core.test.annotations.RepositoryConfig;
import org.nuxeo.labs.composable.prompts.model.embedding.EmbeddingRequest;
import org.nuxeo.labs.composable.prompts.model.execution.RunRequest;
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
        String response = composablePromptsService.runExecution(
                new RunRequest(System.getProperty("composablePromptsInteractionId"), "{\"text\":\"Hello\"}",
                        new RunRequest.Configuration(System.getProperty("composablePromptsEnvironmentId"), "")));
        System.out.println(response);
    }

    @Test
    public void testRequestEmbeddings() {
        String response = composablePromptsService.getEmbeddings(
                new EmbeddingRequest(
                        "{\"content\":\"Hello, can you please give me the embedding for this text?\"}",
                        System.getProperty("composablePromptsEnvironmentId")
                )
        );
        System.out.println(response);
    }
}
