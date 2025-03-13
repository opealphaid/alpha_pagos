package com.proyects.pasarelapagosalpha.model.response;

import lombok.Data;
import java.util.List;

public class VpayResponse {
    private String message;
    private List<VpayResponseList> responseList;
    private String status;


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<VpayResponseList> getResponseList() {
        return responseList;
    }

    public void setResponseList(List<VpayResponseList> responseList) {
        this.responseList = responseList;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
