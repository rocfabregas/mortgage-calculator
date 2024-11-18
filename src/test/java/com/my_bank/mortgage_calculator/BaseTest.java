package com.my_bank.mortgage_calculator;

import static org.mockito.ArgumentMatchers.anyInt;

import com.my_bank.mortgage_calculator.client.InterestRateClient;
import com.my_bank.mortgage_calculator.dto.Amount;
import com.my_bank.mortgage_calculator.dto.Currency;
import com.my_bank.mortgage_calculator.dto.MortgageRequest;
import com.my_bank.mortgage_calculator.entity.InterestRate;
import java.math.BigDecimal;
import java.util.UUID;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

public class BaseTest {

  private static MockedStatic<InterestRateClient> mockedStatic;

  @BeforeAll
  static void setup() {
    InterestRate rate = InterestRate.builder()
        .interestRate(BigDecimal.valueOf(3))
        .maturityPeriod(360)
        .build();
    mockedStatic = Mockito.mockStatic(InterestRateClient.class);
    mockedStatic.when(() -> InterestRateClient.getInterestRateByMaturityPeriod(anyInt())).thenReturn(rate);

  }

  @AfterAll
  static void tearDown() {
    if (mockedStatic != null) {
      mockedStatic.close();
    }
  }

  public MortgageRequest buildMortgageRequest(int incomeData, int loanData, int homeData) {
    Amount income = Amount.builder()
        .amount(BigDecimal.valueOf(incomeData))
        .currency(Currency.builder().name("EUR").symbol("€")
            .build()).build();

    Amount loanValue = Amount.builder()
        .amount(BigDecimal.valueOf(loanData))
        .currency(Currency.builder().name("EUR").symbol("€")
            .build()).build();

    Amount homeValue = Amount.builder()
        .amount(BigDecimal.valueOf(homeData))
        .currency(Currency.builder().name("EUR").symbol("€")
            .build()).build();

    return MortgageRequest.builder()
        .customerId(UUID.randomUUID())
        .mortgageType("STANDARD")
        .income(income)
        .maturityPeriod(360)
        .loanValue(loanValue)
        .homeValue(homeValue)
        .build();
  }

}
