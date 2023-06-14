package com.convera.postgress.data.api.repository.model.quotes;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.Nullable;
import java.math.BigDecimal;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Quote {

    private String quoteId;
    private String customerId;
    private String receiveCurrency;
    private String payCurrency;
    private BigDecimal specifiedAmount;
    private String specifiedCurrency;
    private String valueDate;
    private BigDecimal customerRate;
    private BigDecimal customerRateInverted;
    private String baseCurrency;
    private BigDecimal costRate;
    private BigDecimal receiveAmount;
    private BigDecimal payAmount;
    private Integer expirationInterval;
    private BigDecimal forwardPoints;
    private BigDecimal salesMargin;

}

