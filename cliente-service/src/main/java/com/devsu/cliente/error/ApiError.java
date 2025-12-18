package com.devsu.cliente.error;

import java.util.List;

public class ApiError {

    private String error;
    private String code;
    private String timestamp;
    private String path;
    private List<FieldError> details;

    public ApiError() {
    }

    public ApiError(String error, String code, String timestamp, String path, List<FieldError> details) {
        this.error = error;
        this.code = code;
        this.timestamp = timestamp;
        this.path = path;
        this.details = details;
    }

    public String getError() { return error; }
    public void setError(String error) { this.error = error; }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public String getTimestamp() { return timestamp; }
    public void setTimestamp(String timestamp) { this.timestamp = timestamp; }

    public String getPath() { return path; }
    public void setPath(String path) { this.path = path; }

    public List<FieldError> getDetails() { return details; }
    public void setDetails(List<FieldError> details) { this.details = details; }

    public static class FieldError {
        private String field;
        private String message;

        public FieldError() {
        }

        public FieldError(String field, String message) {
            this.field = field;
            this.message = message;
        }

        public String getField() { return field; }
        public void setField(String field) { this.field = field; }

        public String getMessage() { return message; }
        public void setMessage(String message) { this.message = message; }
    }
}
