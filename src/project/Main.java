package project;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.*;
import javax.swing.*;

public class Main extends JFrame{
	public Main(){
		setSize(600,500);
		this.setResizable(false);
		setTitle("(두면 도서관)두면 도서관에 오신 것을 환영합니다.");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(null);
		c.setBackground(new Color(248,248,255));
		
		// 타이틀 만들기
		ImageIcon logoIcon = new ImageIcon("image/edu.png");
		Image img = logoIcon.getImage();	// 아이콘 크기 수정을 위해 필요한 과정
		Image imgfin = img.getScaledInstance(60, 60, java.awt.Image.SCALE_SMOOTH);
		ImageIcon liIconfin = new ImageIcon(imgfin);
		JLabel title = new JLabel("두면 도서관", liIconfin, SwingConstants.CENTER);
		title.setLocation(150,20);
		title.setSize(250,80);
		title.setFont(new Font("휴먼둥근헤드라인", Font.BOLD, 30));
		c.add(title);
				
		// 중간 설명 작성하기
		JLabel explain = new JLabel("<html>두면 도서관 온라인 회원들을 위한 서비스입니다.<br>"
				+ "원하시는 도서관 시스템을 선택해 주시기 바랍니다. 시민 참여 프로그램 신청은 페이지 하단에"
				+ " 기재된 번호로 유선 연락 부탁드립니다.</html>");
		explain.setFont(new Font("본고딕 KR", Font.BOLD, 12));
		explain.setLocation(100,75);
		explain.setSize(400,120);
		c.add(explain);	
		
		// 버튼 설명
		JLabel searchEx = new JLabel("도서 정보 검색");
		JLabel recomEx = new JLabel("이 달의 도서 추천");
		JLabel lentEx = new JLabel("도서 예약 하기");
		JLabel memoEx = new JLabel("메   모   장");
		JLabel myinfoEx = new JLabel("회원 정보 수정");
		JLabel seatEx = new JLabel("독서실 자리 예약");
		
		searchEx.setFont(new Font("본고딕", Font.BOLD, 13));
		recomEx.setFont(new Font("본고딕", Font.BOLD, 13));
		lentEx.setFont(new Font("본고딕", Font.BOLD, 13));
		memoEx.setFont(new Font("본고딕", Font.BOLD, 13));
		myinfoEx.setFont(new Font("본고딕", Font.BOLD, 13));
		seatEx.setFont(new Font("본고딕", Font.BOLD, 13));
		
		searchEx.setLocation(69,230);
		searchEx.setSize(100,40);
		recomEx.setLocation(235,230);
		recomEx.setSize(140,40);
		lentEx.setLocation(405,230);
		lentEx.setSize(101,40);
		memoEx.setLocation(80,325);
		memoEx.setSize(100,40);
		myinfoEx.setLocation(246,325);
		myinfoEx.setSize(100,40);
		seatEx.setLocation(399,325);
		seatEx.setSize(120,40);
		
		c.add(searchEx);
		c.add(recomEx);
		c.add(lentEx);
		c.add(memoEx);
		c.add(myinfoEx);
		c.add(seatEx);
		
		// 버튼 별 이벤트 활용 필요!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		// 도서 정보 검색 버튼
		JButton searchBtn = new JButton("Search");
		searchBtn.setSize(90,40);
		searchBtn.setLocation(70,190);
		c.add(searchBtn);		
		searchBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
			BookSearch booksearch =	new BookSearch();	
			setVisible(false);
			}
		});
		
		// 이달의 추천도서 버튼
		JButton recoBtn = new JButton("Hot");
		recoBtn.setSize(90,40);
		recoBtn.setLocation(245,190);
		c.add(recoBtn);	
		
		recoBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
			Recommand Recommand =new Recommand();	
			setVisible(false);
			}
		});
		
		// 도서 예약 버튼
		JButton reserveBtn = new JButton("Lent");
		reserveBtn.setSize(90,40);
		reserveBtn.setLocation(405,190);
		c.add(reserveBtn);
		
		reserveBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				BookLent lent = new BookLent();
				setVisible(false);
			}
		});
		
		// 메모시스템 버튼
		JButton MemoBtn = new JButton("Memo");
		MemoBtn.setSize(90,40);
		MemoBtn.setLocation(70,290);
		c.add(MemoBtn);	
		
		MemoBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Memo memo = new Memo();
				setVisible(false);
			}
		});
		
		// 회원정보 수정 버튼
		JButton InfoBtn = new JButton("My Info");
		InfoBtn.setSize(90,40);
		InfoBtn.setLocation(245,290);
		c.add(InfoBtn);	
		
		InfoBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				myInfo info = new myInfo();
				setVisible(false);
			}
		});
		
		// 독서실 자리예약 버튼
		JButton SeatBtn = new JButton("Seat");
		SeatBtn.setSize(90,35);
		SeatBtn.setLocation(405,290);
		c.add(SeatBtn);	
		
		SeatBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Seatting seatarea = new Seatting();
				setVisible(false);
			}
		});
		
		// 하단 정보 기재란 
		outtroLabel outline = new outtroLabel(200,405);
		outline.setBackground(new Color(248,248,255));
		c.add(outline);
		
		setVisible(true);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Main();
	}
}
