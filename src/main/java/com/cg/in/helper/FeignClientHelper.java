package com.cg.in.helper;

import java.util.Collections;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.cg.in.entities.EmployeeBo;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.validation.Valid;
//import com.cg.in.entities.EmployeeEo;
@Service
@FeignClient(name = "employeeClient", url = "${employee.service.url}")
public interface FeignClientHelper {

	 @CircuitBreaker(name = "feignClientCircuitBreaker", fallbackMethod = "fallbackGetEmployee")
	 @GetMapping
	 List<EmployeeBo> getEmployee();
	
	 @CircuitBreaker(name = "feignClientCircuitBreaker", fallbackMethod = "fallbackCreateEmployee")
	 @PostMapping("/create")
	 public ResponseEntity<EmployeeBo> createEmployee(@RequestBody EmployeeBo employee);
	 
	 default List<EmployeeBo> fallbackGetEmployee(Throwable t) {
	        // Implement fallback logic
	        return Collections.emptyList();
	    }

	    default ResponseEntity<EmployeeBo> fallbackCreateEmployee(Throwable t) {
	        // Implement fallback logic
	        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();
	    }
	 
}
