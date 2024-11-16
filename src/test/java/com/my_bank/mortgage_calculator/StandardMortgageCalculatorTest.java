package com.my_bank.mortgage_calculator;

import com.my_bank.mortgage_calculator.dto.MortgageResponse;
import com.my_bank.mortgage_calculator.service.StandardMortgageCalculator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

@ExtendWith(MockitoExtension.class)
class StandardMortgageCalculatorTest {

	@InjectMocks
	private StandardMortgageCalculator mortgageCalculatorService;

	@Test
	@Disabled
	void givenFeasibleMortgage_whenCalculatingMortgage_thenIsFeasible() {

		MortgageResponse response = mortgageCalculatorService.calculateMortgage(null);

		Assertions.assertTrue(response.isFeasible());

	}

	@Test
	@Disabled
	void givenUnfeasibleMortgage_whenCalculatingMortgage_thenIsNotFeasible() {

		MortgageResponse response = mortgageCalculatorService.calculateMortgage(null);

		Assertions.assertFalse(response.isFeasible());

	}

}
