package com.my_bank.mortgage_calculator.client;


import com.my_bank.mortgage_calculator.entity.InterestRate;
import jakarta.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class InterestRateClient {

  private static final List<InterestRate> interestRates = new ArrayList<>();

  @PostConstruct
  private void initializeInterestRates() {
    log.info("Initializing interest rates");

    interestRates.add(InterestRate.builder().maturityPeriod(360).interestRate(BigDecimal.valueOf(3)).build());
    interestRates.add(InterestRate.builder().maturityPeriod(240).interestRate(BigDecimal.valueOf(2)).build());
    interestRates.add(InterestRate.builder().maturityPeriod(180).interestRate(BigDecimal.valueOf(1.5)).build());
  }

  public List<InterestRate> getInterestRates() {
    log.info("Retrieving interest rates");
    return interestRates;
  }

  public static Optional<InterestRate> getInterestRateByMaturityPeriod(int maturityPeriod) {
    return interestRates.stream().filter(rate -> rate.getMaturityPeriod() == maturityPeriod).findFirst();
  }

}
