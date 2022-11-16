package com.eduardo.alticci.controllers;

import com.eduardo.alticci.errors.BusinessException;
import com.eduardo.alticci.services.AlticciService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigInteger;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AlticciController.class)
public class AlticciControllerTests {

    @MockBean
    private AlticciService alticciService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("When method is called with valid number then return status 200")
    void whenCalculateValueThenStatus200() throws Exception {
        when(this.alticciService.calculateAlticciValue(any(Integer.class))).thenReturn(new BigInteger("9"));
        this.mockMvc.perform(get("/alticci/10"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(result -> assertThat(result.getResponse().getContentAsString()).isEqualTo("9"));
    }

    @Test
    @DisplayName("When method is called with invalid number then return status 400")
    void whenCalculateNegativeValueThenStatus400() throws Exception {
        when(this.alticciService.calculateAlticciValue(any(Integer.class))).thenThrow(new BusinessException("Error"));
        this.mockMvc.perform(get("/alticci/-1"))
                .andExpect(status().isBadRequest())
                .andExpect(result -> {
                    assertThat(result.getResolvedException()).isInstanceOf(BusinessException.class);
                    assertThat(result.getResolvedException().getMessage()).contains("Error");
                });
    }
}
