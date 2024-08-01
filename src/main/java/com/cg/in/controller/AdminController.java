package com.cg.in.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.in.entities.EmployeeBo;
import com.cg.in.service.AdminServiceImpl;
import com.cg.in.utils.constants.Utility;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@RestController
@RequestMapping("/client/employees")
public class AdminController {

	

    private final AdminServiceImpl employeeService;

    public AdminController(AdminServiceImpl employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/resttemplate")
    public List<EmployeeBo> getAllEmployeesRestTemplate() {
    	log.info(Utility.ALLEMP);
        return employeeService.getAllEmployeesRestTemplate();
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
