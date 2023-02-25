package com.cruddatabaseapp.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cruddatabaseapp.entity.Employee;
import com.cruddatabaseapp.service.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

	private EmployeeService employeeService;
	
	@Autowired
	public EmployeeRestController(EmployeeService theEmployeeService) {
		this.employeeService = theEmployeeService;
	}
	
	// expose the "/employees" endpoint and return list of employee
	@GetMapping("/employees")
	public List<Employee> findAll(){
		return employeeService.findAll();
	}
}
