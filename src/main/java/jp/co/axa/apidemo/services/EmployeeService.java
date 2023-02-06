package jp.co.axa.apidemo.services;

import jp.co.axa.apidemo.entities.Employee;

import java.util.List;


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
    public Employee getEmployee(Long employeeId);
    
    

    /**
     * To save a new Employee
     * 
     * @param employee  Employee Object
     */
    public void saveEmployee(Employee employee);

    /**
     * To delete an Employee
     * 
     * @param employeeId employee's Id
     */
    public void deleteEmployee(Long employeeId);

    /**
     * To Update an Employee
     * 
     * @param employee Employee Object
     */
    public void updateEmployee(Employee employee);
}