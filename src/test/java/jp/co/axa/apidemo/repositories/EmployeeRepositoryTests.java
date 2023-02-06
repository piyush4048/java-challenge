package jp.co.axa.apidemo.repositories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.Optional;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


import jp.co.axa.apidemo.ApiDemoApplicationTests;
import jp.co.axa.apidemo.entities.Employee;


import java.util.List;



/**
 * Jnuit test for Repository
 *
 */
public class EmployeeRepositoryTests extends ApiDemoApplicationTests{
	
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	
	/**
	 *  JUnit test for saveEmployee
	 * 
	 */
	@Test
	 public void test1() {
		Employee givenEmp = Employee.builder().name("test").department("Java").salary(100).build();
		Employee  savedEmp= employeeRepository.save(givenEmp);
		assertEquals(savedEmp.getName(), givenEmp.getName());
		assertEquals(savedEmp.getDepartment(), givenEmp.getDepartment());
		assertEquals(savedEmp.getSalary(), savedEmp.getSalary());
		assertEquals(savedEmp.getId()>0, true);
	}
	
	/**
	 *  JUnit test for get all Employees
	 * 
	 */
	@Test
	 public void test2() {
		Employee givenEmp = Employee.builder().name("test").department("Java").salary(100).build();
		employeeRepository.save(givenEmp);
		List<Employee> empList = employeeRepository.findAll();
		assertEquals(empList.size()>0, true);
	}
	
	/**
	 *  JUnit test for getEmployee
	 * 
	 */
	@Test
	 public void test3() {
		Optional<Employee> retrieveEmp = employeeRepository.findById(2L);
		assertNotEquals(retrieveEmp.get(), null);
		assertEquals(retrieveEmp.get().getName(), "test");
		assertEquals(retrieveEmp.get().getDepartment(), "Java");
		assertEquals(retrieveEmp.get().getSalary(), Integer.valueOf(100));
		
	}

	
	
	/**
	 *  JUnit test for update Employee
	 * 
	 */
	@Test
	 public void test4() {
		Employee emp = employeeRepository.findById(2L).get();
          emp.setName("updated_name");
         Employee updatedEmp =  employeeRepository.save(emp);
		assertEquals(updatedEmp.getName(), "updated_name");
		
	}
	
	/**
	 *  JUnit test for delete employee
	 * 
	 */
	@Test
	 public void test5() {
		employeeRepository.deleteById(2l);
		assertEquals(employeeRepository.findAll().stream().filter(a -> a.getName().equals("updated_name")).findFirst().isPresent(),false);
		
	}
	
	

}
