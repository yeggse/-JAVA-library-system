package project;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;

public class Seatting extends JFrame {
	Connection conn;
	Statement stmt = null;
	static String id;
	Seatting(String id){
		this.id = id;
		setSize(600,500);
		this.setResizable(false);
		setTitle("(두면 도서관)열람실 좌석 예약하기");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(null);
		c.setBackground(Color.white);
		
		// 타이틀 만들기
		ImageIcon logoIcon = new ImageIcon("image/study.png");
		Image img = logoIcon.getImage();	// 아이콘 크기 수정을 위해 필요한 과정
		Image imgfin = img.getScaledInstance(60, 60, java.awt.Image.SCALE_SMOOTH);
		ImageIcon logoIconfin = new ImageIcon(imgfin);
		JLabel title = new JLabel("열람실 좌석 예약", logoIconfin, SwingConstants.CENTER);
		title.setLocation(100,20);
		title.setSize(380,80);
		title.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 30));
		c.add(title);
				
		// 중간 설명 작성하기
		JLabel explain = new JLabel("<html>원하시는 좌석을 예약하실 수 있습니다.<br>"
				+ "현재 다른 회원이 이용중인 좌석은 예약이 불가능합니다. </html>");
		explain.setFont(new Font("본고딕 KR", Font.BOLD, 13));
		explain.setLocation(140,50);
		explain.setSize(400,120);
		c.add(explain);	
		
		// 좌석 안내 패널
		JPanel panel = new JPanel();
		panel.setBackground(new Color(245,245,220));
		panel.setLocation(15,145);
		panel.setSize(555,270);
		panel.setLayout(null);
		c.add(panel);
		
		myPanel panel2 = new myPanel(10,10);
		panel.add(panel2);
		
		myPanel panel3 = new myPanel(10,100);
		panel.add(panel3);
		
		myPanel panel4 = new myPanel(10,190);
		panel.add(panel4);
		
		// 부속물 패널
		subPanel ent = new subPanel(450,5,90,30,"출입구");
		panel.add(ent);
		
		subPanel bar = new subPanel(420,50,120,140,"책장");
		panel.add(bar);
		
		subPanel bar2 = new subPanel(420,200,120,60,"책장");
		panel.add(bar2);
		
		// SQL 연결
		try {
			Class.forName("com.mysql.jdbc.Driver"); // MySQL 드라이버 로드
			conn = DriverManager.getConnection
					("jdbc:mysql://localhost:3306/psersonalprojet", "root","test123"); // JDBC 연결
			System.out.println("좌석 : DB 연결 완료");
			stmt = conn.createStatement();	// SQL문 처리용 Statement 객체 생성
		} catch (ClassNotFoundException e) {
			System.out.println("JDBC 드라이버 로드 에러");
		} catch (SQLException e) {
			System.out.println("DB 연결 오류");
		}
		
		// 좌석 버튼!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!이벤트 처리가 완전하지 않은!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		JButton sBtn = null;
		for (int i=0; i<8;i++) {
			sBtn = new JButton((i+1)+"");
			sBtn.setSize(90,40);
			sBtn.setBackground(new Color(147,112,219));
			sBtn.setForeground(Color.white);
			sBtn.setFont(new Font("휴먼엑스포", Font.BOLD, 15));
			String a = sBtn.getText();
			sBtn.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					try {
						ResultSet srs = stmt.executeQuery("select * from people p inner join seat s on s.id=p.id where p.id='"+id+"';");
						System.out.println("select * from people p inner join seat s on s.id=p.id where p.id='"+id+"';");
						while(srs.next()) {
							System.out.println("dd");
							if("O".equals(srs.getString("s.seat_lent"))) {
								JOptionPane.showMessageDialog(null, "이미 사용중인 좌석입니다. \n 다른 좌석을 선택하세요.", "실패", JOptionPane.WARNING_MESSAGE);
								System.out.println("aa");
							} else {
								stmt.executeUpdate("update seat	set seat_lent = 'O' where seat_no = '"+a+"' and id = '"+id+");");
								System.out.println("ww");
							}
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						System.out.println("좌석 이용 버튼 에러");
					}
				}
			});
			panel2.add(sBtn);
		}
		
		for (int i=9; i<17;i++) {
			sBtn = new JButton((i+1)+"");
			sBtn.setSize(90,40);
			sBtn.setBackground(new Color(147,112,219));
			sBtn.setForeground(Color.white);
			sBtn.setFont(new Font("휴먼엑스포", Font.BOLD, 15));
			panel3.add(sBtn);
		}
		
		for (int i=17; i<24;i++) {
			sBtn = new JButton();
			sBtn.setText((i+1)+"");
			sBtn.setSize(90,40);
			sBtn.setBackground(new Color(147,112,219));
			sBtn.setForeground(Color.white);
			sBtn.setFont(new Font("휴먼엑스포", Font.BOLD, 15));
			panel4.add(sBtn);
		}
		

				
//		// 좌석 버튼 이벤트
//		//for 문으로 이벤트 넣기 + DB에 데이터 저장!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//		sBtn.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//				try {
//					ResultSet srs = stmt.executeQuery("select seat_lent from people where id = '"+id+"';");
//					while(srs.next()) {
//						if("X".equals(srs.getString("seat_lent"))) {
//							JOptionPane.showMessageDialog(null, "이미 사용중인 좌석입니다. \n 다른 좌석을 선택하세요.", "실패", JOptionPane.WARNING_MESSAGE);
//						} else {
//							stmt.executeUpdate("update seat	set seat_lent = 'O' where seat_no = '"+a+"' and id = '"+id+");");
//						}
//					}
//				} catch (SQLException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//					System.out.println("좌석 이용 버튼 에러");
//				}
//			}
//		});

		
		// 좌석 반환 버튼
		// 좌석 버튼 이벤트 + DB 저장!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		JButton endBtn = new JButton("이용종료");
		endBtn.setSize(85,25);
		endBtn.setLocation(490,425);
		endBtn.setBackground(new Color(255,215,0));
		endBtn.setForeground(Color.darkGray);
		endBtn.setFont(new Font("휴먼모음T", Font.BOLD, 15));
		c.add(endBtn);
		
		
		
		// 뒤로 가기
		BackBTN back = new BackBTN(this);
		c.add(back);
				
		setVisible(true);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Seatting(id);
	}

}

class myPanel extends JPanel{
	myPanel(int x, int y){
		setBackground(new Color(245,245,220));
		setSize(390,80);
		setLocation(x,y);
		setLayout(new FlowLayout(FlowLayout.LEFT));
	}
}

class subPanel extends JPanel{
	subPanel(int x, int y, int w, int h, String s){
		setBackground(new Color(139,137,112));
		setSize(w,h);
		setLocation(x,y);
		setLayout(new FlowLayout());
		
		JLabel label = new JLabel(s);
		label.setForeground(Color.white);
		label.setFont(new Font("함초롱돋움", Font.BOLD, 18));
		this.add(label);
	}
}