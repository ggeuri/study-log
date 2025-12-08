package com.ch.model1.dto;

import java.util.List;

import lombok.Data;

@Data
public class News {
	private int news_id; 
	private String title ; 
	private String writer; 
	private String content ; 
	private String regdate; 
	private int  hit; 
	private int cnt; 
	//하나의 뉴스기사는 다수의 자식 보유 가능 . 오늘 수업의 핵심 
	private List<Comment> commentList; 
}
