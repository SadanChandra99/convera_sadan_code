package com.convera.postgress.data.api.helper;

import com.convera.postgress.data.api.repository.model.payments.*;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public final class PaymentsHelperTest  {

    public static Payment getValidPayment(String id){
        return Payment.builder()
                .id(id)
                .createdOn(LocalDateTime.now().minusWeeks(1l))
                .lastUpdatedOn(LocalDateTime.now())
                .createdBy("converaUser")
                .fundingStatus("fundingStatus")
                .releaseStatus("releaseStatus")
                .sanctionsCheckPassed(true)
                .lastSanctionsCheckOn(LocalDateTime.now().minusDays(1l))
                .validationPassed(LocalDateTime.now().minusDays(1l).plusHours(1l))
                .paymentTransactionStatus("paymentTransactionStatus")
                .paymentTransactionStatusReason("paymentTransactionStatusReason")
                .paymentMethod("paymentMethod")
                .paymentReference("paymentReference")
                .paymentPurposeCode("paymentPurposeCode")
                .paymentPurposeDesc("paymentPurposeDesc")
                .payCurrencyCode("payCurrencyCode")
                .payAmount(new BigDecimal(1000))
                .receiveCurrencyCode("USD")
                .receiveAmount(new BigDecimal(10000))
                .exchangeRate(new BigDecimal(19.00))
                .instructionCodeForBank("Instruction Code for Bank")
                .instructionForBank("instructionForBank")
                .remittanceType("Remittance Type ")
                .chargeBearerType("BEN")
                .chargeAmount(new BigDecimal(100))
                .confirmationNumber(String.valueOf((int) Math.random() * 10000))
                .payorDetails(getValidPayorDetails())
                .payeeDetails(getValidPayeeDetails())
                .orderDetails(getValidOrderDetails())
                .build();
    }

    public static PayorDetails getValidPayorDetails(){
        return PayorDetails.builder()
                .id(UUID.randomUUID().toString())
                .name("John Doe")
                .address(getValidPayorAddress())
                .build();

    }

    private static Address getValidPayorAddress() {
        return Address.builder()
                .line("650 Ponce De Leon")
                .stateOrProvince("GA")
                .townOrCityName("Atlanta")
                .zipCode("30308")
                .countryCode("US")
                .build();
    }

    public static PayeeDetails getValidPayeeDetails(){
        return PayeeDetails.builder()
                .id(UUID.randomUUID().toString())
                .name("John Smith")
                .type(PayeeType.INDIVIDUAL)
                .contact(getValidPayeeContact())
                .address(getValidPayeeAddress())
                .bankDetails(getValidPayeeBankDetails())
                .build();
    }

    private static BankDetails getValidPayeeBankDetails() {
        return BankDetails.builder()
                .agentFiOrBic("BBVA Belgium")
                .agentIbanOrBban("FR7630006000011234567890189")
                .agentFiName("BBVA Belgium")
                .address(getValidBankDetailsAddress())
                .accountType("Checking")
                .accountId("1234567890123456")
                .accountHolderName("Pier Doe")
                .build();
    }

    private static Address getValidBankDetailsAddress() {
        return Address.builder()
                .line("Cantersteen 47")
                .stateOrProvince("Bruxelles")
                .townOrCityName("Bruxelles")
                .zipCode("1000")
                .countryCode("BE")
                .build();
    }

    private static PayeeContact getValidPayeeContact() {
        return PayeeContact.builder()
                .name("M Smith")
                .phoneNumber("(404) 881-8888")
                .mobilePhone("(404) 881-8881")
                .email("payeecontact@convera.com")
                .build();
    }

    public static OrderDetails getValidOrderDetails(){
        return OrderDetails.builder()
                .id(UUID.randomUUID().toString())
                .orderStatus("orderStatus")
                .fundingStatus("fundingStatus")
                .releaseStatus("releaseStatus")
                .releaseDate(LocalDateTime.now().minusDays(2l).plusHours(5))
                .createdOn(LocalDateTime.now().minusDays(2l))
                .release(true)
                .totalNumberOfTransactions(1)
                .totalAmountOfTransactions(new BigDecimal(10000))
                .build();
    }

    private static Address getValidPayeeAddress() {
        return Address.builder()
                .line("6 Ponce De Leon")
                .stateOrProvince("GA")
                .townOrCityName("Atlanta")
                .zipCode("30308")
                .countryCode("US")
                .build();
    }

}
