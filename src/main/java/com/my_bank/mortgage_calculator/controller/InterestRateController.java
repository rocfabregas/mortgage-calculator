package com.my_bank.mortgage_calculator.controller;

import com.my_bank.mortgage_calculator.client.InterestRateClient;
import com.my_bank.mortgage_calculator.entity.InterestRate;
import java.util.Map;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/interest-rates")
@AllArgsConstructor
public class InterestRateController {

  private final InterestRateClient interestRateClient;

  @GetMapping
  public ResponseEntity<Map<Integer, InterestRate>> getInterestRates() {
    return ResponseEntity.ok(interestRateClient.getInterestRates());
  }
}
