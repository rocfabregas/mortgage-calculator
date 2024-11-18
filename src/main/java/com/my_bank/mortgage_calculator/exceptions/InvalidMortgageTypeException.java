package com.my_bank.mortgage_calculator.exceptions;

public class InvalidMortgageTypeException extends RuntimeException {
  public InvalidMortgageTypeException(String mortgageType) {
    super("Invalid mortgage type: " + mortgageType);
  }
}
