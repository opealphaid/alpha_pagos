package com.proyects.pasarelapagosalpha.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class VpayConfig {

    @Value("${vpay.api.url}")
    private String apiUrl;

    @Value("${vpay.api.token}")
    private String apiToken;

    @Value("${vpay.api.user}")
    private String apiUser;

    @Value("${vpay.api.company}")
    private String apiCompany;

    @Value("${vpay.api.transaction.identifier}")
    private String apiTransactionIdentifier;

    @Value("${vpay.api.destination.account.id}")
    private String apiDestinationAccountId;

    @Value("${vpay.api.operation.qr}")
    private String apiOperationQr;

    public String getApiUrl() {
        return apiUrl;
    }

    public String getApiToken() {
        return apiToken;
    }

    public String getApiUser() {
        return apiUser;
    }

    public String getApiCompany() {
        return apiCompany;
    }

    public String getApiTransactionIdentifier() {
        return apiTransactionIdentifier;
    }

    public String getApiDestinationAccountId() {
        return apiDestinationAccountId;
    }

    public String getApiOperationQr() {
        return apiOperationQr;
    }

}
