package com.ch.shop.model.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ch.shop.dto.Member;
import com.ch.shop.util.MailSender;

import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class MemberServiceImpl implements MemberService{

	@Autowired
	private MemberDAO memberDAO;
	@Autowired
	private MailSender mailSender;
	
	@Override
	@Transactional
	public void registOrUpdate(Member member) {
		//회원존재여부 체크 (중복인서트 막기 위해서)
		Member obj = memberDAO.findByProvider(member);
		
		if(obj==null) {
			memberDAO.insert(member); //회원가입안되어있을 경우만 이거 	
			log.debug("회원가입처리");
			//이메일 발송 (카카오빼고) 
			mailSender.send(member.getEmail(), "회원가입축하드립니당", "축하용!");
			
		}else {
			//sns회원의 경우 자신의 프로필 변경할 수 있기때문에 mysql도 동기화 
			memberDAO.update(member);
			log.debug("업데이트");
		}
		
				
	}
	

}
