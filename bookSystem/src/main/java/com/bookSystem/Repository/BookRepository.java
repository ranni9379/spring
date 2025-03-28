package com.bookSystem.Repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.bookSystem.DTO.BookSearchDto;
import com.bookSystem.Entity.Book;
import com.bookSystem.Entity.BookUse;
import com.bookSystem.Entity.MyBasket;

@Mapper
public interface BookRepository {
	// 책 등록 - 테이블에 저장
public int save(Book book);

	//책 검색
	public List<Book> findByAll(BookSearchDto bookSearchDto); 
	
	//책 장바구니에 넣기 - 대여하고 싶은 도서 찜!!!
	// 맵 key - member_id->mid, book_id_> bid
	
	public int basketSave(Map<String, Integer> my);
	
	// 대출 메뉴 클릭 장박구니 테이블에 도서목록 가져오기
	public List<MyBasket> selectBasket(int memberId);
	
	// 대출 목록에 책 정보를 출력해야 하므로 mybasket 테이블에 있는 
	// book_id를 통해 책정보 조회하기, 하나씩 가져올거야!
	public Book findById(int bookId);
	
	public int deleteBasket(int id);
	
	public int loanInsert(Map<String, Integer> info);


	public List<BookUse> findByMyLoan(int memberId);


	public int returnExecuteDB(int id);


	

	
	
}
