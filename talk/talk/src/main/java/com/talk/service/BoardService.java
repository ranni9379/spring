package com.talk.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.talk.DTO.BoardDetailDto;
import com.talk.DTO.BoardDto;
import com.talk.DTO.BoardListDto;
import com.talk.DTO.CommentViewDto;
import com.talk.Entity.BoardEntity;
import com.talk.Entity.CommentEntity;
import com.talk.Repository.BoardRepository;
import com.talk.Repository.CommentRepository;



@Service
public class BoardService {

	@Autowired
	private BoardRepository boardRepository;
	
	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private FileService fileService;
	
	
	
	
	//게시글 저장
	public void boardSave(BoardDto boardDto, String memberId, MultipartFile multipartFile) {
		BoardEntity boardEntity = BoardDto.to(boardDto);
		boardEntity.setMemberId(memberId); // 작성자- 로그인한 회원 아이디
		
		try {
			boardEntity.setFileName(fileService.uploadFile(multipartFile));
			}catch(IOException e) {
				System.out.println("파일업로드 실패!!!");
				e.printStackTrace();
			}
		boardRepository.insert(boardEntity);
	}
	
	
	// 게시글 삭제
	public void boardDelete(int id) {
		
	}
	//게시글 수정
	public void boardUpdate(BoardDto boardDto) {
		
	}
	//게시글 상세보기
	public BoardDetailDto boardDetail(int id) {
		
		BoardEntity boardEntity = boardRepository.findById(id);
		List<CommentEntity> commentEntities = commentRepository.findByBoardIdOrderByDesc(id);
		
			// 댓글 엔티티들을 -> 댓글 dto로 변환하기 위한 부분	
			List<CommentViewDto> commentViewDtos = new ArrayList<>();	
				
			for(CommentEntity entity : commentEntities)	{
				CommentViewDto dto = CommentViewDto.from(entity);
				commentViewDtos.add(dto);
			}
			
			// 게시글 상세 페이지에 표시하기 위해 보드 엔티티를 보드디티오로 넘기기
			BoardDetailDto boardDetailDto = BoardDetailDto.of(boardEntity, commentViewDtos);
			
			return boardDetailDto;
			
				
		
	}
	//게시글 목록
	public List<BoardListDto> boardList(int pageNum){
		//한페이지에 보여줄 갯수
		int pageCnt=10;
		
		List<BoardListDto> boardListDtos = new ArrayList<>();
		
		Map<String, Integer> paging = new HashMap<>();
		paging.put("index", (pageNum-1)*pageCnt);
		paging.put("pageCnt", pageCnt);
		
		
		List<BoardEntity> boardEntities = boardRepository.findByIdBetween(paging);
		
		for(BoardEntity entity : boardEntities) {
			BoardListDto dto = BoardListDto.from(entity);
			
			boardListDtos.add(dto);
		}
		
		return boardListDtos;
	}
	//게시글 전체 갯수
	public int boardCount() {
		return boardRepository.findByAllCount();
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
