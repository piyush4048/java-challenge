package jp.co.axa.apidemo.repositories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.Optional;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


import jp.co.axa.apidemo.ApiDemoApplicationTests;
import jp.co.axa.apidemo.entities.Employee;


import java.util.List;



/**
 * Jnuit test for Repository layer
 *
 */
public class EmployeeRepositoryTests extends ApiDemoApplicationTests{
	
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	static Long id;
	
	
	/**
	 * JUnit test for saveEmployee
	 * 
	 */
	@Test
	 public void test1() {
		Employee givenEmp = Employee.builder().name("test").department("Java").salary(100).build();
		Employee  savedEmp= employeeRepository.save(givenEmp);
		assertNotNull(savedEmp);
		assertEquals(savedEmp.getName(), givenEmp.getName());
		assertEquals(savedEmp.getDepartment(), givenEmp.getDepartment());
		assertEquals(savedEmp.getSalary(), savedEmp.getSalary());
		id = savedEmp.getId();
	}
	
	/**
	 * JUnit test to get all Employees
	 * 
	 */
	@Test
	 public void test2() {
		List<Employee> empList = employeeRepository.findAll();
		assertEquals(empList.size()>0, true);
	}
	
	/**
	 * JUnit test to get a specific getEmployee
	 * 
	 */
	@Test
	 public void test3() {
		Optional<Employee> retrieveEmp = employeeRepository.findById(id);
		assertNotNull(retrieveEmp.get());
		assertEquals(retrieveEmp.get().getName(), "test");
		assertEquals(retrieveEmp.get().getDepartment(), "Java");
		assertEquals(retrieveEmp.get().getSalary(), Integer.valueOf(100));
	}
	
	/**
	 * JUnit test to update Employee
	 * 
	 */
	@Test
	 public void test4() {
		Employee emp = employeeRepository.findById(id).get();
          emp.setName("updated_name");
         Employee updatedEmp =  employeeRepository.save(emp);
		assertEquals(updatedEmp.getName(), "updated_name");
	}
	
	/**
	 * JUnit test to delete employee
	 * 
	 */
	@Test
	 public void test5() {
		employeeRepository.deleteById(id);
		assertFalse(employeeRepository.findAll().stream().filter(a -> a.getId()==id).findFirst().isPresent());
	}
}
