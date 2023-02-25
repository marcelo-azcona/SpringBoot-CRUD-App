package com.cruddatabaseapp.service;

import java.util.List;

import com.cruddatabaseapp.entity.Employee;

public interface EmployeeService {
	
	public List<Employee> findAll();
	
	public Employee findById(int id);
	
	public void save(Employee theEmployee);
	
	public void deleteById(int id); 
}
