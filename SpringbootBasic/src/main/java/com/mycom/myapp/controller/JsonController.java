package com.mycom.myapp.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mycom.myapp.dto.CarDto;
import com.mycom.myapp.dto.EmpDto;

// Json 응답, Json 요청 처리
// 모든 응답을 Json 으로 하는 경우라면 @RestController (@Controller + @ResponseBody)

@RestController
public class JsonController {
	
	@GetMapping("/string")
	public String m1() {
		System.out.println("/string");
		String str = "안녕핫세ㅛㅇ";
		return str; // restController이므로 html페이지 찾는게 아니라, str 문자열 리턴
	}
	
	@GetMapping("/jsonstring")
	public String m2() {
		System.out.println("/jsonstring");
		String jsonstr = "\"result\":\"success\"";
		return jsonstr; // restController이므로 html페이지 찾는게 아니라, str 문자열 리턴
	}
	
	// jsp 사용하지 않기 때문에, client에서 필요한 데이터는 백엔드에서 내려주고, 클라이언트에서 적절한 곳에 저장하고, 이를 이용해서 화면 처리
	@GetMapping("/map")
	public Map<String, String> m3() {
		System.out.println("/map");
		
		 Map<String, String> map = new HashMap<>();
	     map.put("result", "success");
	     map.put("role", "관리자");
	     
	     return map;
	}
	
	// dto
	@GetMapping("/dto")
	public CarDto m4() {
		System.out.println("/dto");
		CarDto carDto = new CarDto("소나타",20000,"홍길동");
		return carDto;
	}
	
	// dto
	@GetMapping("/listdto")
	public List<CarDto> m5() {
		System.out.println("/listdto");
		List<CarDto> list = new ArrayList<>();
		list.add(new CarDto("소나타",20000,"홍길동"));
		list.add(new CarDto("그랜저",30000,"이길동"));
		list.add(new CarDto("아반떼",10000,"삼길동"));
		
		return list;
	}
	
	// LocalDateTime 객체 json 리턴
	// spring default json converter : jackson
	// jackson vs gson

	// localdatetime
	@GetMapping("/localdatetime")
	public LocalDateTime m6() {
	    return LocalDateTime.now();
	}
	
	
	// http request에 json parameter 처리
	// (요청이 json으로 올 때)
	@PostMapping("/emp")
	public Map<String, String> m7(@RequestBody EmpDto empDto){
		System.out.println("/emp");
		System.out.println(empDto);
		Map<String, String> map = new HashMap<>();
		map.put("result", "success");
		return map;
	}
	
	@PostMapping("/emplist")
	public Map<String, String> m7(@RequestBody List<EmpDto> empList){
		System.out.println("/emplist");
		empList.forEach(empDto -> System.out.println(empDto));
		Map<String, String> map = new HashMap<>();
		map.put("result", "success");
		return map;
	}
}
 