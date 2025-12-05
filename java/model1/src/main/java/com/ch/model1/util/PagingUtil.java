package com.ch.model1.util;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import lombok.Data;

//페이징처리로직 일일이 변수선언하면 효율성 떨어지니까 재사용가능한 객체로 정의 
@Data
public class PagingUtil {
	int totalRecord ; //총 레코드수 
	int pageSize = 10; //한페이지당 보여질 레코드 수 (참고로 이 변수명은 오라클 SQLPlus 접속기에서 사용하는 변수명)
	int totalPage ; //총 페이지수 
	int blockSize = 10; //블럭당 보여질 페이지 수 
	int currentPage = 1 ; //현재페이지 
	int firstPage ; //블럭당 반복문 시작 페이지 값  
	int lastPage;  // 블럭당 반복문 끝 페이지 값
	int num; //페이지당 시작 번호 
	int curPos; //페이지당 ArrayList 시작 인덱스 

	//복잡한 페이징 처리 로직을 아래의 메서드에서 대신 처리한다 
	// 사용자가 브라우저로 목록 요청을 할때마다 호출함 
	public void init(List list, HttpServletRequest request) {
		this.totalRecord=list.size();
		this.totalPage =  (int)Math.ceil(totalRecord / (float)pageSize);
		//사용자가 선택한 페이지수 처리 
		//파라미터 있을때만 사용자가 넘겨준 페이지명을 this.currentPage에 대입 
		if(request.getParameter("currentPage")!=null) {
			this.currentPage = Integer.parseInt(request.getParameter("currentPage"));		
		}
		// ★ 여기 한 줄만 삭제함
		this.firstPage = this.currentPage - (this.currentPage-1)%this.blockSize;
		this.lastPage = this.firstPage + (this.blockSize-1);
		this.curPos =  (this.currentPage-1)*this.pageSize;
		this.num = this.totalRecord - this.curPos;
		
	}
}