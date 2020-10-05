package com.example.customer.accounts.repository.model.transactions;

import java.io.Serializable;

public class Participant implements Serializable {
    private String maskedPan;
    private String name;

    public String getMaskedPan() {
        return maskedPan;
    }

    public void setMaskedPan(String maskedPan) {
        this.maskedPan = maskedPan;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

