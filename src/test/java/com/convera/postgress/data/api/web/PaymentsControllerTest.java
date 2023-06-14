package com.convera.postgress.data.api.web;


import com.convera.postgress.data.api.helper.PaymentsHelper;
import com.convera.postgress.data.api.repository.model.payments.Payment;
import com.convera.postgress.data.api.service.PaymentsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = PaymentsController.class)
public class PaymentsControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @InjectMocks
    private ObjectMapper objectMapper;

    @MockBean
    private PaymentsService paymentsService;

    @Test
    void getPayment_success() throws Exception {
        String accountId = "63d372ef-3b68-4977-9602-f7a48c7756ac";
        Payment payment = PaymentsHelper.getValidPayment(accountId);
        when(paymentsService.getPaymentsByAccountId(accountId))
                .thenReturn(Optional.of(List.of(payment)));

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        System.out.println(mapper.writeValueAsString(payment));

        this.mockMvc.perform(get("/payments").queryParam("account",accountId))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data", Matchers.hasSize(1)))
                .andExpect(jsonPath("$.data.[0].id", Matchers.is(payment.getId())))
                .andExpect(jsonPath("$.data.[0].createdBy",Matchers.is(payment.getCreatedBy())));
    }

    @Test
    void getPayment_success_multipleElements() throws Exception {
        String accountId = "63d372ef-3b68-4977-9602-f7a48c7756ac";
        String id = UUID.randomUUID().toString();
        String id2 = UUID.randomUUID().toString();
        Payment payment = PaymentsHelper.getValidPayment(accountId);
        Payment payment2 = PaymentsHelper.getValidPayment(accountId);

        when(paymentsService.getPaymentsByAccountId(accountId))
                .thenReturn(Optional.of(List.of(payment,payment2)));

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        System.out.println(mapper.writeValueAsString(payment));

        this.mockMvc.perform(get("/payments").queryParam("account",accountId))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.*", Matchers.hasSize(2)))
                .andExpect(jsonPath("$.data.[0].id", Matchers.is(payment.getId())))
                .andExpect(jsonPath("$.data.[0].createdBy",Matchers.is(payment.getCreatedBy())))
                .andExpect(jsonPath("$.data.[1].id", Matchers.is(payment2.getId())))
                .andExpect(jsonPath("$.data.[1].createdBy",Matchers.is(payment.getCreatedBy())));
    }

    @Test
    void getPayment_notFound() throws Exception {
        String accountId = "63d372ef-3b68-4977-9602-f7a48c7756ac";
        when(paymentsService.getPaymentsByAccountId(accountId))
                .thenReturn(Optional.empty());
//        this.mockMvc.perform(get("/payments").queryParam("account",accountId))
//                .andExpect(status().isNotFound());
    }
}
