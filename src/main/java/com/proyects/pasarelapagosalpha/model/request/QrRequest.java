package com.proyects.pasarelapagosalpha.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QrRequest {
    private String currency;
    private String gloss;
    private double amount;
    private String expirationDate;

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getGloss() {
        return gloss;
    }

    public void setGloss(String gloss) {
        this.gloss = gloss;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }
}
