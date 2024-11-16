package com.my_bank.mortgage_calculator.dto;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class MortgageResponse {

  boolean isFeasible;
  BigDecimal monthlyCost;

}
