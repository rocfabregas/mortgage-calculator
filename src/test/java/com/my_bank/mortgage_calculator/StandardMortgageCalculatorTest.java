package com.my_bank.mortgage_calculator;

import com.my_bank.mortgage_calculator.dto.MortgageRequest;
import com.my_bank.mortgage_calculator.dto.MortgageResponse;
import com.my_bank.mortgage_calculator.service.StandardMortgageCalculator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class StandardMortgageCalculatorTest extends BaseTest {

  @InjectMocks
  private StandardMortgageCalculator mortgageCalculatorService;

  @Test
  void givenFeasibleMortgage_whenCalculatingMortgage_thenIsFeasible() {

    MortgageRequest request = buildMortgageRequest(40_000, 140_000, 175_000);

    MortgageResponse response = mortgageCalculatorService.calculate(request);

    Assertions.assertTrue(response.isFeasible());
  }

  @Test
  void givenUnfeasibleMortgage_whenCalculatingMortgage_thenIsNotFeasible() {

    MortgageRequest request = buildMortgageRequest(20_000, 140_000, 175_000);

    MortgageResponse response = mortgageCalculatorService.calculate(request);

    Assertions.assertFalse(response.isFeasible());

  }

}
