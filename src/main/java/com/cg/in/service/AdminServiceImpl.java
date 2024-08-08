package com.cg.in.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cg.in.EmployeeMapper;
import com.cg.in.entities.EmployeeBo;
import com.cg.in.entities.EmployeeEo;
import com.cg.in.helper.FeignClientHelper;
import com.cg.in.helper.RestTemplateHelper;

@Service
public class AdminServiceImpl implements AdminService {

	    private final RestTemplateHelper restTemplateHelper;
	    private final FeignClientHelper feignClientHelper;
	    private final EmployeeMapper employeeMapper;

	    @Autowired
	    public AdminServiceImpl(RestTemplateHelper restTemplateHelper, FeignClientHelper feignClientHelper, EmployeeMapper employeeMapper) {
	        this.restTemplateHelper = restTemplateHelper;
	        this.feignClientHelper = feignClientHelper;
	        this.employeeMapper = employeeMapper;
	    }
	
	@Override
	public List<EmployeeBo> getAllEmployeesRestTemplate() {
		
		 List<EmployeeEo> employees = restTemplateHelper.getAllEmployees();
	        return employees.stream()
	                .map(employeeMapper::eoToBo)
	                .collect(Collectors.toList());
	}

	@Override
	public List<EmployeeBo> getAllEmployeesFeign() {
		
		 List<EmployeeBo> employees = feignClientHelper.getEmployee();
	        return employees;
	}

	@Override
	public ResponseEntity<EmployeeBo> createEmployee(EmployeeBo employee) {
		
		return feignClientHelper.createEmployee(employee);
	}
	@Override
	public EmployeeBo getEmployeeByIdRestTemplate(int id) {
        EmployeeEo employee = restTemplateHelper.getEmployeeById(id);
        return employeeMapper.eoToBo(employee);
    }
    
	@Override
    public ResponseEntity<EmployeeBo> createEmployeeRestTemplate(EmployeeBo employeeBo) {
        EmployeeEo employeeEo = employeeMapper.boToEo(employeeBo);
        ResponseEntity<EmployeeEo> responseEntity = restTemplateHelper.createEmployee(employeeEo);
        EmployeeBo createdEmployeeBo = employeeMapper.eoToBo(responseEntity.getBody());
        return ResponseEntity.status(responseEntity.getStatusCode()).body(createdEmployeeBo);
    }
	
}
