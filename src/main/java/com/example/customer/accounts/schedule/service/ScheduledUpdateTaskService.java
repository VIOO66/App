package com.example.customer.accounts.schedule.service;

import com.example.customer.accounts.repository.model.TokenModel;
import com.example.customer.accounts.repository.AccountMapStorage;
import com.example.customer.accounts.repository.TransactionMapStorage;
import com.example.customer.accounts.repository.model.account.AccountWireMockModel;
import com.example.customer.accounts.repository.model.transactions.TransactionWireMockModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.time.LocalTime;
import java.util.*;

@Service
public class ScheduledUpdateTaskService {

    private static final Logger logger = LoggerFactory.getLogger(ScheduledUpdateTaskService.class);
    private static final String USERNAME = "username";
    private static final String XAUTH = "X-AUTH";

    @Value("${update.on.behalf.of.user}")
    private String updateOnBehalfOfUser;

    @Value("${wm.address}")
    private String wmAddress;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private AccountMapStorage accountMapStorage;

    @Autowired
    private TransactionMapStorage transactionMapStorage;

    //Execute daily data update, based on the cron specified below
    @Scheduled(cron = "${update.cron.expression}")
    public void executeDailyUpdate() {
        logger.info("Scheduled update triggered on " + LocalTime.now());
        updateData();
    }

    //Execute initial data retrieval after the start of the application
    @PostConstruct
    public void executeInitialUpdate() {
        logger.info("Update on start-up triggered on " + LocalTime.now());
        updateData();
    }

    public void updateData() {
        String url = wmAddress + "/login";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.set(USERNAME, updateOnBehalfOfUser);
        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(new HashMap<>(), headers);

        // send POST request
        ResponseEntity<TokenModel> response = null;
        try {
            response = this.restTemplate.postForEntity(url, entity, TokenModel.class);
            String tokenId;
            if (response.getStatusCode() == HttpStatus.OK) {
                tokenId = Objects.requireNonNull(response.getBody()).getToken();
                updateAccountData(tokenId);
                updateTransactionData(tokenId);
            } else {
                logger.info("Could not retrieve authentication token");
            }
        } catch (ResourceAccessException ex) {
            logger.error("Connection refused: " + ex.getMessage());
        }
    }

    private void updateAccountData(final String tokenId) {
        String url = wmAddress + "/accounts";
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.set(XAUTH, tokenId);
        HttpEntity request = new HttpEntity(headers);

        // send GET request
        ResponseEntity<List<AccountWireMockModel>> response = this.restTemplate.exchange(url, HttpMethod.GET, request,
                new ParameterizedTypeReference<>() {
                });
        if (response.getStatusCode() == HttpStatus.OK) {
            accountMapStorage.getAccountMap().put(updateOnBehalfOfUser, response.getBody());
        } else {
            logger.debug("Could not retrieve account data");
        }
    }

    private void updateTransactionData(final String tokenId) {
        String url = wmAddress + "/transactions";
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.set(XAUTH, tokenId);
        HttpEntity request = new HttpEntity(headers);

        // send GET request
        ResponseEntity<List<TransactionWireMockModel>> response = this.restTemplate.exchange(url, HttpMethod.GET, request,
                new ParameterizedTypeReference<>() {
                });
        if (response.getStatusCode() == HttpStatus.OK) {
            transactionMapStorage.getTransactionMap().put(updateOnBehalfOfUser, response.getBody());
        } else {
            logger.debug("Could not retrieve account data");
        }
    }
}
