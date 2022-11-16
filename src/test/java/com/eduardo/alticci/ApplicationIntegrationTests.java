package com.eduardo.alticci;

import com.eduardo.alticci.controllers.AlticciController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.math.BigInteger;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
public class ApplicationIntegrationTests {

    private static final int REDIS_PORT = 6379;

    @Container
    static GenericContainer<?> redis = new GenericContainer<>(DockerImageName.parse("redis:7.0"))
            .withExposedPorts(REDIS_PORT);

    @Autowired
    private AlticciController alticciController;

    @Autowired
    private CacheManager cacheManager;

    @DynamicPropertySource
    static void redisProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.redis.host", () -> redis.getHost());
        registry.add("spring.redis.port", () -> redis.getMappedPort(REDIS_PORT));
    }

    @BeforeEach
    void setUp() {
        this.alticciController.getAlticciValue(10);
    }

    @Test
    @DisplayName("When calculate a cached value then return should be the cached one")
    void calculateCachedValue() {
        var result = this.alticciController.getAlticciValue(10).getBody();
        var cachedResult = Optional.ofNullable(this.cacheManager.getCache("valuesCache"))
                .map(cache -> cache.get(10, BigInteger.class));

        assertTrue(cachedResult.isPresent());
        assertEquals(cachedResult.get(), result);
        assertEquals(new BigInteger("9"), result);
    }

    @Test
    @DisplayName("When calculate a non-cached value then return should be a new one")
    void calculateNonCachedValue() {
        var result = this.alticciController.getAlticciValue(9).getBody();
        var cachedResult = Optional.ofNullable(this.cacheManager.getCache("valuesCache"))
                .map(cache -> cache.get(10, BigInteger.class));

        assertTrue(cachedResult.isPresent());
        assertNotEquals(cachedResult.get(), result);
        assertEquals(new BigInteger("7"), result);
    }
}
