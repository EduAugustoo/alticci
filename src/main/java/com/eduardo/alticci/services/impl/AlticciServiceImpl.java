package com.eduardo.alticci.services.impl;

import com.eduardo.alticci.services.AlticciService;
import org.springframework.stereotype.Service;

@Service
public class AlticciServiceImpl implements AlticciService {

    private static final Integer ALTICCI_ZERO = 0;
    private static final Integer ALTICCI_ONE = 1;
    private static final Integer ALTICCI_TWO = 1;

    @Override
    public Integer calculateAlticciValue(Integer num) {
        switch (num) {
            case 0:
                return ALTICCI_ZERO;
            case 1:
                return ALTICCI_ONE;
            case 2:
                return ALTICCI_TWO;
        }

        return null;
    }
}
