package com.example.customer.accounts.repository.model.transactions;

import java.io.Serializable;
import java.time.OffsetDateTime;

public class TransactionWireMockModel implements Serializable {

    private String id;
    private String accountId;
    private ExchangeRate exchangeRate;
    private OriginalAmount originalAmount;
    private Participant creditor;
    private Participant debtor;
    private String status;
    private String currency;
    private double amount;
    private OffsetDateTime update;
    private String description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public ExchangeRate getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(ExchangeRate exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public OriginalAmount getOriginalAmount() {
        return originalAmount;
    }

    public void setOriginalAmount(OriginalAmount originalAmount) {
        this.originalAmount = originalAmount;
    }

    public Participant getCreditor() {
        return creditor;
    }

    public void setCreditor(Participant creditor) {
        this.creditor = creditor;
    }

    public Participant getDebtor() {
        return debtor;
    }

    public void setDebtor(Participant debtor) {
        this.debtor = debtor;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public OffsetDateTime getUpdate() {
        return update;
    }

    public void setUpdate(OffsetDateTime update) {
        this.update = update;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
