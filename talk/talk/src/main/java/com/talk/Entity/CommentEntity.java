package com.talk.Entity;

import java.time.LocalDate;

import com.talk.DTO.CommentDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentEntity {
	private int id;
	private int boardId;
	private String memberId;
	private String content;
	private LocalDate writeDate;
	
public static CommentEntity from(CommentDto commentDto) {
	CommentEntity commentEntity = new CommentEntity();
	
	return commentEntity;
}

}
