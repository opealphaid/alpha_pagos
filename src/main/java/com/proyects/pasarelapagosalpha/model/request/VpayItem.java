package com.proyects.pasarelapagosalpha.model.request;

public class VpayItem {
    private String attribute;
    private String value;

    public VpayItem(String key, String value) {
        this.attribute = key;
        this.value = value;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
