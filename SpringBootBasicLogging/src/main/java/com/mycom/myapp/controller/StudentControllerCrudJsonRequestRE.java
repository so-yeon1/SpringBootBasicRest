package com.mycom.myapp.controller;

import com.mycom.myapp.dto.StudentDto;
import com.mycom.myapp.dto.StudentResultDto;
import com.mycom.myapp.service.StudentServiceCrud;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// ** ResponseEntity만 사용
// 1. ResultDto 사용 X. 대신 예외처리로 오류 파악, 이를 통해 ResponseEntity의 응답코드 결정
//
// ** ResponseEntity + ResultDto 함께 사용
// 1. ResultDto를 Client에게 전달, Client가 Server의 작업 결과를 ResultDto를 통해서 처리
// 2. ResultDto를 Client에게 전달, 사용 X. 대신 Controlller에서 Service에서 return한 ResultDto 객체를 이용해서
// 	  ResponseEntity의 응답코드를 결정
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/json/re")
public class StudentControllerCrudJsonRequestRE {
	private final StudentServiceCrud studentServiceCrud;
	
	@GetMapping("/students") // Get: list
	public ResponseEntity<StudentResultDto> listStudent(){
		StudentResultDto studentResultDto = studentServiceCrud.listStudent();

		// ResponseEntity + ResultDto 함께 사용 의 #1
		// 오류 없으면 resultDto 를 body에 추가, 200 OK
		// 오류 있으면 resultDto 를 body에 추가 X, 에러코드
		// client는 try-catch
		// 500 에러로 status 코드를 보내도, body에 데이터가 있으면 브라우저에서 예외처리 X.
//		return new ResponseEntity<StudentResultDto>(studentResultDto, HttpStatus.INTERNAL_SERVER_ERROR); // status code

		// 500 에러로 status 코드를 보낼 때, body에 데이터 뺌. => try-catch에서 예외 발생
//		return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR); // status code

		// ResponseEntity 객체 생성, 리턴하는 다른 표현.
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(studentResultDto);

		// 또다른 표현. (status의 메소드가 따로 있음)
//		return ResponseEntity
//				.ok()
//				.body(studentResultDto);

//		return ResponseEntity
//				.notFound()
//				.build();	// body 없이 마무리. body 를 채우면 client 오류 처리 X
	}

	@GetMapping("/students/{id}")
	public ResponseEntity<StudentResultDto> detailStudent(@PathVariable("id") Integer id) {

		// ResponseEntity + ResultDto 함께 사용 의 #2
		// Service의 리턴된 ResultDto의 result 값을 확인하고 그에 맞는 ResponseEntity 객체 생성, client 전달

		StudentResultDto studentResultDto = studentServiceCrud.detailStudent(id);
		switch (studentResultDto.getResult()) {
			case "success" : return ResponseEntity.ok().body(studentResultDto);
			case "notfound" : return ResponseEntity.notFound().build();
			case "fail" : return ResponseEntity.internalServerError().build();
			default :  return ResponseEntity.internalServerError().build();
		}

//		return ResponseEntity
//				.internalServerError()
//				.build();	// body 없이 마무리. body 를 채우면 client 오류 처리 X

//      return ResponseEntity
//              .internalServerError()
//              .build();  // body 없이 마무리

//      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
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
