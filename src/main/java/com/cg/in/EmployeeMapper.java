package com.cg.in;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.cg.in.entities.EmployeeBo;
import com.cg.in.entities.EmployeeEo;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

	 EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);

	    EmployeeBo eoToBo(EmployeeEo employeeEO);
	    EmployeeEo boToEo(EmployeeBo employeeBO);
	
}
