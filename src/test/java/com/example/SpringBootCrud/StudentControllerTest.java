package com.example.SpringBootCrud;

import java.time.LocalDateTime;
import java.util.*;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.*;

import com.crud.SpringBootCrudApplication;
import com.crud.controller.StudentController;
import com.crud.entity.Student;
import com.crud.repository.StudentRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@ContextConfiguration(classes = SpringBootCrudApplication.class)
@WebMvcTest(StudentController.class)
public class StudentControllerTest {

	@MockBean
	StudentRepository studentRepository;
	
	 @Autowired
	 public ObjectMapper objectMapper;
	 
	 @Autowired
	 public MockMvc mockMvc;
	 
	  @Test
	  void shouldCreateTutorial() throws Exception {
		  Student student = new Student(1,"Sanjay Tyagi", 400, LocalDateTime.now());
		  
	    mockMvc.perform(post("/crud/student").contentType(MediaType.APPLICATION_JSON)
	        .content(objectMapper.writeValueAsString(student))) // serialising data in JSON from java object
	        .andExpect(status().isCreated())
	        .andExpect(forwardedUrl(null))
	        .andDo(print());
	  }
	  
	  @Test
	  void shouldReturnStudent() throws Exception {

		  Student student = new Student(1,"Sanjay Tyagi", 400, LocalDateTime.now());

		  // Mocking what would findById method return
	    when(studentRepository.findById(1)).thenReturn(student);
	    
	    mockMvc.perform(get("/crud/student/{id}", 1)).andExpect(status().isOk())
	        .andExpect(jsonPath("$.id").value(student.getId()))
	        .andExpect(jsonPath("$.name").value(student.getName()))
	        .andExpect(jsonPath("$.salary").value(student.getSalary()))
	        .andExpect(jsonPath("$.timestamp").value(student.getTimestamp()))
	        .andDo(print());
	  }
	  
	  @Test
	  void shouldReturnNotFoundStudent() throws Exception {

		Student student = null;
		
	    when(studentRepository.findById(1)).thenReturn(student);
	    
	    mockMvc.perform(get("/crud/student/{id}", 1))
	         .andExpect(status().isNotFound())
	         .andDo(print());
	  }
	  
	  @Test
	  void shouldUpdateStudent() throws Exception {
	    int id = 1;

	    Student student = new Student(1,"Sanjay Tyagi", 400, LocalDateTime.now());
	    
	    Student updatedstudent = new Student(1,"Sanjay Tyagi", 400, LocalDateTime.now());

	    when(studentRepository.findById(id)).thenReturn(student);
	    
	    when(studentRepository.save(any(Student.class))).thenReturn(updatedstudent);

	    mockMvc.perform(put("/crud/student/{id}", id).contentType(MediaType.APPLICATION_JSON)
	        .content(objectMapper.writeValueAsString(updatedstudent)))
	        .andExpect(status().isOk())
	        .andExpect(jsonPath("$.id").value(student.getId()))
	        .andExpect(jsonPath("$.name").value(student.getName()))
	        .andExpect(jsonPath("$.salary").value(student.getSalary()))
	        .andExpect(jsonPath("$.timestamp").value(student.getTimestamp()))
	        .andDo(print());
	  }
	  
	  @Test
	  void shouldReturnNotFoundUpdateTutorial() throws Exception {
	    int id = 2;

	    Student updatedstudent = new Student(2,"Sanjay Tyagi", 400, LocalDateTime.now());

	    when(studentRepository.findById(id)).thenReturn(null);
	    when(studentRepository.save(any(Student.class))).thenReturn(updatedstudent);

	    mockMvc.perform(put("/crud/student/{id}", id).contentType(MediaType.APPLICATION_JSON)
	        .content(objectMapper.writeValueAsString(updatedstudent)))
	        .andExpect(status().isNotFound())
	        .andDo(print());
	  }
	  
	  @Test
	  void shouldDeleteTutorial() throws Exception {
	    int id = 1;
	    // deleteById returns null that's why then can't be used with it.
	    doNothing().when(studentRepository).deleteById(id);
	    
	    mockMvc.perform(delete("/crud/student/{id}", id))
	         .andExpect(status().isNoContent())
	         .andDo(print());
	  }
	  
	  @Test
	  void shouldDeleteAllTutorials() throws Exception {
		  
	    doNothing().when(studentRepository).deleteAll();
	    
	    mockMvc.perform(delete("/crud/student"))
	         .andExpect(status().isNoContent())
	         .andDo(print());
	  }
}
