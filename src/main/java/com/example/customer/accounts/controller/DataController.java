package com.example.customer.accounts.controller;

import com.example.customer.accounts.repository.AccountMapStorage;
import com.example.customer.accounts.repository.TransactionMapStorage;
import com.example.customer.accounts.repository.model.account.AccountWireMockModel;
import com.example.customer.accounts.repository.model.transactions.TransactionWireMockModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/data")
public class DataController {

    private static final Logger logger = LoggerFactory.getLogger(DataController.class);

    @Autowired
    private AccountMapStorage accountMapStorage;

    @Autowired
    private TransactionMapStorage transactionMapStorage;

    @GetMapping("/account/{username}")
    public ResponseEntity<List<AccountWireMockModel>> getAccountData(@PathVariable("username") final String username) {
        if (accountMapStorage.getAccountMap().get(username) != null) {
            return new ResponseEntity<>(accountMapStorage.getAccountMap().get(username), HttpStatus.OK);
        } else {
            logger.debug("No account data found for username: " + username);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/transactions/{username}")
    public ResponseEntity<List<TransactionWireMockModel>> getTransactionData(@PathVariable("username") final String username) {
        if (transactionMapStorage.getTransactionMap().get(username) != null) {
            return new ResponseEntity<>(transactionMapStorage.getTransactionMap().get(username), HttpStatus.OK);
        } else {
            logger.debug("No transactions found for username: " + username);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
