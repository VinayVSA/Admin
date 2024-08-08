package com.cg.in.helper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.cg.in.entities.EmployeeEo;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
@Service
@Component
public class RestTemplateHelper {

	private final RestTemplate restTemplate;

    public RestTemplateHelper(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Value("${employee.service.url}")
    private String employeeServiceUrl;
    
    
   // @CircuitBreaker(name = "restTemplateCircuitBreaker", fallbackMethod = "fallbackGetAllEmployees")
    public List<EmployeeEo> getAllEmployees() {
        EmployeeEo[] employees = restTemplate.getForObject(employeeServiceUrl, EmployeeEo[].class);
        return Arrays.asList(employees);
    }
	
   // @CircuitBreaker(name = "restTemplateCircuitBreaker", fallbackMethod = "fallbackGetEmployeeById")
    public EmployeeEo getEmployeeById(int id) {
        return restTemplate.getForObject(employeeServiceUrl + "/retrieve?id=" + id, EmployeeEo.class);
    }
    
    //@CircuitBreaker(name = "restTemplateCircuitBreaker", fallbackMethod = "fallbackCreateEmployee")
    public ResponseEntity<EmployeeEo> createEmployee(EmployeeEo employee) {
        return restTemplate.postForEntity(employeeServiceUrl + "/create", employee, EmployeeEo.class);
    }
    
 // Fallback methods
//    public ResponseEntity<String> fallbackGetAllEmployees(Throwable t) {
//  
//        // Return a response entity with an error message
//        return ResponseEntity.status(500)
//                .body("The first application is stopped.");
//    }
//
//    public ResponseEntity<String> fallbackGetEmployeeById(Throwable t) {
//        
//        // Return a response entity with an error message
//        return ResponseEntity.status(500)
//                .body("The first application did not retrieve the employee details because it is stopped.");
//    }
//
//    public ResponseEntity<String> fallbackCreateEmployee(Throwable t) {
//       
//        // Return a response entity with an error message
//        return ResponseEntity.status(500)
//                .body("The first application did not create the employee because it is stopped.");
//    }
    
}
