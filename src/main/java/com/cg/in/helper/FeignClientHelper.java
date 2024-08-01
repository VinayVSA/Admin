package com.cg.in.helper;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import com.cg.in.entities.EmployeeBo;
//import com.cg.in.entities.EmployeeEo;
@Service
@FeignClient(name = "employeeClient", url = "${employee.service.url}")
public interface FeignClientHelper {

	 @GetMapping
	 List<EmployeeBo> getEmployee();
	
}
