package com.crud.controller;
import java.time.LocalDate;
import java.time.*;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.crud.entity.Student;
import com.crud.exception.ResourceNotFoundException;
import com.crud.repository.StudentRepository;

// http://localhost:8080/crud/student/1
//@RequestParam(required = false) String title
@RestController
@RequestMapping("/crud")
public class StudentController {

	@Autowired
	StudentRepository studentRepository;
	
	  @GetMapping("/student")
	  public ResponseEntity<List<Student>> getAllStudent(@RequestParam("id") Integer id) {
		  
	    //@RequestParam is used to read the HTML form data provided by a user and bind it to the request parameter
		// In post man when we set QueryParam then It get used (http://localhost:8080/crud/student?id=1)
		  List<Student> students = new ArrayList<Student>();

	    if (id == null)
	    	studentRepository.findAll().forEach(students::add);
	    
	    if (students.isEmpty()) 
	      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    

	    return new ResponseEntity<>(students, HttpStatus.OK);
	  }
	  
	  @GetMapping("/student/{id}")
	  public ResponseEntity<Student> getStudentById(@PathVariable("id") int id) {
		  
		  Student student = studentRepository.findById(id);
		  
		  if(student == null)
	        throw new ResourceNotFoundException("Not found Student with id = ");

	    return new ResponseEntity<>(student, HttpStatus.OK);
	  }
	  
	  @PostMapping("/student")
	  public ResponseEntity<Student> createStudent(@RequestBody Student student) {
		  
		  Student createStudent = new Student(student.getName(), student.getSalary(),LocalDateTime.now());
		  
		 studentRepository.save(createStudent);
	    
	    return new ResponseEntity<>(createStudent, HttpStatus.CREATED);
	  }
	  // Put Used To Update
	  @PutMapping("/student/{id}")
	  public ResponseEntity<Student> updateStudent(@PathVariable("id") int id, @RequestBody Student student) {
		  
	    Student updateStudent = studentRepository.findById(id);
	    
	    	  if(updateStudent == null)
	  	        throw new ResourceNotFoundException("Not found Student with id = ");
	    	  
	    	  updateStudent.setName(student.getName());
	    	  updateStudent.setSalary(student.getSalary());
	    	  updateStudent.setTimestamp(LocalDateTime.now());
	    
	    return new ResponseEntity<>(studentRepository.save(updateStudent), HttpStatus.OK);
	  }

	  @DeleteMapping("/student/{id}")
	  public ResponseEntity<HttpStatus> deleteStudent(@PathVariable("id") int id) {
		  
	    if (studentRepository.existsById(id)) {
	    	studentRepository.deleteById(id);
	    }
	    
	    studentRepository.deleteById(id);
	    
	    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	  }
	  
	  @DeleteMapping("/student")
	  public ResponseEntity<HttpStatus> deleteAllStudents() {
	    studentRepository.deleteAll();
	    
	    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	  }

}
