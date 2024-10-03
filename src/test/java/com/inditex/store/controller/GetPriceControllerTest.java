package com.inditex.store.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.inditex.store.commons.exceptions.PriceNotFoundException;
import com.inditex.store.commons.exceptions.StoreControllerExceptionHandler;
import com.inditex.store.price.api.controller.GetPriceControllerImpl;
import com.inditex.store.price.api.mapper.GetPriceResponseMapper;
import com.inditex.store.price.domain.usecase.GetPriceUseCase;
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
public class GetPriceControllerTest {

  private static final String URL = "/inditex/api/prices";
  private final GetPriceResponseMapper getPriceResponseMapper =
      Mappers.getMapper(GetPriceResponseMapper.class);
  @Mock
  private GetPriceUseCase getPriceUseCase;
  private MockMvc mockMvc;

  @BeforeEach
  public void setUp() {
    mockMvc =
        MockMvcBuilders.standaloneSetup(new GetPriceControllerImpl(getPriceUseCase,
                                                                   getPriceResponseMapper) {})
            .setControllerAdvice(new StoreControllerExceptionHandler())
            .build();
  }

  @Test
  void givenValidRequestThen200() throws Exception {
    mockMvc
        .perform(
            MockMvcRequestBuilders.get(URL)
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
                .param("applicationDate", "2020-06-14T16:00:00")
                .param("productId", "3945")
                .param("brandId", "1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isNoContent());
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