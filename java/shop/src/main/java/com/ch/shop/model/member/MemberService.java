package com.ch.shop.model.member;

import com.ch.shop.dto.Member;

public interface MemberService {
	//회원가입, 만약 가입된회원이면 가입X,정보변경시 정보변경하고.. 
	public void registOrUpdate(Member member);
	

}
