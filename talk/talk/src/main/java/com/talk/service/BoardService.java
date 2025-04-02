package com.talk.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.talk.DTO.BoardDetailDto;
import com.talk.DTO.BoardDto;
import com.talk.DTO.BoardListDto;
import com.talk.Entity.BoardEntity;
import com.talk.Repository.BoardRepository;
import com.talk.Repository.CommentRepository;

@Service
public class BoardService {

	@Autowired
	private BoardRepository boardRepository;
	
	@Autowired
	private CommentRepository commentRepository;
	
	//게시글 저장
	public void boardSave(BoardDto boardDto) {
		
	}
	
	// 게시글 삭제
	public void boardDelete(int id) {
		
	}
	//게시글 수정
	public void boardUpdate(BoardDto boardDto) {
		
	}
	//게시글 상세보기
	public BoardDetailDto boardDetail(int id) {
		
		return null;
	}
	//게시글 목록
	public List<BoardListDto> boardList(int id){
		return null;
	}
	//게시글 전체 갯수
	public int boardCount() {
		return 0;
	}
	//최근글(5개)
	public List<BoardListDto> boardRecent(){
		
		List<BoardEntity> boardEntities = boardRepository.findByOrderByWriteDateDesc();
		
		List<BoardListDto> boardListDtos = new ArrayList<>();
		for(BoardEntity board : boardEntities) {
			BoardListDto dto = BoardListDto.from(board);
			
			boardListDtos.add(dto);
		}
		
		
		return  boardListDtos;
	}
	//인기글(5개)
	public List<BoardListDto> boardPopular(){
		
		List<BoardEntity> boardEntities = boardRepository.findByOrderByHitDesc();
		
		List<BoardListDto> boardListDtos = new ArrayList<>();
		for(BoardEntity board : boardEntities) {
			BoardListDto dto = BoardListDto.from(board);
			
			boardListDtos.add(dto);
		}
		
		
		return  boardListDtos;
	
	}
}
