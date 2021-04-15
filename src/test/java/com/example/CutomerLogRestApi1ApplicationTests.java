package com.example;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.Test; 
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class CutomerLogRestApi1ApplicationTests {

	@Autowired
    private MockMvc mockMvc;
	
	static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
	
	//Check IN
	@Test
	@Transactional
    public void TestCase1() throws Exception {
		
	 	mockMvc.perform(MockMvcRequestBuilders.get("/checkIn")
	 			.param("name","JoeyTribianni")
	 			.contentType(MediaType.APPLICATION_JSON)
	 			.accept(MediaType.APPLICATION_JSON))
	        	.andExpect(status().isOk())
	        	.andReturn();
	 	
    }
	
	//Get Log By Date
	@Test
	@Transactional
    public void TestCase2() throws Exception {
		
		LocalDateTime now = LocalDateTime.now();
		String[] s = dtf.format(now).split(" ");
		
	 	mockMvc.perform(MockMvcRequestBuilders.get("/getLog")
	 			.param("date",s[0])
	 			.contentType(MediaType.APPLICATION_JSON)
	 			.accept(MediaType.APPLICATION_JSON))
	        	.andExpect(status().isOk())
	        	.andReturn();
	 	
    }
	
	//Get All Log
	@Test
	@Transactional
    public void TestCase3() throws Exception {
		
	 	mockMvc.perform(MockMvcRequestBuilders.get("/getAllLog")
	 			.contentType(MediaType.APPLICATION_JSON)
	 			.accept(MediaType.APPLICATION_JSON))
	        	.andExpect(status().isOk())
	        	.andExpect(MockMvcResultMatchers.jsonPath("$[*].logType").exists())
		        .andExpect(MockMvcResultMatchers.jsonPath("$").isNotEmpty())
	        	.andReturn();
	 	
    }
	
	//Check OUT
	@Test
	@Transactional
    public void TestCase4() throws Exception {
		
	 	mockMvc.perform(MockMvcRequestBuilders.get("/checkOut")
	 			.param("name","JoeyTribianni")
	 			.contentType(MediaType.APPLICATION_JSON)
	 			.accept(MediaType.APPLICATION_JSON))
	        	.andExpect(status().isOk())
	        	.andReturn();
	 	
    }
	
	

}
