package com.my_bank.mortgage_calculator.dto;

import lombok.Data;

@Data
public class MortgageRequest {

  private Amount income;
  private int maturityPeriod;
  private Amount loanValue;
  private Amount homeValue;

}
