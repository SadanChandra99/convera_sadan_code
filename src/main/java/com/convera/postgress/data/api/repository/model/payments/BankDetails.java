package com.convera.postgress.data.api.repository.model.payments;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BankDetails {

    private String agentFiOrBic;
    private String agentIbanOrBban;
    private String agentFiName;

    private Address address;
    private String accountType;
    private String accountId;
    private String accountHolderName;
}
