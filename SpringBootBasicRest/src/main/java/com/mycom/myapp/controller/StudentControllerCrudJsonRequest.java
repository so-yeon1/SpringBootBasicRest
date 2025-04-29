package com.mycom.myapp.controller;

import com.mycom.myapp.dto.StudentDto;
import com.mycom.myapp.dto.StudentResultDto;
import com.mycom.myapp.service.StudentServiceCrud;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

//Controller의 파라미터로 사용되는 DTO는 기본생성자 + 전체생성자를 포함해야 한다.
// REST를 적용하면 /api/v1
// get list : /students
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/json")
public class StudentControllerCrudJsonRequest {
	private final StudentServiceCrud studentServiceCrud;
	
	@GetMapping("/students") // Get: list
	public StudentResultDto listStudent(){
		return studentServiceCrud.listStudent();
	}
	
	@GetMapping("/students/{id}")
	public StudentResultDto detailStudent(@PathVariable("id") Integer id) {
	    return studentServiceCrud.detailStudent(id);
	}

	// 등록, 수정에 사용되는 StudentDto를 Client에서 JSON으로 보냄.
	@PostMapping("/students") // Post: insert
	public StudentResultDto insertStudent(@RequestBody StudentDto studentDto) {
	    return studentServiceCrud.insertStudent(studentDto);
	}
	
	@PutMapping("/students/{id}") // Put: update
	public StudentResultDto  updateStudent(@PathVariable("id") Integer id, @RequestBody StudentDto studentDto) {
	    studentDto.setId(id);
		return studentServiceCrud.updateStudent(studentDto);
	}
	
	@DeleteMapping("/students/{id}")
	public StudentResultDto deleteStudent(@PathVariable("id") Integer id) {
	    return studentServiceCrud.deleteStudent(id);
	}

	@GetMapping("/students/count")
	public StudentResultDto countStudent() {
		return studentServiceCrud.countStudent();
	}
	
	@GetMapping("/students/page")
	public StudentResultDto listStudent(@RequestParam("pageNumber") Integer pageNumber,
									@RequestParam("pageSize") Integer pageSize) {
		return studentServiceCrud.listStudent(pageNumber, pageSize);
	}
}
