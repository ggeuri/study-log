package com.ch.shop.model.member;

import com.ch.shop.dto.Member;

public interface MemberDAO {
	
	public void insert(Member member);
	public Member findByProvider(Member member);
	public void update(Member member);

}
