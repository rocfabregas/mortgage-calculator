package com.my_bank.mortgage_calculator.entity;

import java.math.BigDecimal;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InterestRate {

  private int maturityPeriod;
  private BigDecimal interestRate;

}
