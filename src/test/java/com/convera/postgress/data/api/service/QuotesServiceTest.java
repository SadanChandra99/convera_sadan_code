package com.convera.postgress.data.api.service;

import com.convera.postgress.data.api.repository.model.quotes.QuoteRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class QuotesServiceTest {

    @InjectMocks
    private QuotesService quotesService;


    @Test
    void getQuickQuotesByCustomerId() {
        assertNotNull(quotesService.getQuickQuotesByCustomerId("1068914"));
    }

    @Test
    void saveQuickQuote() {
        assertNotNull(quotesService.saveQuickQuote(
                QuoteRequest.builder()
                        .customerId("customerId")
                        .payCurrency("CAD")
                        .receiveCurrency("USD")
                        .specifiedCurrency("USD")
                        .specifiedAmount(new BigDecimal(1000))
                        .payReceive("PAY")
                        .build())
        );
    }

    @Test
    void getQuickQuoteByQuoteId() {
        assertNotNull(quotesService.getQuickQuoteByQuoteId("21cb32a7-cb11-421f-84f5-c245578a1406"));
    }
}