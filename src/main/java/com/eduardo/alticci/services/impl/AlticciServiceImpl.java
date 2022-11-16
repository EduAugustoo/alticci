package com.eduardo.alticci.services.impl;

import com.eduardo.alticci.errors.BusinessException;
import com.eduardo.alticci.services.AlticciService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
public class AlticciServiceImpl implements AlticciService {

    private static final BigInteger ALTICCI_ZERO = BigInteger.ZERO;
    private static final BigInteger ALTICCI_ONE = BigInteger.ONE;
    private static final BigInteger ALTICCI_TWO = BigInteger.ONE;

    @Cacheable("valuesCache")
    @Override
    public BigInteger calculateAlticciValue(Integer number) {
        if (number < 0) {
            throw new BusinessException("Negative numbers are not allowed!");
        }

        switch (number) {
            case 0:
                return ALTICCI_ZERO;
            case 1:
                return ALTICCI_ONE;
            case 2:
                return ALTICCI_TWO;
        }

        BigInteger first = ALTICCI_ZERO;
        BigInteger second = ALTICCI_ONE;
        BigInteger third = ALTICCI_TWO;
        BigInteger nth = BigInteger.ZERO;

        for (int i = 3; i <= number; i++) {
            nth = first.add(second);
            first = second;
            second = third;
            third = nth;
        }

        return nth;
    }
}