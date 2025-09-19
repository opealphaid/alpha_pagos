package com.proyects.pasarelapagosalpha.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CallbackResponse {

    @JsonProperty("message")
    private String message;

    @JsonProperty("success")
    private Boolean success;

    // Constructores
    public CallbackResponse() {}

    public CallbackResponse(String message, Boolean success) {
        this.message = message;
        this.success = success;
    }

    // Getters y Setters
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public Boolean getSuccess() { return success; }
    public void setSuccess(Boolean success) { this.success = success; }
}
