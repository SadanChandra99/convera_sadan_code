package com.convera.postgress.data.api.repository.model.payments;

import lombok.AllArgsConstructor;


import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Payment {
    private String id;
    private LocalDateTime createdOn;
    private LocalDateTime lastUpdatedOn;
    private String createdBy;
    private String fundingStatus;
    private String releaseStatus;
    private boolean sanctionsCheckPassed;
    private LocalDateTime lastSanctionsCheckOn;
    private LocalDateTime validationPassed;
    private String paymentTransactionStatus;
    private String paymentTransactionStatusReason;
    private String paymentMethod;
    private String paymentReference;
    private String paymentPurposeCode;
    private String paymentPurposeDesc;
    private String payCurrencyCode;
    private BigDecimal payAmount;
    private String receiveCurrencyCode;
    private BigDecimal receiveAmount;
    private BigDecimal exchangeRate;
    private String instructionCodeForBank;
    private String instructionForBank;
    private String remittanceType;
    private String chargeBearerType;
    private BigDecimal chargeAmount;
    private String confirmationNumber;
    private PayorDetails payorDetails;
    private PayeeDetails payeeDetails;
    private OrderDetails orderDetails;

}
