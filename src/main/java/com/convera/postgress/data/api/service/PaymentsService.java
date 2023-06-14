package com.convera.postgress.data.api.service;

import com.convera.postgress.data.api.helper.PaymentsHelper;
import com.convera.postgress.data.api.repository.model.payments.Payment;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PaymentsService {


    public Optional<List<Payment>> getPaymentsByAccountId(String account) {
        return Optional.of(Arrays.asList(PaymentsHelper.getValidPayment(account)
        ));
    }
}
