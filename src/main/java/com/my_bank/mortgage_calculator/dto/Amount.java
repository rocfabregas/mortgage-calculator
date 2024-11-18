package com.my_bank.mortgage_calculator.dto;

import java.math.BigDecimal;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Amount {
  private BigDecimal amount;
  private Currency currency;

}
