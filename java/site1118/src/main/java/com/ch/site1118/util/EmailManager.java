package com.ch.site1118.util;

import java.security.MessageDigest;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;



//	이메일 발송해주는 객체정의
//javas기반에서 이미 메일을 발송할 수 있는  api가 지원됨. jar형태의 라이브러리 
//	activation-1.1.1.jar, javax.mail-1.5.5jar 
public class EmailManager {
	String host ="smtp.gmail.com";//사용하고자하는 메일서버주소 
	String user = "geurisbebe@gmail.com";//메일서버사용자계
	String password = "---------------";//앱비밀번호 
	Properties props = new Properties(); //java.util.map 자식<key-value>
	
//	메일 발송 메서드 
// to매개변수 - 메일받을 회원가입한 자 
	public void send(String to) {
//		props객체에 필요한 모든 설정정보의쌍을대입하자 
//		참고로 key값은 이미 정해진 것으로 아래의 값으로 적어야함 
		props.put("mail.smtp.host",host);
		props.put("mail.smtp.port",465); // 구글 smtp(보내는메일서버) 포트번호가 465
		props.put("mail.smtp.auth","true");
		props.put("mail.smtp.ssl.enable","true");
//		props.put("mail.smtp.ssl.trust","smtp.gmail.com");
		props.put("mail.smtp.ssl.protocols","TLSv1.2");
		
//		세션생선 javax.mail 
		Session session = Session.getDefaultInstance(props,new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
//				개발자의 구글 이메일 계정 및 앱비밀번호 입력 
				return new PasswordAuthentication(user, password);
			}
		
		}); //컨트롤시프트O해서 javax.mail로 임포트 
		
//		제목, 내용 등의 메일 작성
		MimeMessage message = new MimeMessage(session);
//		메일 발송자 
		try {
			message.setFrom(new InternetAddress(user));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setSubject("축하축하");
			message.setContent("<h1>땡큐<h2>","text/html;charset=utf-8");
			
			Transport.send(message);
			System.out.println("발송성공 ");
		} catch (Exception e) {
			System.out.println("발송실패");
			e.printStackTrace();
		} 
		
		
	}

}
