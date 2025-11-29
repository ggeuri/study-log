package com.ch.notice.gui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.ch.notice.domain.Notice;
import com.ch.notice.repository.NoticeDAO;

public class RegistForm extends JFrame{
								//is a 
//클래스가 보유한 멤버변수가 객체형일 경우 has a 관계 
	
//	생성자의 목적은 이 객체의 인스턴스가 생성될때 초기화할 작업이 있을경우 초기화 작업을 지원하기 위함이다 
	JTextField title; //제목입력 텍스트박스
	JTextField writer; //작성자입력 텍스트박스
	JTextArea content ; //내용 
	JButton bt ; 
	NoticeDAO dao ; 
	
	public RegistForm() {
		title = new JTextField(30); //텍스트박스의 디자인길이  
		//컴포넌트 부착하기전에 레이아웃을 결정짓자 CSS div로 레이아웃 
		writer = new JTextField(30);
		content = new JTextArea(10,30); 
		bt = new JButton("등록");
		dao = new NoticeDAO();
		
		setLayout(new FlowLayout()); // 수평 수직으로 흐르는 레이아웃 
		this.add(title);
		this.add(writer);
		this.add(content);
		this.add(bt);
		
		
		
		this.setSize(400,300); //너비 높
		this.setVisible(true); // 디폴트가 안보이므로 보이게 
		// TODO Auto-generated constructor stub
		
		bt.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				regist();
				
			}
		});
		
	}
	
	public void regist() {
		Notice notice = new Notice(); 
		notice.setTitle(title.getText());
		notice.setWriter(writer.getText());
		notice.setContent(content.getText());
		
		int result = dao.regist(notice);
		
		if(result<1) {
			JOptionPane.showMessageDialog(this,"실패");
		}else {
			JOptionPane.showMessageDialog(this,"성공");
			
		}
	}
	
	public static void main(String[] args) {
		
		RegistForm win = new RegistForm();
		
	}
}
