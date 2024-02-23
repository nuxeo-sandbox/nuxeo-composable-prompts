package org.nuxeo.labs.composable.prompts.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.nuxeo.ecm.core.api.NuxeoException;
import org.nuxeo.labs.composable.prompts.model.RunRequest;
import org.nuxeo.labs.composable.prompts.model.RunResult;
import org.nuxeo.runtime.api.Framework;
import org.nuxeo.runtime.model.DefaultComponent;

import java.io.IOException;

public class ComposablePromptsComponent extends DefaultComponent implements ComposablePromptsService {

    private static final Logger log = LogManager.getLogger(ComposablePromptsComponent.class);

    public static final MediaType JSON = MediaType.get("application/json");

    public static final String BASE_URL = " https://api.composableprompts.com/api/v1";

    public static final String API_TOKEN = "composable.prompts.api.token";

    public static final String PROJECT_ID = "composable.prompts.project.id";

    final OkHttpClient client = new OkHttpClient();

    protected static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String runExecution(RunRequest runRequest) {

        RequestBody body;

        try {
            String json = objectMapper.writeValueAsString(runRequest);
            log.info(json);
            body = RequestBody.create(json, JSON);
        } catch (JsonProcessingException e) {
            throw new NuxeoException(e);
        }

        Request request = new Request.Builder()
                .header("Accept", "application/json")
                .header("Authorization", String.format("Bearer %s", Framework.getProperty(API_TOKEN)))
                .header("X-Project-Id", Framework.getProperty(PROJECT_ID))
                .url(String.format("%s/runs", BASE_URL))
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            String res = response.body().string();
            JsonNode obj = objectMapper.readTree(res);
            if (obj.has("error")) {
                throw new NuxeoException(res);
            }
            return res;
        } catch (IOException e) {
            throw new NuxeoException(e);
        }
    }

}
