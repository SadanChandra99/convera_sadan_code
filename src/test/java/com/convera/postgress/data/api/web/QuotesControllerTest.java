package com.convera.postgress.data.api.web;

//
//import com.convera.postgress.data.api.helper.QuotesHelper;
//import com.convera.postgress.data.api.repository.model.quotes.Quote;
//import com.convera.postgress.data.api.repository.model.quotes.QuoteRequest;
//import com.convera.postgress.data.api.service.QuotesService;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.hamcrest.Matchers;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.web.client.HttpClientErrorException;
//
//import java.math.BigDecimal;
//import java.util.Map;
//import java.util.Optional;
//import java.util.UUID;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
////@WebMvcTest(controllers = QuotesController.class)
//class QuotesControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//    @InjectMocks
//    private ObjectMapper objectMapper;
//
//    @MockBean
//    private QuotesService quotesService;
//
//    @Test
//    void getQuickQuotes_success() throws Exception {
//        String customerId = "1068914";
//        when(quotesService.getQuickQuotesByCustomerId(any()))
//                .thenReturn(Optional.of(QuotesHelper.getQuickQuotes(customerId,3)));
//
//        this.mockMvc.perform(get("/quick-quotes")
//                        .queryParam("customerId",customerId))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.data", Matchers.isA(Map.class)));
//    }
//
////    @Test
////    void getQuickQuotes_notFound() throws Exception {
////        String customerId = "12345";
////        when(quotesService.getQuickQuotesByCustomerId(any()))
////                .thenReturn(Optional.empty());
////        this.mockMvc.perform(get("/quick-quotes")
////                        .queryParam("customerId",customerId))
////                .andExpect(status().isNotFound());
////    }
//
//    @Test
//    void saveQuickQuote_success() throws Exception {
//        String customerId = UUID.randomUUID().toString();
//        String quoteId = "1068914";
//        Quote quote = QuotesHelper.getQuickQuote(quoteId);
//        quote.setCustomerId(customerId.toString());
//
//        QuoteRequest quoteRequest = QuoteRequest.builder()
//                .specifiedAmount(new BigDecimal(1000))
//                .specifiedCurrency("USD")
//                .payReceive("PAY")
//                .payCurrency("CAD")
//                .customerId(customerId.toString())
//                .receiveCurrency("USD")
//                .build();
//
//
//        when(quotesService.saveQuickQuote(any())).thenReturn(quote);
//        this.mockMvc.perform(
//                        post("/quick-quotes")
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .content(objectMapper.writeValueAsString(quoteRequest))
//                )
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.data.quoteId", Matchers.is(quote.getQuoteId())))
//                .andExpect(jsonPath("$.data.customerId", Matchers.is(quote.getCustomerId())));
//    }
//
//    @Test
//    void saveQuickQuote_ThrowBadRequest() throws Exception {
//        QuoteRequest quoteRequest = QuoteRequest.builder()
//                .specifiedAmount(new BigDecimal(1000))
//                .specifiedCurrency("USD")
//                .payReceive("PAY")
//                .payCurrency("CAD")
//                .customerId("USD")
//                .receiveCurrency("USD")
//                .build();
//
//        when(quotesService.saveQuickQuote(any())).thenThrow(new HttpClientErrorException(HttpStatus.BAD_REQUEST));
//        this.mockMvc.perform(
//                        post("/quick-quotes")
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .content(objectMapper.writeValueAsString(quoteRequest))
//                )
//                .andDo(print())
//                .andExpect(status().isBadRequest())
//                .andExpect(jsonPath("$.metadata.statusCode", Matchers.is(400)))
//                .andExpect(jsonPath("$.metadata.statusDescription", Matchers.is("Bad request")));
//    }
//
//    @Test
//    void saveQuickQuote_500() throws Exception {
//        QuoteRequest quoteRequest = QuoteRequest.builder()
//                .specifiedAmount(new BigDecimal(1000))
//                .specifiedCurrency("USD")
//                .payReceive("PAY")
//                .payCurrency("CAD")
//                .customerId("USD")
//                .receiveCurrency("USD")
//                .build();
//
//        when(quotesService.saveQuickQuote(any())).thenThrow(new RuntimeException("error"));
//        this.mockMvc.perform(
//                        post("/quick-quotes")
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .content(objectMapper.writeValueAsString(quoteRequest))
//                )
//                .andDo(print())
//                .andExpect(status().is5xxServerError())
//                .andExpect(jsonPath("$.metadata.statusCode", Matchers.is(500)))
//                .andExpect(jsonPath("$.metadata.statusDescription", Matchers.is("Internal Server Error")));
//    }
//
//
//    @Test
//    void getQuickQuote_success() throws Exception {
//        String quoteId = "21cb32a7-cb11-421f-84f5-c245578a1406";
//        Quote quote = QuotesHelper.getQuickQuoteByQuoteId(quoteId);
//        when(quotesService.getQuickQuoteByQuoteId(any()))
//                .thenReturn(Optional.of(quote));
//
//        this.mockMvc.perform(get("/quick-quotes/{quoteId}", quoteId))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.data.quoteId", Matchers.is(quote.getQuoteId())))
//                .andExpect(jsonPath("$.data.customerId", Matchers.is(quote.getCustomerId())));
//    }
//
////    @Test
////    void getQuickQuote_notFound() throws Exception {
////        String quoteId = "21cb32a7-cb11-421f-84f5-c245578a1406";
////        when(quotesService.getQuickQuotesByCustomerId(any()))
////                .thenReturn(Optional.empty());
////        this.mockMvc.perform(get("/quick-quotes/{quoteId}", quoteId))
////                .andExpect(status().isNotFound());
////    }
//}