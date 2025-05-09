package com.mycom.myapp.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

// #1. '/' 에 대한 매핑이 없고 localhost:8080 접속 => index.html 찾는다.
//	  없으면 /error 매핑 찾는다. 이것마저 없으면 whitelabel page 보여준다.
// #2. '/' 에 대한 매핑이 있고, 리턴 문자열이 "home" 이면 static 폴더에서 home 파일 찾는다.
// 	  없으면 /error 매핑 찾는다. 이것마저 없으면 whitelabel page 보여준다.
//	  있으면 비록 파일의 내용이 html이어도 download
// #3. '/' 에 대한 매핑이 있고, 리턴 문자열이 "home.html" 이면 static 폴더에서 home.html 파일 찾는다.
//		없으면 /error 매핑 찾는다. 이것마저 없으면 whitelabel page 보여준다.
//		있으면 html 페이지 정상 처리
// #4. static 컨텐츠인 html에 대한 직접 요청(주소창에 직접입력) vs 간접 요청(컨트롤러 경유) (둘 중 어떤게 나은지 정답은 없음)

@Controller
public class PageController {

	// #2
//	@GetMapping("/")
//	public String home() {
//	    System.out.println("/");
//	    return "home";
//	}

	// #3
	@GetMapping("/")
	public String home() {
	    System.out.println("/");
	    return "home.html";
	}

	// Circular view path [home]: would dispatch back to the current handler URL [/home] again.
	// /home으로 들어가면 return에 있는 home을 찾아 반환하고, 또 /home이 호출되고... 반복돼서 오류
//	@GetMapping("/home")
//	public String home2() {
//	    System.out.println("/");
//	    return "home";
//	}
	
	// page 요청
	@GetMapping("/login")
	public String login() {
	    System.out.println("/login");
	    return "login.html";
	}
	
	
	@PostMapping("/login")
	public String login(@RequestParam("username") String username, @RequestParam("password") String password) {
	    System.out.println(username + ", " + password);
	    return "redirect:main.html";
	}
	
	// ajax 요청
	@GetMapping("/login2")
	public String login2() {
	    System.out.println("/login2");
	    return "login2.html";
	}
	
	@PostMapping("/login2")
	@ResponseBody
	public Map<String, String> login2(@RequestParam("username") String username, @RequestParam("password") String password) {
	    Map<String, String> map = new HashMap<>();
	    map.put("result", "success");
	    
	    return map;
	}
}
 