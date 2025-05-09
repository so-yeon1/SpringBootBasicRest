package com.mycom.myapp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mycom.myapp.entity.Student;
import com.mycom.myapp.service.StudentServiceCrud;

import lombok.RequiredArgsConstructor;

// return type이 Student(Entity)인데, StudentDto를 사용하는게 바람직. 지금은 연습용으로 단순화한것.
@RestController
@RequiredArgsConstructor
@RequestMapping("/students")
public class StudentControllerCrud {
	private final StudentServiceCrud studentServiceCrud;
	
	@GetMapping("/list")
	public List<Student> listStudent(){
		return studentServiceCrud.listStudent();
	}
	
	@GetMapping("/detail/{id}")
	public Optional<Student> detailStudent(@PathVariable("id") Integer id) {
	    return studentServiceCrud.detailStudent(id);
	}

	@PostMapping("/insert")
	public Student insertStudent(Student student) {
	    return studentServiceCrud.insertStudent(student);
	}
	
	@PostMapping("/update")
	public Optional<Student> updateStudent(Student student) {
	    return studentServiceCrud.updateStudent(student);
	}
	
	@GetMapping("/delete/{id}")
	public void deleteStudent(@PathVariable("id") Integer id) {
	    studentServiceCrud.deleteStudent(id);
	}

	@GetMapping("/count")
	public long countStudent() {
		return studentServiceCrud.countStudent();
	}
	
	@GetMapping("/page")
	public List<Student> listStudent(@RequestParam("pageNumber") Integer pageNumber,
									@RequestParam("pageSize") Integer pageSize) {
		return studentServiceCrud.listStudent(pageNumber, pageSize);
	}
}
