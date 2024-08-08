package com.cg.in.config;

import java.time.Duration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;

@Configuration
public class Resilience4jConfig {
	 @Bean
	    public CircuitBreakerRegistry circuitBreakerRegistry() {
	        return CircuitBreakerRegistry.ofDefaults();
	    }

	    @Bean
	    public CircuitBreakerConfig customCircuitBreakerConfig() {
	        return CircuitBreakerConfig.custom()
	                .failureRateThreshold(50)
	                .waitDurationInOpenState(Duration.ofMillis(1000))
	                .slidingWindowSize(10)
	                .build();
	    }

}
