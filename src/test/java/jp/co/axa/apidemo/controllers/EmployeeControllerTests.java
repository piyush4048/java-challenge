package jp.co.axa.apidemo.controllers;


import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import com.fasterxml.jackson.databind.ObjectMapper;

import jp.co.axa.apidemo.ApiDemoApplicationTests;
import jp.co.axa.apidemo.entities.Employee;

import org.junit.Before;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


import org.springframework.http.MediaType;

/**
 * Jnuit test for Controller layer
 *
 */
public class EmployeeControllerTests extends ApiDemoApplicationTests{
	
	     private MockMvc mockMvc;
	     
	     private ObjectMapper objectMapper ;
	     
	     @Autowired
	     EmployeeController employeeController;
	     
	     static Long id;
	    
	    @Before
	    public void setup() {
	        mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
	        objectMapper = new ObjectMapper();
	    }
	     
	    /**
		 * JUnit test for @PostMapping("/employees") controller
		 * 
		 * @throws Exception Exception Handler
		 */
	   
	    @Test
	    public void test1() throws Exception {
	    	Employee emp = Employee.builder().name("test").salary(100).department("Java").build();
	    	ResultActions results = mockMvc.perform(post("/api/v1/employees")
	    			 .content(objectMapper.writeValueAsString(emp)).
	    			 contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	    	assertNotNull(results);
	    	id  = Long.valueOf(results.andReturn().getResponse().getContentAsString().split(":")[1].split(",")[0]);
	    }
	    
	    /**
		 * JUnit test for @GetMapping("/employees") controller 
		 * 
		 * @throws Exception Exception Handler
		 */
	    @Test
	    public void test2() throws Exception {
	    	ResultActions results = mockMvc.perform(get("/api/v1/employees").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	    	 assertNotNull(results);
	    }
	    
	    /**
		 * JUnit test for @GetMapping("/employees/{employeeId}") controller
		 * 
		 * @throws Exception Exception Handler
		 */
	    @Test
	    public void test3() throws Exception {
	    	 ResultActions results = mockMvc.perform(get("/api/v1/employees/{employeeId}", id).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	    	 assertNotNull(results);
	    	 String res = results.andReturn().getResponse().getContentAsString();
	    	assertEquals(res.contains("Java"), true);
	    	assertEquals(res.contains("test"), true);
	    	assertEquals(res.contains("100"), true);
	    }
	    
	    /**
		 * JUnit test for @PutMapping("/employees/{employeeId}") controller
		 * 
		 * @throws Exception Exception Handler
		 */
	    @Test
	    public void test4() throws Exception {
	    	Employee emp = Employee.builder().name("updated_name").salary(100).department("Java").build();
	    	 ResultActions results = mockMvc.perform(put("/api/v1/employees/{employeeId}", id).contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(emp))).andExpect(status().isOk());
	    	 assertNotNull(results);
	    	 assertEquals(results.andReturn().getResponse().getContentAsString().contains("updated_name"), true);
	    }
	    
	    /**
		 * JUnit test for @DeleteMapping("/employees/{employeeId}") controller
		 * 
		 * @throws Exception Exception Handler
		 */
	    @Test
	    public void test5() throws Exception {
	    	 ResultActions results = mockMvc.perform(delete("/api/v1/employees/{employeeId}", id).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	    	 assertNotNull(results);
	    }
}
