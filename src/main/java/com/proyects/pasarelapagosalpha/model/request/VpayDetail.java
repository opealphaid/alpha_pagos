package com.proyects.pasarelapagosalpha.model.request;

import lombok.Data;
import java.util.List;


public class VpayDetail {
    private List<VpayItem> items;

    public List<VpayItem> getItems() {
        return items;
    }

    public void setItems(List<VpayItem> items) {
        this.items = items;
    }
}

