package org.nuxeo.labs.composable.prompts;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;
import org.nuxeo.labs.composable.prompts.model.RunError;
import org.nuxeo.labs.composable.prompts.model.RunRequest;
import org.nuxeo.labs.composable.prompts.model.RunResult;

import java.util.Map;

public class TestModelJson {

    protected static final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void testRunRequestSerialization() throws JsonProcessingException {
        RunRequest request = new RunRequest("123", "\"test\"", new RunRequest.Configuration("abc", "model1"));
        String json = objectMapper.writeValueAsString(request);
        Assert.assertEquals("{\"interactionId\":\"123\",\"data\":\"test\",\"configuration\":{\"environment\":\"abc\",\"model\":\"model1\"}}", json);
    }

    @Test
    public void testRunResultDeserialization() throws JsonProcessingException {
        String json = """
                {
                  "result": {
                    "color": "blue"
                  },
                  "status": "completed"
                }
                """;
        RunResult runResult = objectMapper.readValue(json, RunResult.class);
        Map<String,Object> result = runResult.result;
        Assert.assertEquals("blue",result.get("color"));
    }

    @Test
    public void testRunResultErrorDeserialization() throws JsonProcessingException {
        String json = """
                {
                    "error": "Not Found",
                    "status": 404,
                    "message": "Interaction not found: 6554cf617eae1c28ef5f3d41"
                }
                """;
        RunError error = objectMapper.readValue(json, RunError.class);
        Assert.assertEquals("404", error.status);
    }

}
