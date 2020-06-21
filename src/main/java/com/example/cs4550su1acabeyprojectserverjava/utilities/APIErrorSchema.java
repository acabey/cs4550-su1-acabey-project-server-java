package com.example.cs4550su1acabeyprojectserverjava.utilities;

public class APIErrorSchema {
    private String message;
    private String details;
    private String hint;
    private String nextActions;
    private boolean error = true;

    protected APIErrorSchema() {
    }

    public APIErrorSchema(String message) {
        this(message, null, null, null);
    }

    public APIErrorSchema(
            String message, String details, String hint, String nextActions) {
        this.message = message;
        this.details = details;
        this.hint = hint;
        this.nextActions = nextActions;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public String getNextActions() {
        return nextActions;
    }

    public void setNextActions(String nextActions) {
        this.nextActions = nextActions;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }
}

