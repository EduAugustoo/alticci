package com.eduardo.alticci.services.impl;

import com.eduardo.alticci.services.AlticciService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AlticciServiceImpl implements AlticciService {

    private static final Integer ALTICCI_ZERO = 0;
    private static final Integer ALTICCI_ONE = 1;
    private static final Integer ALTICCI_TWO = 1;

    @Cacheable("valuesCache")
    @Override
    public Integer calculateAlticciValue(Integer num) {
        log.info("Sem cache!!");
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
