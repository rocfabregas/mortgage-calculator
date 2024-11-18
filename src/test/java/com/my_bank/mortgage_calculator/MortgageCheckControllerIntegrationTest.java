package com.my_bank.mortgage_calculator;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.my_bank.mortgage_calculator.dto.MortgageRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.MOCK,
    classes = MortgageCalculatorApiApplication.class)
@AutoConfigureMockMvc
public class MortgageCheckControllerIntegrationTest extends BaseTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper mapper;

  @Test
  void givenFeasibleMortgage_whenCalculatingMortgage_thenReturnIsFeasible() throws Exception {
    MortgageRequest request = buildMortgageRequest(40_000, 140000, 175000);
    mockMvc.perform(
            post("/api/v1/mortgage-checks")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(request))).andExpect(status().isOk())
        .andExpect(jsonPath("$.feasible").value(true));
  }

  @Test
  void givenUnfeasibleMortgage_whenCalculatingMortgage_thenReturnIsNotFeasible() throws Exception {
    MortgageRequest request = buildMortgageRequest(20_000, 140000, 175000);
    mockMvc.perform(
            post("/api/v1/mortgage-checks")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(request))).andExpect(status().isOk())
        .andExpect(jsonPath("$.feasible").value(false));
  }

}
