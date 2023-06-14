package com.convera.postgress.data.api.helper;

import com.convera.postgress.data.api.repository.model.quotes.Quote;
import com.convera.postgress.data.api.repository.model.quotes.QuoteRequest;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public final class QuotesHelper {


    public static Quote getQuickQuoteByQuoteId(String quoteId) {
        Quote q = getQuickQuote("1068914");
   //     Quote q = getQuickQuote(UUID.randomUUID().toString());
        q.setQuoteId(quoteId);
        return q;
    }
    public static Quote getQuickQuote(QuoteRequest quoteRequest) {
        Quote q = getQuickQuote(quoteRequest.getCustomerId());
        q.setPayCurrency(quoteRequest.getPayCurrency());
        q.setReceiveCurrency(q.getReceiveCurrency());
        q.setSpecifiedCurrency(q.getSpecifiedCurrency());
        q.setSpecifiedAmount(q.getSpecifiedAmount());
        return q;
    }

    public static Map<String,Quote> getQuickQuotes(String customerId, Integer elements) {
        Map<String,Quote> response = new HashMap<>();
        for (int i = 0; i < elements; i++) {
            Quote q = getQuickQuote(customerId);
            response.put(q.getQuoteId(), q);
        }
        return response;
    }

    public static Quote getQuickQuote(String customerId){
        return Quote.builder()
                .customerId(customerId)
                .quoteId(customerId)
//                .quoteId(UUID.randomUUID().toString())
                .receiveCurrency("USD")
                .payCurrency("CAD")
                .specifiedAmount(new BigDecimal(2))
                .specifiedCurrency("CAD")
                .valueDate("20220825")
                .customerRate(new BigDecimal(2))
                .customerRateInverted(random(new BigDecimal(2),7))
                .baseCurrency("USD")
                .costRate(random(new BigDecimal(2),2))
                .receiveAmount(random(new BigDecimal(100),1))
                .payAmount(random(new BigDecimal(100),1))
                .expirationInterval(2)
                .forwardPoints(random(new BigDecimal(10),0))
                .salesMargin(random(new BigDecimal(1),7))
                .build();
    }

    static BigDecimal random(BigDecimal range, int scale) {
        return new BigDecimal(Math.random()).multiply(range).setScale(scale, BigDecimal.ROUND_DOWN);
    }
}
