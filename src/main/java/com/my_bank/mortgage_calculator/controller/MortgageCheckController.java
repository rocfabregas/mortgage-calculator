package com.my_bank.mortgage_calculator.controller;

import com.my_bank.mortgage_calculator.client.MortgageCalculatorClient;
import com.my_bank.mortgage_calculator.dto.MortgageRequest;
import com.my_bank.mortgage_calculator.dto.MortgageResponse;
import com.my_bank.mortgage_calculator.exceptions.InvalidMortgageTypeException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/mortgage-checks")
@AllArgsConstructor
public class MortgageCheckController {

  private final MortgageCalculatorClient mortgageCalculatorClient;

  @PostMapping
  public ResponseEntity<MortgageResponse> checkMortgage(@RequestBody @Validated final MortgageRequest mortgageRequest)
      throws InvalidMortgageTypeException {
    return ResponseEntity.ok(mortgageCalculatorClient.calculateMortgage(mortgageRequest));
  }

}
