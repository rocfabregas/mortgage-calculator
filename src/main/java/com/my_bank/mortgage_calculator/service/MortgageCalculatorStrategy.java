package com.my_bank.mortgage_calculator.service;

import com.my_bank.mortgage_calculator.dto.MortgageRequest;
import com.my_bank.mortgage_calculator.dto.MortgageResponse;

public interface MortgageCalculatorStrategy {

  MortgageResponse calculate(final MortgageRequest mortgageRequest);

}
