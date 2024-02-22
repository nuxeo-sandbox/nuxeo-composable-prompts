package org.nuxeo.labs.composable.prompts.service;

import org.nuxeo.labs.composable.prompts.model.Run;
import org.nuxeo.labs.composable.prompts.model.RunResult;

public interface ComposablePromptsService {

    RunResult runExecution(Run run);

}
