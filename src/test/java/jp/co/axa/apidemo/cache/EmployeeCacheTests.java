package jp.co.axa.apidemo.cache;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;

import jp.co.axa.apidemo.ApiDemoApplicationTests;
import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.services.EmployeeService;

/**
 * Jnuit test for Caching layer
 *
 */
public class EmployeeCacheTests extends ApiDemoApplicationTests{
	
 @Autowired
 CacheManager cacheManager;
 
 @Autowired
 EmployeeService employeeService;
 
  Long id;
 
 
 
 @Before
 public void setUp() {
	id = employeeService.saveEmployee(Employee.builder().name("test1").department("java").salary(100).build()).getId();
 }
 
    /**
	 *  JUnit test to check the cached data
	 * 
	 */
 @Test
 public void test1() {
	 employeeService.getEmployee(id);
	 // employee1 must be save in cache
	 assertNotNull(cacheManager.getCache("employee").get(id));
 }
 
 /**
	 *  JUnit test to check the cached data, after updating employee
	 * 
	 */
@Test
public void test2() {
	employeeService.updateEmployee(Employee.builder().id(id).name("test1").department("Go").salary(100).build());
	 // employee must be updated in cache
	 assertEquals(((Employee)cacheManager.getCache("employee").get(id).get()).getDepartment(), "Go");
}

/**
 *  JUnit test to check the cached data, after deleting employee
 * 
 */
@Test
public void test3() {
employeeService.deleteEmployee(id);

 // employee must be deleted from the cache
 assertNull(cacheManager.getCache("employee").get(id));
}
}
