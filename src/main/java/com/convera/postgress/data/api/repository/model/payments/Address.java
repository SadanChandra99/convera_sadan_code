package com.convera.postgress.data.api.repository.model.payments;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Address {
    private String line;
    private String line2;
    private String townOrCityName;
    private String stateOrProvince;
    private String zipCode;
    private String countryCode;
}
