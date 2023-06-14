package com.convera.postgress.data.api.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PaymentsServiceTest {

    @InjectMocks
    private PaymentsService paymentsService;

    @Test
    void getPaymentsByAccountId() {
        assertNotNull(paymentsService.getPaymentsByAccountId("63d372ef-3b68-4977-9602-f7a48c7756ac"));
    }
}