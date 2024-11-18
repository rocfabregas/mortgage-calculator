package com.my_bank.mortgage_calculator.client;

import com.my_bank.mortgage_calculator.dto.MortgageRequest;
import com.my_bank.mortgage_calculator.dto.MortgageResponse;
import com.my_bank.mortgage_calculator.exceptions.InvalidMortgageTypeException;
import com.my_bank.mortgage_calculator.service.MortgageCalculatorStrategy;
import com.my_bank.mortgage_calculator.service.StandardMortgageCalculator;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MortgageCalculatorClient {

  private static final String STANDARD_STRATEGY = "STANDARD";

  private final Map<String, MortgageCalculatorStrategy> strategies = new HashMap<>();

  public MortgageCalculatorClient() {
    strategies.put(STANDARD_STRATEGY, new StandardMortgageCalculator());
  }

  public MortgageResponse calculateMortgage(final MortgageRequest mortgageRequest) throws InvalidMortgageTypeException {
    log.info("Calculating mortgage...");

    MortgageCalculatorStrategy strategy = strategies.get(mortgageRequest.getMortgageType().toUpperCase());

    if (strategy == null) {
      log.error("Invalid mortgage type: {}", mortgageRequest.getMortgageType());
      throw new InvalidMortgageTypeException(mortgageRequest.getMortgageType());
    }

    return strategy.calculate(mortgageRequest);
  }
}
