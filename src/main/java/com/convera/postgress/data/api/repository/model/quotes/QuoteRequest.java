package com.convera.postgress.data.api.repository.model.quotes;


import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuoteRequest {

    @NotEmpty
    @ApiModelProperty(
            example = "1068914",
            required = true,
            value = "Customer Id"
    )
    private String customerId;

    @NotEmpty
    @ApiModelProperty(
            example = "CAD",
            required = true,
            value = "Pay Currency"
    )
    private String payCurrency;

    @NotEmpty
    @ApiModelProperty(
            example = "USD",
            required = true,
            value = "Receive Currency"
    )
    private String receiveCurrency;

    @NotEmpty
    @ApiModelProperty(
            example = "CAD",
            required = true,
            value = "Specified Currency"
    )
    private String specifiedCurrency;

    @NotNull
    @ApiModelProperty(
            example = "150",
            required = true,
            value = "Specified Amount"
    )
    private BigDecimal specifiedAmount;

    @NotEmpty
    @ApiModelProperty(
            example = "PAY",
            required = true,
            value = "Pay Receive"
    )
    private String payReceive;

}
