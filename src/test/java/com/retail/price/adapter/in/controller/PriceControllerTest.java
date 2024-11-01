package com.retail.price.adapter.in.controller;

import static com.retail.price.utils.TestUtils.getHeaders;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.retail.price.adapter.in.mapper.GetPriceResponseMapper;
import com.retail.price.application.port.in.GetPriceUseCase;
import com.retail.price.commons.exceptions.PriceNotFoundException;
import com.retail.price.commons.exceptions.StoreControllerExceptionHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
public class PriceControllerTest {

  private static final String URL = "/store/api/prices";
  private final GetPriceResponseMapper getPriceResponseMapper =
      Mappers.getMapper(GetPriceResponseMapper.class);
  @Mock
  private GetPriceUseCase getPriceUseCase;
  private MockMvc mockMvc;

  @BeforeEach
  public void setUp() {
    mockMvc =
        MockMvcBuilders.standaloneSetup(new PriceControllerImpl(getPriceUseCase,
                                                                getPriceResponseMapper) {})
            .setControllerAdvice(new StoreControllerExceptionHandler())
            .build();
  }

  @Test
  void givenValidRequestThen200() throws Exception {
    mockMvc
        .perform(
            MockMvcRequestBuilders.get(URL)
                .headers(getHeaders())
                .param("applicationDate", "2020-06-14T16:00:00")
                .param("productId", "35455")
                .param("brandId", "1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
  }

  @Test
  void givenNonExistentOrderThen204() throws Exception {

    doThrow(PriceNotFoundException.class)
        .when(getPriceUseCase)
        .getPrice(any(), any(), any());

    mockMvc
        .perform(
            MockMvcRequestBuilders.get(URL)
                .headers(getHeaders())
                .param("applicationDate", "2020-06-14T16:00:00")
                .param("productId", "3945")
                .param("brandId", "1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound());
  }

  @Test
  void givenNonExistentOrderThen400() throws Exception {

    mockMvc
        .perform(
            MockMvcRequestBuilders.get(URL)
                .param("applicationDate", "2020-06-14T16:00:00")
                .param("productId", "3945")
                .param("brandId", "0")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest());
  }

}