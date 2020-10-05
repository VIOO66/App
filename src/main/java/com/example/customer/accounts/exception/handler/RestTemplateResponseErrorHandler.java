package com.example.customer.accounts.exception.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.DefaultResponseErrorHandler;

import java.io.IOException;

@Component
public class RestTemplateResponseErrorHandler
        extends DefaultResponseErrorHandler {

    private static final Logger logger = LoggerFactory.getLogger(RestTemplateResponseErrorHandler.class);

    @Override
    public void handleError(ClientHttpResponse httpResponse)
            throws IOException {
        try {
            super.handleError(httpResponse);
        } catch (Exception e) {
            logger.error("Exception [" + e.getMessage() + "] occurred while trying to send the request", e);
            throw e;
        }
    }

    @Override
    public boolean hasError(ClientHttpResponse clientHttpResponse) throws IOException {
        try {
            return super.hasError(clientHttpResponse);
        } catch (Exception e) {
            logger.error("Exception [" + e.getMessage() + "] occurred while trying to send the request", e);
            return true;
        }
    }
}
