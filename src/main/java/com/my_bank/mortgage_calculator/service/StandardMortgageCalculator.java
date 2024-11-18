package com.my_bank.mortgage_calculator.service;

import com.my_bank.mortgage_calculator.client.InterestRateClient;
import com.my_bank.mortgage_calculator.dto.MortgageRequest;
import com.my_bank.mortgage_calculator.dto.MortgageResponse;
import com.my_bank.mortgage_calculator.entity.InterestRate;
import com.my_bank.mortgage_calculator.exceptions.InvalidMaturityPeriodException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class StandardMortgageCalculator implements MortgageCalculatorStrategy {

  @Override
  public MortgageResponse calculate(final MortgageRequest mortgageRequest) {
    boolean isFeasible = isFeasible(mortgageRequest);
    BigDecimal monthlyCost = BigDecimal.valueOf(0);

    if (isFeasible) {
      monthlyCost = calculateMonthlyCost(mortgageRequest);
    }

    return MortgageResponse.builder().isFeasible(isFeasible).monthlyCost(monthlyCost).build();
  }

  private boolean isFeasible(final MortgageRequest mortgageRequest) {
    BigDecimal income = mortgageRequest.getIncome().getAmount();
    BigDecimal loan = mortgageRequest.getLoanValue().getAmount();
    BigDecimal maxLoan = income.multiply(BigDecimal.valueOf(4));
    BigDecimal homeValue = mortgageRequest.getHomeValue().getAmount();

    return loan.compareTo(maxLoan) <= 0
        && loan.compareTo(homeValue) <= 0;
  }

  private BigDecimal calculateMonthlyCost(final MortgageRequest mortgageRequest) {
    BigDecimal loan = mortgageRequest.getLoanValue().getAmount();
    int months = mortgageRequest.getMaturityPeriod();
    Optional<InterestRate> rate = InterestRateClient.getInterestRateByMaturityPeriod(months);

    if (rate.isEmpty()) {
      log.error("Invalid maturity period: {}", months);
      throw new InvalidMaturityPeriodException(String.valueOf(months));
    }

    BigDecimal monthlyRate = rate.get().getInterestRate().divide(BigDecimal.valueOf(100), MathContext.DECIMAL32)
        .divide(BigDecimal.valueOf(12), MathContext.DECIMAL32);
    return loan.multiply(monthlyRate)
        .divide(BigDecimal.ONE.subtract(BigDecimal.ONE
                .divide(monthlyRate.add(BigDecimal.ONE)
                    .pow(months), MathContext.DECIMAL32)),
            MathContext.DECIMAL32);
  }
}
