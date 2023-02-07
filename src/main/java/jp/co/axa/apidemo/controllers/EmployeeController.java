package jp.co.axa.apidemo.controllers;

import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Employee Related Controller
 * Caching Enabled
 * 
 */
@RestController
@RequestMapping("/api/v1")
@EnableCaching
@CacheConfig(cacheNames = "employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    /**
     * To get all employees information
     * 
     * @return Employees List
     */
    @GetMapping("/employees")
    public List<Employee> getEmployees() {
        List<Employee> employees = employeeService.retrieveEmployees();
        return employees;
    }

    /**
     * To get a specific employee
     * 
     * @param employeeId Employee's Id
     * @return Employee An Employee Entity
     */
    @GetMapping("/employees/{employeeId}")
    public Employee getEmployee(@PathVariable(name="employeeId")Long employeeId) {
        return employeeService.getEmployee(employeeId);
    }

    /**
     * To Save a new Employee
     * 
     * @param employee Employee Entity to save
     * @return saved Entity
     */
    @PostMapping("/employees")
    public Employee saveEmployee(@RequestBody Employee employee){
       return employeeService.saveEmployee(employee);
    }

    /**
     * To delete an Employee
     * 
     * @param employeeId Employee's Id
     */
    @DeleteMapping("/employees/{employeeId}")
    public void deleteEmployee(@PathVariable(name="employeeId")Long employeeId){
        employeeService.deleteEmployee(employeeId);
        System.out.println("Employee Deleted Successfully");
    }

    /**
     * To update an Employee 
     * 
     * @param employee Employee entity to update
     * @param employeeId id of employee
     * @return updated Entity
     */
    @PutMapping("/employees/{employeeId}")
    public Employee updateEmployee(@RequestBody Employee employee,
                               @PathVariable(name="employeeId")Long employeeId){
        Employee emp = employeeService.getEmployee(employeeId);
        if(emp != null){
        	employee.setId(employeeId);
          return  employeeService.updateEmployee(employee);
        }
        return null;
    }
}
