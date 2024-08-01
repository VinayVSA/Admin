package com.cg.in.helper;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.cg.in.entities.EmployeeEo;

@Component
public class RestTemplateHelper {

	private final RestTemplate restTemplate;

    public RestTemplateHelper(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<EmployeeEo> getAllEmployees() {
        EmployeeEo[] employees = restTemplate.getForObject("http://localhost:8989/employee", EmployeeEo[].class);
        return Arrays.asList(employees);
    }
	
}
