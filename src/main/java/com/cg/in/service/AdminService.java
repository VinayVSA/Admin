package com.cg.in.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.cg.in.entities.EmployeeBo;

public interface AdminService {
	

	 public List<EmployeeBo> getAllEmployeesRestTemplate();
	 
	 public List<EmployeeBo> getAllEmployeesFeign();
	 
	 public ResponseEntity<EmployeeBo> createEmployee(EmployeeBo employee);

	EmployeeBo getEmployeeByIdRestTemplate(int id);

	ResponseEntity<EmployeeBo> createEmployeeRestTemplate(EmployeeBo employeeBo);

}
