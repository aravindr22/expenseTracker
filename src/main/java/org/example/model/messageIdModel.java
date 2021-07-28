package org.example.model;

public class messageIdModel {
    private String message;
    private String authencationCode;

    public messageIdModel() {
    }

    public messageIdModel(String message, String authencationCode) {
        this.message = message;
        this.authencationCode = authencationCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAuthencationCode() {
        return authencationCode;
    }

    public void setAuthencationCode(String authencationCode) {
        this.authencationCode = authencationCode;
    }
}
