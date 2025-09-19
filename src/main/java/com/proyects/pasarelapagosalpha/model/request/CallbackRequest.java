package com.proyects.pasarelapagosalpha.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CallbackRequest {

    @JsonProperty("idQr")
    private String idQr;

    @JsonProperty("gloss")
    private String gloss;

    @JsonProperty("idTransactionVpay")
    private Long idTransactionVpay;

    @JsonProperty("channelTransaccion")
    private String channelTransaccion;

    @JsonProperty("transactionDateTime")
    private LocalDateTime transactionDateTime;

    @JsonProperty("additionalData")
    private String additionalData;

    public CallbackRequest() {}

    public CallbackRequest(String idQr, String gloss, Long idTransactionVpay,
                           String channelTransaccion, LocalDateTime transactionDateTime,
                           String additionalData) {
        this.idQr = idQr;
        this.gloss = gloss;
        this.idTransactionVpay = idTransactionVpay;
        this.channelTransaccion = channelTransaccion;
        this.transactionDateTime = transactionDateTime;
        this.additionalData = additionalData;
    }

}