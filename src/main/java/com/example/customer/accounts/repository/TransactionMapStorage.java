package com.example.customer.accounts.repository;

import com.example.customer.accounts.repository.model.transactions.TransactionWireMockModel;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

@Component
public class TransactionMapStorage {

    private HashMap<String, List<TransactionWireMockModel>> transactionMap;

    public TransactionMapStorage(HashMap<String, List<TransactionWireMockModel>> transactionMap) {
        this.transactionMap = transactionMap;
    }

    public HashMap<String, List<TransactionWireMockModel>> getTransactionMap() {
        return transactionMap;
    }
}
