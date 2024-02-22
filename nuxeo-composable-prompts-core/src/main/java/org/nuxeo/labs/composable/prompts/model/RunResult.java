package org.nuxeo.labs.composable.prompts.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonRawValue;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RunResult {

    public String status;

    public Error error;

    @JsonRawValue
    public String result;

    public RunResult() {
    };

    public RunResult(String status, Error error, String result) {
        this.status = status;
        this.error = error;
        this.result = result;
    }

    @Override
    public String toString() {
        return "RunResult{" +
                "status='" + status + '\'' +
                ", error=" + error +
                ", result='" + result + '\'' +
                '}';
    }

    public static class Error {

        public String code;

        public String message;

        public Error() {
        };

        public Error(String code, String message) {
            this.code = code;
            this.message = message;
        }

        @Override
        public String toString() {
            return "Error{" +
                    "code='" + code + '\'' +
                    ", message='" + message + '\'' +
                    '}';
        }
    }

}
