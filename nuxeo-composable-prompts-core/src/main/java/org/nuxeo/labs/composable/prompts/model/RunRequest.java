package org.nuxeo.labs.composable.prompts.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonRawValue;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RunRequest {

    public String interactionId;
    @JsonRawValue
    public String data;
    public Configuration config;

    public RunRequest(String interactionId, String data, Configuration config) {
        this.interactionId = interactionId;
        this.data = data;
        this.config = config;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Configuration {
        public String environment;
        public String model;

        public Configuration(String environment, String model) {
            this.environment = environment;
            this.model = model;
        }
    }

}



