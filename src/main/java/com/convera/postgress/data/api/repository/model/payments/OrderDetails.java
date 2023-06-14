package com.convera.postgress.data.api.repository.model.payments;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDetails {

    private String id;
    private String orderStatus;
    private String fundingStatus;
    private String releaseStatus;
    private LocalDateTime releaseDate;
    private LocalDateTime createdOn;
    private LocalDateTime lastUpdatedOn;
    private boolean release;
    private Integer totalNumberOfTransactions;
    private BigDecimal totalAmountOfTransactions;


}
