package com.my_bank.mortgage_calculator.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.util.UUID;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MortgageRequest {

  @NotNull
  UUID customerId;
  @NotNull
  private String mortgageType;
  @NotNull
  private Amount income;
  @NotNull
  @Positive
  private int maturityPeriod;
  @NotNull
  private Amount loanValue;
  @NotNull
  private Amount homeValue;

}
