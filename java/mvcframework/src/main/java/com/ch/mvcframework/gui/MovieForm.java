package com.ch.mvcframework.gui;

import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.ch.mvcframework.movie.model.MovieManager;

//영화 관련 판단해주는 공통 로직을 GUI프로그래밍에서도 사용할 수 있는지 테스트 
//공통로직테스트 ! 

public class MovieForm extends JFrame{
	//has a 관계는 멤버변수 
	JButton bt; 
	JComboBox box; 
	
	
	public MovieForm() {
		bt = new JButton("피드백 요청");
		box = new JComboBox();
		String[] movies = {
				"주토피아2"
				,"귀멸의칼날"
				,"위키드2"
		};
		
		MovieManager manager = new MovieManager(); 
		
//		for(int i = 0; i < movies.length; i++) {
//			box.addItem(movies[i]);
//		}
		for (String movie : movies) {
			box.addItem(movie);
		}
		
		this.add(box);
	
		
		//버튼 부착 전에 레이아웃잡고 부착 
		setLayout(new FlowLayout()); //수평 또는 수직의 직선으로 컴포넌트 배치.윈도우 창 따라 내용물 흘러다님 
		this.add(bt);
		
		bt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String item = (String)box.getSelectedItem();
				
				String msg = manager.getAdvice(item); // 유저가 선택한 영화 !
				
				JOptionPane.showMessageDialog(MovieForm.this, msg);
				
			}
		});
		
		setSize(300,200);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		MovieForm movieForm = new MovieForm(); //MovieForm이 곧 윈도우. JFrame!! extends로 is a 관계 설정 
		
		
	}
	

}
