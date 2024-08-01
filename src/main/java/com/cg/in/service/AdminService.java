package com.cg.in.service;

import java.util.List;

import com.cg.in.entities.EmployeeBo;

public interface AdminService {
	

	 public List<EmployeeBo> getAllEmployeesRestTemplate();
	 
	 public List<EmployeeBo> getAllEmployeesFeign();

}
