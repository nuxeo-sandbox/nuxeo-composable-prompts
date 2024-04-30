package org.nuxeo.labs.composable.prompts;

import org.nuxeo.ecm.automation.test.AutomationFeature;
import org.nuxeo.runtime.api.Framework;
import org.nuxeo.runtime.test.runner.Deploy;
import org.nuxeo.runtime.test.runner.Features;
import org.nuxeo.runtime.test.runner.FeaturesRunner;
import org.nuxeo.runtime.test.runner.RunnerFeature;

import static org.nuxeo.labs.composable.prompts.service.ComposablePromptsComponent.API_KEY;

@Features({AutomationFeature.class })
@Deploy("nuxeo-composable-prompts-core")
public class TestComposablePromptsFeature implements RunnerFeature {

    @Override
    public void beforeRun(FeaturesRunner runner) {
        Framework.getProperties().setProperty(API_KEY, System.getProperty("composablePromptsApiKey"));
    }
}