package com.talk.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.talk.DTO.MemberSignInDto;
import com.talk.DTO.MemberSignUpDto;
import com.talk.service.MemberService;

@Controller
@RequestMapping("/member")
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
@GetMapping("/signUp")
public String signUp(MemberSignUpDto memberSignUp, Model model)
{
	return null;}

@GetMapping("/signIn")
public String signIn(MemberSignInDto memberSignInDto, Model model)
{ return null;}
}
