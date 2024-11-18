package com.my_bank.mortgage_calculator.exceptions;

public class InvalidMaturityPeriodException extends RuntimeException {

  public InvalidMaturityPeriodException(String maturityPeriod) {
    super("Invalid maturity period: " + maturityPeriod);
  }
}
