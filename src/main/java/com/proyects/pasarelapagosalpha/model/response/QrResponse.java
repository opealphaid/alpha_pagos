package com.proyects.pasarelapagosalpha.model.response;

public class QrResponse {
    private String base64Image;
    private Long paymentId;

    public QrResponse(String base64Image, Long paymentId) {
        this.base64Image = base64Image;
        this.paymentId = paymentId;
    }

    public String getBase64Image() {
        return base64Image;
    }

    public void setBase64Image(String base64Image) {
        this.base64Image = base64Image;
    }

    public Long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
    }
}

