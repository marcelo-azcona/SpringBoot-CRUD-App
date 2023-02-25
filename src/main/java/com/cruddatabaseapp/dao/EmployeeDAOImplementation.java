package com.cruddatabaseapp.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cruddatabaseapp.entity.Employee;

@Repository
public class EmployeeDAOImplementation implements EmployeeDAO {

	// define fields for entityManager
	private EntityManager entityManager;
	
	//set up constructor injection
	@Autowired
	public EmployeeDAOImplementation(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}
	
	@Override
	// @Transactional
	public List<Employee> findAll() {

		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		
		// create a query for the DB
		Query<Employee> theQuery = currentSession.createQuery("from Employee", Employee.class);
		
		// execute the query and get the result list
		List<Employee> employees = theQuery.getResultList();
		
		// return the results
		return employees;
	}
	
	@Override
	// @Transactional
	public Employee findById(int id) {
		
		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		
		// get the employee from the DB
		Employee theEmployee = currentSession.get(Employee.class, id);
		
		return theEmployee;
	}

	@Override
	// @Transactional
	public void save(Employee theEmployee) {
		
		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
				
		// save the employee
		currentSession.saveOrUpdate(theEmployee);
	}

	@Override
	// @Transactional
	public void deleteById(int id) {
		
		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
				
		// delete object with primary key
		Query<Employee> theQuery = currentSession.createQuery(
										"delete from Employee where id=:employeeId");
		
		theQuery.setParameter("employeeId", id);
		
		theQuery.executeUpdate();
	}

}
