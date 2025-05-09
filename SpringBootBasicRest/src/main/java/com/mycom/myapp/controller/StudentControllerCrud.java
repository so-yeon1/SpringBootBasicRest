package com.mycom.myapp.controller;

import com.mycom.myapp.dto.StudentDto;
import com.mycom.myapp.dto.StudentResultDto;
import com.mycom.myapp.entity.Student;
import com.mycom.myapp.service.StudentServiceCrud;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//Controller의 파라미터로 사용되는 DTO는 기본생성자 + 전체생성자를 포함해야 한다.
// REST를 적용하면 /api/v1
// get list : /students
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")

@Tag(name="기본 Student CRUD API", description="Student의 등록, 수정, 삭제, 목록 조회, 상세 조회 기능을 REST API로 제공합니다.")
public class StudentControllerCrud {
	private final StudentServiceCrud studentServiceCrud;

	@Operation(summary="학생 목록 조회", description = "전체 학생 목록을 조회합니다.")
	@GetMapping("/students") // Get: list
	public StudentResultDto listStudent(){
		return studentServiceCrud.listStudent();
	}

	@Operation(summary="학생 상세 조회", description = "개별 학생 목록을 조회합니다.", deprecated = true)
	@GetMapping("/students/{id}")
	public StudentResultDto detailStudent(@PathVariable("id") Integer id) {
	    return studentServiceCrud.detailStudent(id);
	}

	@Operation(summary="학생 등록", description = "신규 학생을 등록합니다.", hidden = true)
	@PostMapping("/students") // Post: insert
	public StudentResultDto insertStudent(StudentDto studentDto) {
	    return studentServiceCrud.insertStudent(studentDto);
	}

	@Operation(summary="학생 수정", description = "학생을 수정합니다.")
	@PutMapping("/students/{id}") // Put: update
	public StudentResultDto  updateStudent(@PathVariable("id") Integer id, StudentDto studentDto) {
	    studentDto.setId(id);
		return studentServiceCrud.updateStudent(studentDto);
	}

	@Operation(summary="학생 삭제", description = "학생을 삭제합니다.")
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
