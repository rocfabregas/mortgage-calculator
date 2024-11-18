package com.my_bank.mortgage_calculator.client;


import com.my_bank.mortgage_calculator.entity.InterestRate;
import jakarta.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class InterestRateClient {

  private static final Map<Integer, InterestRate> INTEREST_RATES = new HashMap<>();

  @PostConstruct
  private void initializeInterestRates() {
    log.info("Initializing interest rates");

    INTEREST_RATES.put(360, InterestRate.builder().maturityPeriod(360).interestRate(BigDecimal.valueOf(3)).build());
    INTEREST_RATES.put(240, InterestRate.builder().maturityPeriod(240).interestRate(BigDecimal.valueOf(3)).build());
    INTEREST_RATES.put(180, InterestRate.builder().maturityPeriod(180).interestRate(BigDecimal.valueOf(3)).build());
  }

  public Map<Integer, InterestRate> getInterestRates() {
    log.info("Retrieving interest rates");
    return INTEREST_RATES;
  }

  public static InterestRate getInterestRateByMaturityPeriod(int maturityPeriod) {
    return INTEREST_RATES.get(maturityPeriod);
  }

}
