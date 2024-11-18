package com.my_bank.mortgage_calculator;

import com.my_bank.mortgage_calculator.client.MortgageCalculatorClient;
import com.my_bank.mortgage_calculator.dto.MortgageRequest;
import com.my_bank.mortgage_calculator.exceptions.InvalidMortgageTypeException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class MortgageCalculatorClientTest extends BaseTest {

	@InjectMocks
	private MortgageCalculatorClient mortgageCalculatorClient;

	@Test
	void givenInvalidMortgageType_whenCalculatingMortgage_thenErrorHandled() {

		MortgageRequest request = MortgageRequest.builder().mortgageType("INVALID-TYPE").build();

		Assertions.assertThrows(InvalidMortgageTypeException.class, () -> mortgageCalculatorClient.calculateMortgage(request));

	}

	@Test
	void givenValidMortgageType_whenCalculatingMortgage_thenReturn() {

		MortgageRequest request = buildMortgageRequest(40_000, 140_000, 175_000);

		Assertions.assertNotNull(mortgageCalculatorClient.calculateMortgage(request));

	}
}
