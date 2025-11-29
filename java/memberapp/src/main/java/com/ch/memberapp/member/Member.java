package com.ch.memberapp.member;

// 현실의 한명의 회원을 표현한 객체 - 이러한 용도의 객체를 가리켜 설계분야에서는 DTO,VO라함 
// DTO - Data Transfer Object 
// 주용도? JS처럼 단순히 정보 저장 용도 
// 즉 데이터만을 보유시키기위해 정의하는용도 
public class Member {
	
	
//	자바에서 아래와같이 클래스 정의하면서 멤버변수 그대로 노출시키지않음 
//	캡슐화필요. 객체안의 데이터 보호 , 데이터 제어하는 방법 대해서는 메서드를 통해 객체를 제어하는 클래스 정의기법 
//	public <     protected     < default < private
	          // 같은 패키지.상속    같은패키지      아무도 접근못함 
	
	private int member_id;
	private String id;
	private String pwd;
	private String name;
	private String regdate;
	
	public int getMember_id() {
		return member_id;
	}
	public void setMember_id(int member_id) {
		this.member_id = member_id;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}


}
