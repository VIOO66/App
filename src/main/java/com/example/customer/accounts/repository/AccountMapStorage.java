package com.example.customer.accounts.repository;

import com.example.customer.accounts.repository.model.account.AccountWireMockModel;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

@Component
public class AccountMapStorage {

    private HashMap<String, List<AccountWireMockModel>> accountMap;

    public AccountMapStorage(HashMap<String, List<AccountWireMockModel>> accountMap) {
        this.accountMap = accountMap;
    }

    public HashMap<String, List<AccountWireMockModel>> getAccountMap() {
        return accountMap;
    }
}
