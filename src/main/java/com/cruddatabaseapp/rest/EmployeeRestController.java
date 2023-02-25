package com.cruddatabaseapp.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	// read a single employee by the Id
	@GetMapping("/employees/{employeeId}")
	public Employee getEmployee(@PathVariable int employeeId){
		
		Employee theEmployee = employeeService.findById(employeeId);
		
		if (theEmployee == null) {
			throw new RuntimeException("Employee not found " + employeeId);
		}
		
		return theEmployee;
	}
	
	// Add a new employee - RequestBody is for the rquest body data transformation: JSON data --> Object data
	@PostMapping("/employee/{employeeId}")
	public Employee addEmployee(@RequestBody Employee theEmployee) {
		
		// to force a save of a new item, instead of an update. The DB will asign the id.
		theEmployee.setId(0);
		
		employeeService.save(theEmployee);
		
		return theEmployee;
	}
	
	// Update employee
	@PutMapping("/employees")
	public Employee updateEmployee(@RequestBody Employee theEmployee) {
			
		employeeService.save(theEmployee);
			
		return theEmployee;
	}
	
	@DeleteMapping("/employee/{employeeId}")
	public String deleteEmployee(@PathVariable int employeeId) {
		
		Employee tempEmployee = employeeService.findById(employeeId);
			
		if (tempEmployee == null) {
			throw new RuntimeException("Employee id not found " + employeeId);
		}
		
		employeeService.deleteById(employeeId);
		
		return "Deleted employee with the id: " + employeeId;
	}
}
