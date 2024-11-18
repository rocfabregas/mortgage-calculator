package com.my_bank.mortgage_calculator.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Currency {
  private String name;
  private String symbol;

}
