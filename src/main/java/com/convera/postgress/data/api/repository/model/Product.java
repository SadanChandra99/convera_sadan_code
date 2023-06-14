package com.convera.postgress.data.api.repository.model;

import com.convera.postgress.data.api.constants.ProductStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * product class.
 *
 * @author Vikram Sahl
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Entity
public class Product {

  @Id
  @JsonProperty
  private long id;

  @NotNull
  @JsonProperty
  private String name;

  @NotNull
  @JsonProperty
  private String description;

  @JsonProperty
  private ProductStatus productStatus;
}
