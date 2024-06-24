package com.example.demoforJPA.Controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demoforJPA.entity.Student;
import com.example.demoforJPA.repo.StudentRepo;

@RestController
public class StudentController {

	@Autowired
	StudentRepo studentRepo;
	
	@PostMapping("/api/students")
	public ResponseEntity<Student> saveStudent(@RequestBody Student student) {
		return new ResponseEntity<>(studentRepo.save(student),HttpStatus.CREATED);
	}
	
	@GetMapping("/start")
	public String Hello() {
		return "Hello world!";
	}
	
	@GetMapping("/api/students")
	public ResponseEntity<List<Student>> getStudentDeatils(){
		return new ResponseEntity<>(studentRepo.findAll(),HttpStatus.OK);
	}
	
	@GetMapping("/api/students/{id}")
	public ResponseEntity<Student> getStudentDetails(@PathVariable long id){
		Optional<Student> student = studentRepo.findById(id);
		
		if(student.isPresent()) {
			return new ResponseEntity<>(student.get(),HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("/api/students/{id}")
	public ResponseEntity<Student> updateStudentDetails(@PathVariable long id, @RequestBody Student stu){
		Optional<Student> student = studentRepo.findById(id);
		if(student.isPresent()) {
			student.get().setStudentName(stu.getStudentName());
			student.get().setStudentEmail(stu.getStudentEmail());
			student.get().setStudentAddress(stu.getStudentAddress());
			
			return new ResponseEntity<>(studentRepo.save(student.get()),HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/api/students/{id}")
	public ResponseEntity<Student> deleteStudentDetails(@PathVariable long id){
		Optional<Student> student = studentRepo.findById(id);
		
		if(student.isPresent()) {
			studentRepo.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
