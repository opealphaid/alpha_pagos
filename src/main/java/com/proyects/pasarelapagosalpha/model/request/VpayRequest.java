package com.proyects.pasarelapagosalpha.model.request;

import java.util.List;


public class VpayRequest {
    private String operation;
    private List<VpayAttribute> header;
    private List<VpayDetail> detail;

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public List<VpayAttribute> getHeader() {
        return header;
    }

    public void setHeader(List<VpayAttribute> header) {
        this.header = header;
    }

    public List<VpayDetail> getDetail() {
        return detail;
    }

    public void setDetail(List<VpayDetail> detail) {
        this.detail = detail;
    }

    @Override
    public String toString() {
        return "VpayRequest{" +
                "operation='" + operation + '\'' +
                ", header=" + header +
                ", detail=" + detail +
                '}';
    }
}
