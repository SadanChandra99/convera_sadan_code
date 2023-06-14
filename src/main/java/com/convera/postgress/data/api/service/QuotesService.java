package com.convera.postgress.data.api.service;

import com.convera.postgress.data.api.helper.QuotesHelper;
import com.convera.postgress.data.api.repository.model.quotes.Quote;
import com.convera.postgress.data.api.repository.model.quotes.QuoteRequest;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class QuotesService {
    public Optional<Map<String,Quote>> getQuickQuotesByCustomerId(String customerId) {
        return Optional.of(QuotesHelper.getQuickQuotes(customerId,5));
    }

    public Quote saveQuickQuote(QuoteRequest quoteRequest) {
        return QuotesHelper.getQuickQuote(quoteRequest);
    }

    public Optional<Quote> getQuickQuoteByQuoteId(String quoteId) {
        return Optional.of(QuotesHelper.getQuickQuoteByQuoteId(quoteId));
    }
}
