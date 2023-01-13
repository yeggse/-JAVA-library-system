package project;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.*;
import javax.swing.*;

public class Recommand extends JFrame{
	Recommand(){
		setSize(600,500);
		this.setResizable(false);
		setTitle("(두면 도서관)이달의 도서 추천");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(null);
		c.setBackground(Color.white);
		
		// 타이틀 만들기
		ImageIcon logoIcon = new ImageIcon("image/h.png");
		Image img = logoIcon.getImage();	// 아이콘 크기 수정을 위해 필요한 과정
		Image imgfin = img.getScaledInstance(60, 60, java.awt.Image.SCALE_SMOOTH);
		ImageIcon liIconfin = new ImageIcon(imgfin);
		JLabel title = new JLabel("이 달의 추천 도서", liIconfin, SwingConstants.CENTER);
		title.setLocation(150,20);
		title.setSize(250,80);
		title.setFont(new Font("휴먼둥근헤드라인", Font.BOLD, 30));
		c.add(title);
				
		// 중간 설명 작성하기
		JLabel explain = new JLabel("<html>두면 도서관의 사서 및 회원이 선정한 이달의 추천 도서를 소개합니다.<br>"
				+ "원하시는 도서관 시스템을 선택해 주시기 바랍니다. 시민 참여 프로그램 신청은 페이지 하단에"
				+ " 기재된 번호로 유선 연락 부탁드립니다.</html>");
		explain.setFont(new Font("본고딕 KR", Font.BOLD, 12));
		explain.setLocation(100,75);
		explain.setSize(400,120);
		c.add(explain);	
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Recommand();
	}
}
