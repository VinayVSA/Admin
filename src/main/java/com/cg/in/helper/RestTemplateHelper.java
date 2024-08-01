package com.cg.in.helper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.cg.in.entities.EmployeeEo;
@Service
@Component
public class RestTemplateHelper {

	private final RestTemplate restTemplate;

    public RestTemplateHelper(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Value("${employee.service.url}")
    private String employeeServiceUrl;
    
    public List<EmployeeEo> getAllEmployees() {
        EmployeeEo[] employees = restTemplate.getForObject(employeeServiceUrl, EmployeeEo[].class);
        return Arrays.asList(employees);
    }
	
}
