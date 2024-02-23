package org.nuxeo.labs.composable.prompts.service;

import org.nuxeo.labs.composable.prompts.model.RunRequest;
import org.nuxeo.labs.composable.prompts.model.RunResult;

public interface ComposablePromptsService {

    String runExecution(RunRequest runRequest);

}
