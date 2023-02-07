package jp.co.axa.apidemo.services;

import jp.co.axa.apidemo.entities.Employee;

import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;


/**
 * Provides Employee Information with the extension of services to update, delete and save the Employees
 *
 */
public interface EmployeeService {
	
	
    /**
     * To retrive all Employees
     * 
     * @return Employees List
     */
    public List<Employee> retrieveEmployees();
    

    /**
     * To retrive an Employee
     * 
     * @param employeeId employee's Id
     * @return Employee
     */
    @Cacheable(cacheNames = "employee", key ="#employeeId")
    public Employee getEmployee(Long employeeId);
    
    
    /**
     * To save a new Employee
     * 
     * @param employee  Employee Object
     * @return saved Employee
     */
    public Employee saveEmployee(Employee employee);

    /**
     * To delete an Employee
     * 
     * @param employeeId employee's Id
     */
    @CacheEvict(cacheNames = "employee", key = "#employeeId")
    public void deleteEmployee(Long employeeId);

    /**
     * To Update an Employee
     * 
     * @param employee Employee Object
     * @return updated Employee
     */
    @CachePut(cacheNames = "employee", key = "#employee.id")
    public Employee updateEmployee(Employee employee);
}