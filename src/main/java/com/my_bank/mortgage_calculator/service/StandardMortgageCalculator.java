package com.my_bank.mortgage_calculator.service;

import com.my_bank.mortgage_calculator.dto.MortgageRequest;
import com.my_bank.mortgage_calculator.dto.MortgageResponse;
import org.springframework.stereotype.Service;

@Service
public class StandardMortgageCalculator implements MortgageCalculatorStrategy {

  @Override
  public MortgageResponse calculateMortgage(MortgageRequest mortgageRequest) {
    return null;
  }
}
