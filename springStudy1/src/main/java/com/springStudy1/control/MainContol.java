package com.springStudy1.control;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.springStudy1.DTO.School;
import com.springStudy1.DTO.User;
import com.springStudy1.service.SchoolService;
import com.springStudy1.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class MainContol {
	
	@Autowired
	private SchoolService schoolService;
	
	@Autowired
	private UserService userService;
	
	
	@GetMapping("/test") //localhost/test
	public  String testPage() {
		System.out.println("와 진짜 실행 된다.... 한글 좋아...");
		return "hello.html";
	}	
	
	// 로그인 화면
	@GetMapping("/signIn")
	public String login() {
		return "signIn";
	}
	@PostMapping("/singIn")
	public String login(@RequestParam("id") String id, @RequestParam("pw") String pw, HttpSession session) {
		//매개변수를 통해 session 객체가져오기
		boolean isSuccess = userService.loginChk(id, pw);
		if(isSuccess)
			session.setAttribute("user", id);
		
		return "index";
	}
	
	
	// 회원가입 화면
	@GetMapping("/signUp")
	public String join() {
		return "signUp";
	}
	
	@PostMapping("/signUp")
	public String joinSave(@ModelAttribute User user) {
		System.out.println(user.getUserId());
		
		userService.save(user);
		
		return "index";  // 회원가입 저장하고 첫페이지에 돌려 보내기
	}
	
	
	
	// 정보수정 화면
	@GetMapping("userUpdate")
	public String memberUpdate() {
		return "memberModify";
	}
	
	
	
	
	@GetMapping("/")
	public String homePage() {
		return "index";
	}
	
	@GetMapping("/list")
	public ModelAndView listPage(@RequestParam String type) {
		System.out.println(type);
		ModelAndView mav = new ModelAndView("list");
		//mav.setViewName("");
		
		
		// 클라이언트가 요청한 유치원, 초등학교, 중학교, 고등학교에 대해 조회 화기 위해
		// service클래스 객체에 넘겨주고 필요한 데이터를 받아서 뷰페이지와 함께 클라이언트에게 전달
		
		List<School> list = schoolService.typeSelect(type);
		mav.addObject("list", list); // ModelAndView에 저장
		
		
		return mav;
	}
	
	
	
}
// 주소: http://localhost/signIn
// 뷰 페이지: signIn.html
//		내용: 아이디, 비밀번호 입력 가능하게

/*
  
 컨트롤 클래스의 역할 지정- @Controller를 클래스 위에 넣기 
  
  클라이언트의 요청 처리
  GET 방식으로 주소 요청 들어온다면 @GetMapping
  POST방식으로 주소 요청 들어온다면 @PostMapping
  
  @GetMapping("주소")
  @GetMapping("/list") -> localhost/list 주소 요청 시 동작
  
   주소 요청시 실행 할 코드는 메서드로 표현 한다.
   @PostMapping("/save")
   public String boardSave(){
   }
   -> localhost/save 주소가 post방식으로 요청되는 경우 boardSave 메서드 실행
 
*/
