package project;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.*;
import javax.swing.*;

import projectDialogs.LentDialog;

public class BookLent extends JFrame {
	
	BookLent(){
		setSize(600,500);
		this.setResizable(false);
		setTitle("(두면 도서관)도서 정보 검색");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(null);
		c.setBackground(Color.white);
		
		// 타이틀 만들기
		ImageIcon logoIcon = new ImageIcon("image/search.png");
		Image img = logoIcon.getImage();	// 아이콘 크기 수정을 위해 필요한 과정
		Image imgfin = img.getScaledInstance(60, 60, java.awt.Image.SCALE_SMOOTH);
		ImageIcon logoIconfin = new ImageIcon(imgfin);
		JLabel title = new JLabel("도서 대여하기", logoIconfin, SwingConstants.CENTER);
		title.setLocation(100,20);
		title.setSize(380,80);
		title.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 30));
		c.add(title);
				
		// 중간 설명 작성하기
		JLabel explain = new JLabel("<html>두면 도서관이 보유하고 있는 책을 검색합니다. <br>"
				+ "도서 대여는 대여 시스템 페이지에서 가능합니다.</html>");
		explain.setFont(new Font("본고딕 KR", Font.BOLD, 13));
		explain.setLocation(170,50);
		explain.setSize(400,120);
		c.add(explain);	
		
		// 책 이름 검색 레이블
		JLabel bookSearchLa = new JLabel("찾고자 하는 책 이름을 입력하세요");
		bookSearchLa.setOpaque(true);
		bookSearchLa.setForeground(Color.gray);
		bookSearchLa.setBackground(Color.white);
		bookSearchLa.setFont(new Font("본고딕 KR", Font.BOLD, 13));
		bookSearchLa.setLocation(100,140);
		bookSearchLa.setSize(300,30);
		c.add(bookSearchLa);
		
		// 검색 tF
		JTextField searching = new JTextField(100);
		searching.setLocation(100,170);
		searching.setSize(300,30);
		c.add(searching);
		
		// 검색 버튼
		// tf를 읽어서 DB에서 검색되도록 이벤트 설정 !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		JButton searchBtn = new JButton("검색");
		searchBtn.setLocation(410,169);
		searchBtn.setSize(70,30);
		searchBtn.setBackground(new Color(72,61,139));
		searchBtn.setFont(new Font("함초롱돋움",Font.BOLD, 15));
		searchBtn.setForeground(Color.white);
		c.add(searchBtn);
		
		// 결과 도출 패널
		//DB에서 도서 갯수 찾은 다음 count 로 도출하기!!!!!!!!!!!!!!!!!
		JPanel resultPanel = new JPanel();
		resultPanel.setSize(520,240);
		resultPanel.setLocation(30,215);
		resultPanel.setBackground(new Color(245,245,245));
		resultPanel.setLayout(null);
		c.add(resultPanel);
				
		// 결과 도출 그래프
		// DB에서 검색되도록!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		
		
		// 대여 버튼
		// 결과 도출 갯수 확인한 다음, for문으로 버튼 생성
		// 대여 버튼 누르면 다이얼로그 발생
		JButton lentBtn = new JButton("대여");
		lentBtn.setLocation(10,30);
		lentBtn.setSize(60,25);
		lentBtn.setBackground(new Color(72,61,120));
		lentBtn.setFont(new Font("함초롱돋움",Font.BOLD, 12));
		lentBtn.setForeground(Color.white);
		resultPanel.add(lentBtn);
		
		projectDialogs.LentDialog lentdialog = new LentDialog();
		lentBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				lentdialog.setVisible(true);
			}
		});
		
		// 뒤로 가기
		BackBTN back = new BackBTN(this);
		c.add(back);
				
		setVisible(true);
	}
		
		

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new BookLent();
	}
}

