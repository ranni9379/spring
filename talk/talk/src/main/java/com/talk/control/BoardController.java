package com.talk.control;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.talk.DTO.BoardDto;
import com.talk.DTO.CommentDto;
import com.talk.service.BoardService;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/board")
@Controller
public class BoardController {
		
	@Autowired

	private BoardService boardService;

	//목록
	@GetMapping("/index")
	public String boardIndex(@RequestParam("page") int pageNum, Model model) {
	
	return "board/boardList";
	}
	//글 페이지
	@GetMapping("/write")
	public String boardWrite(HttpSession session, Model model) {
		model.addAttribute("boardDto", new BoardDto());
	
	return "board/boardWrite";
	}
	
	//저장
	@PostMapping("/writeSave")
	public String boardWrite(BoardDto boardDto, 
			@RequestParam("imgFile")  MultipartFile multipartFile, HttpSession session,
		 Model model) {
		String memberId = (String)session.getAttribute("user");
		boardService.boardSave(boardDto, memberId, multipartFile);
	
	return "redirect:/board/index?page=1";
	}
	@GetMapping("/delete")
	public String boardDelte(@RequestParam("id") int id, Model model) {
	
	return null;
	}
	@GetMapping("/update")
	public String boardUpdate(BoardDto boardDto, HttpSession session) {
	
	return null;}
	
	@GetMapping("/detail")
	public String boardDetail(@RequestParam("id") int id, HttpSession session, Model model)
	{ return null;}
	
	@GetMapping("/updatePage")
	public String boardUpdate(@RequestParam("id") int id, HttpSession session, Model model) {
		return null;}
	
	@GetMapping("/commentSave")
	public String commentSave(CommentDto commentDto, HttpSession session) {
		return null;
	}
	@GetMapping("/commentDelete")
	public String commentDel(@RequestParam("id") int id, HttpSession session){
		return null;
	}
	
}



