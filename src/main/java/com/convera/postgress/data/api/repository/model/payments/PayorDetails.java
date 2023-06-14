package com.convera.postgress.data.api.repository.model.payments;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PayorDetails {

    private String id;
    private String name;
    private Address address;

}
