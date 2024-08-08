package com.cg.in.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cg.in.entities.EmployeeBo;
import com.cg.in.entities.EmployeeEo;
import com.cg.in.service.AdminServiceImpl;
import com.cg.in.utils.constants.Utility;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@RestController
@RequestMapping("/client/employees")
public class AdminController {

	private static final Logger logger=LoggerFactory.getLogger(AdminController.class);
	
    private final AdminServiceImpl employeeService;

    public AdminController(AdminServiceImpl employeeService) {
        this.employeeService = employeeService;
    }

    
    @CircuitBreaker(name = "restTemplateCircuitBreaker", fallbackMethod = "fallbackGetAllEmployees")
    @GetMapping("/resttemplate")
    //public List<EmployeeBo> getAllEmployeesRestTemplate() {
    public ResponseEntity<?> getAllEmployeesRestTemplate() {
    	log.info(Utility.ALLEMP);
        return ResponseEntity.ok(employeeService.getAllEmployeesRestTemplate());
    }
  //  public List<EmployeeBo> fallbackGetAllEmployees(Throwable t) {
    public ResponseEntity<?> fallbackGetAllEmployees(Throwable t) {
        // Return a response entity with an error message
    	logger.error("Fallback method called due to {}",t.getMessage());
        return ResponseEntity.status(500)
                .body("The first application is stopped.");
    	
//        return "Fallback response";

    }
    
    
    
    @CircuitBreaker(name = "restTemplateCircuitBreaker", fallbackMethod = "fallbackGetEmployeeById")
    @GetMapping("/resttemplateid")
    public ResponseEntity<?> getEmployeeByIdRestTemplate(@RequestParam("id") int id) {
        return ResponseEntity.ok(employeeService.getEmployeeByIdRestTemplate(id));
    }
    public ResponseEntity<?> fallbackGetEmployeeById(int id,Throwable t) {
        
        // Return a response entity with an error message
        return ResponseEntity.status(500)
                .body("The first application did not retrieve the employee details because it is stopped.");
    }
    
    
    
    @CircuitBreaker(name = "restTemplateCircuitBreaker", fallbackMethod = "fallbackCreateEmployee")
    @PostMapping("/resttemplate/create")
    public ResponseEntity<EmployeeBo> createEmployeeRestTemplate(@RequestBody EmployeeBo employee) {
        return employeeService.createEmployeeRestTemplate(employee);
    }
    public ResponseEntity<String> fallbackCreateEmployee(Throwable t) {
        
        // Return a response entity with an error message
        return ResponseEntity.status(500).body("The first application did not create the employee because it is stopped.");
    }
    
    

 

    @GetMapping("/feign")
    public List<EmployeeBo> getAllEmployeesFeign() {
    	log.info(Utility.ALLEMP);
        return employeeService.getAllEmployeesFeign();
    }
    
    
    @PostMapping("/feign/create")
    public ResponseEntity<EmployeeBo> createEmployee(@RequestBody EmployeeBo employee)
    {
    	return employeeService.createEmployee(employee);
    }
    
    
	
}
