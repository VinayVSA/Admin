package com.cg.in.helper;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.cg.in.entities.EmployeeBo;

import jakarta.validation.Valid;
//import com.cg.in.entities.EmployeeEo;
@Service
@FeignClient(name = "employeeClient", url = "${employee.service.url}")
public interface FeignClientHelper {

	 @GetMapping
	 List<EmployeeBo> getEmployee();
	
	 @PostMapping("/create")
	 public ResponseEntity<EmployeeBo> createEmployee(@RequestBody EmployeeBo employee);
}
