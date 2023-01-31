package project;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class BookSearch extends JFrame{
	Statement stmt = null;
	static String id;
	int count;
	
	// JTable
	Object ob[][] = new Object[0][5]; //데이터 표시에 열만 나오게 설정
	DefaultTableModel model;  // 데이터 저장 부분
	JTable table;
	JScrollPane js;
	String str[] = {"도 서 명", "출 판 사", "작 가", "위 치","대여여부"}; // 컬럼 명
	
	BookSearch(Statement stmt, String id){
		this.stmt = stmt;
		this.id = id;
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
		JLabel title = new JLabel("도서 정보 검색하기", logoIconfin, SwingConstants.CENTER);
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
		
		// 결과 도출 패널
		JPanel resultPanel = new JPanel();
		resultPanel.setSize(520,240);
		resultPanel.setLocation(30,215);
		resultPanel.setBackground(new Color(245,245,245));
		resultPanel.setLayout(null);
		c.add(resultPanel);
		
		// 결과 도출 그래프
		model = new DefaultTableModel(ob,str);	// 데이터 저장[][], 컬럼 명
		table = new JTable(model);
		js = new JScrollPane(table);
		js.setLocation(20,50);
		js.setSize(480,180);
		resultPanel.add(js);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		JLabel resultintro = new JLabel("찾으시는 도서명을 입력해 주세요.");
		resultintro.setLocation(150,5);
		resultintro.setSize(300,30);
		resultintro.setFont(new Font("함초롱돋움",Font.BOLD, 15));
		resultPanel.add(resultintro);
		
		// 검색 버튼
		JButton searchBtn = new JButton("검색");
		searchBtn.setLocation(410,169);
		searchBtn.setSize(70,30);
		searchBtn.setBackground(new Color(72,61,139));
		searchBtn.setFont(new Font("함초롱돋움",Font.BOLD, 15));
		searchBtn.setForeground(Color.white);

		searchBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String cnt = null;
				try {
					// 검색할 때마다 출력값 초기화
					for (int i = 0; i < model.getRowCount();) {
			            model.removeRow(0);
			        }
					
					// 찾은 책의 갯수
					ResultSet csrs = stmt.executeQuery("select count(*) from book where book_title like '%"+searching.getText()+"%';");
					while(csrs.next()) {
						cnt = csrs.getString(1);
					}
					
					// 찾은 책 출력
					ResultSet srs = stmt.executeQuery("select * from book where book_title like '%"+searching.getText()+"%';");
					System.out.println("select * from book where book_title like '%"+searching.getText()+"%';");
					
					if(cnt=="0") {
						JOptionPane.showMessageDialog(null, "보유 중인 도서가 존재하지 않습니다.", "책 없음", JOptionPane.ERROR_MESSAGE);
					}
					
					while(srs.next()) {
						String titt = srs.getString("book_title");
						String publt = srs.getString("book_publisher");
						String autht = srs.getString("book_author");
						String locat = srs.getString("book_location");
						String avat = srs.getString("book_pas");
						
						Object datat[] = {titt, publt, autht, locat, avat};
						model.addRow(datat);
					}
					
					
					System.out.println(cnt);
					resultintro.setText("찾으시는 도서는 "+cnt+"권 보유 중입니다.");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					System.out.println("도서 검색 버튼 에러");
				}
			}
		});
		c.add(searchBtn);
		
		//엔터로 검색
		searching.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String cnt = null;
				try {
					// 검색할 때마다 출력값 초기화
					for (int i = 0; i < model.getRowCount();) {
			            model.removeRow(0);
			        }
					
					// 찾은 책의 갯수
					ResultSet csrs = stmt.executeQuery("select count(*) from book where book_title like '%"+searching.getText()+"%';");
					while(csrs.next()) {
						cnt = csrs.getString(1);
					}
					
					// 찾은 책 출력
					ResultSet srs = stmt.executeQuery("select * from book where book_title like '%"+searching.getText()+"%';");
					System.out.println("select * from book where book_title like '%"+searching.getText()+"%';");
					
					if(cnt=="0") {
						JOptionPane.showMessageDialog(null, "보유 중인 도서가 존재하지 않습니다.", "책 없음", JOptionPane.ERROR_MESSAGE);
					}
					
					while(srs.next()) {
						String titt = srs.getString("book_title");
						String publt = srs.getString("book_publisher");
						String autht = srs.getString("book_author");
						String locat = srs.getString("book_location");
						String avat = srs.getString("book_pas");
						
						Object datat[] = {titt, publt, autht, locat, avat};
						model.addRow(datat);
					}
					
					
					System.out.println(cnt);
					resultintro.setText("찾으시는 도서는 "+cnt+"권 보유 중입니다.");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					System.out.println("도서 검색 엔터 버튼 에러");
				}
			}
		});
		
		table.setEnabled(false);

		// 뒤로 가기
		BackBTN back = new BackBTN(id, stmt, this);
		c.add(back);
				
		setVisible(true);
		
	}

}
