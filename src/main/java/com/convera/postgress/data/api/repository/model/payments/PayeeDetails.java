package com.convera.postgress.data.api.repository.model.payments;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PayeeDetails {

    private String id;
    private PayeeType type;
    private String name;
    private PayeeContact contact;
    private Address address;
    private BankDetails bankDetails;
}
