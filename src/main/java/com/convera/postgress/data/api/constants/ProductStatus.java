package com.convera.postgress.data.api.constants;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Product status class.
 *
 * @author Vikram Sahl
 */
public enum ProductStatus {
    @JsonProperty("available")
    AVAILABLE,
    @JsonProperty("sold-out")
    SOLD_OUT
}
