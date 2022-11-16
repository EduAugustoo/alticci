package com.eduardo.alticci.services;

import com.eduardo.alticci.errors.BusinessException;
import com.eduardo.alticci.services.impl.AlticciServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AlticciServiceImplTests {

    @Test
    @DisplayName("When calculate sequence with valid number then return correct result")
    void whenCalculateValidNumberThenReturnResult() {
        var alticciService = new AlticciServiceImpl();

        var result = alticciService.calculateAlticciValue(0);
        assertThat(result).isEqualTo(BigInteger.ZERO);

        result = alticciService.calculateAlticciValue(1);
        assertThat(result).isEqualTo(BigInteger.ONE);

        result = alticciService.calculateAlticciValue(2);
        assertThat(result).isEqualTo(BigInteger.ONE);

        result = alticciService.calculateAlticciValue(30);
        assertThat(result).isEqualTo(new BigInteger("2513"));

        result = alticciService.calculateAlticciValue(50);
        assertThat(result).isEqualTo(new BigInteger("696081"));
    }

    @Test
    @DisplayName("When calculate sequence with invalid number then throw exception")
    void whenCalculateInvalidNumberThenThrowException() {
        var alticciService = new AlticciServiceImpl();
        var exception = assertThrows(BusinessException.class, () -> alticciService.calculateAlticciValue(-1));
        assertThat(exception.getMessage()).isEqualTo("Negative numbers are not allowed!");
    }
}