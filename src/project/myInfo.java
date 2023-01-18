package project;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.*;
import javax.swing.*;

import project.Recommand.myPanel;

public class myInfo extends JFrame{
	static String id;
	Connection conn;
	Statement stmt = null;
	Statement stmt1 = null;
	Statement stmt2 = null;
	Statement stmt3 = null;
	myInfo(String id){
		this.id = id;
		setSize(500,600);
		this.setResizable(false);
		setTitle("(두면 도서관)나의 회원 정보 보기");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(null);
		c.setBackground(Color.white);
		
		// 타이틀 만들기
		ImageIcon logoIcon = new ImageIcon("image/me.png");
		Image img = logoIcon.getImage();	// 아이콘 크기 수정을 위해 필요한 과정
		Image imgfin = img.getScaledInstance(60, 60, java.awt.Image.SCALE_SMOOTH);
		ImageIcon logoIconfin = new ImageIcon(imgfin);
		JLabel title = new JLabel("내 정보", logoIconfin, SwingConstants.CENTER);
		title.setLocation(50,20);
		title.setSize(380,80);
		title.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 30));
		c.add(title);
		
		// 레이블
		JLabel idla = new JLabel("ID");
		JLabel pwla = new JLabel("PW");
		JLabel namela = new JLabel("이름");
		JLabel birthla = new JLabel("생년월일");
		JLabel genderla = new JLabel("성별");
		JLabel addressla = new JLabel("주소");
		JLabel bookla = new JLabel("대여 중인 도서");
		JLabel rebookla = new JLabel("도서 반납 일자");
		
		idla.setSize(100,30);
		idla.setLocation(80,105);
		idla.setFont(new Font("함초롱돋움",Font.BOLD, 20));
		c.add(idla);
		
		pwla.setSize(100,30);
		pwla.setLocation(80,145);
		pwla.setFont(new Font("함초롱돋움",Font.BOLD, 20));
		c.add(pwla);
		
		namela.setSize(100,30);
		namela.setLocation(80,185);
		namela.setFont(new Font("함초롱돋움",Font.BOLD, 20));
		c.add(namela);
		
		birthla.setSize(100,30);
		birthla.setLocation(80,225);
		birthla.setFont(new Font("함초롱돋움",Font.BOLD, 20));
		c.add(birthla);
		
		genderla.setSize(100,30);
		genderla.setLocation(80,265);
		genderla.setFont(new Font("함초롱돋움",Font.BOLD, 20));
		c.add(genderla);
		
		addressla.setSize(100,30);
		addressla.setLocation(80,305);
		addressla.setFont(new Font("함초롱돋움",Font.BOLD, 20));
		c.add(addressla);
		
		bookla.setSize(150,30);
		bookla.setLocation(80,368);
		bookla.setFont(new Font("함초롱돋움",Font.BOLD, 17));
		c.add(bookla);
		
		rebookla.setSize(150,30);
		rebookla.setLocation(80,408);
		rebookla.setFont(new Font("함초롱돋움",Font.BOLD, 17));
		c.add(rebookla);
		
		//패널
		JPanel idp = new JPanel();
		JPanel pwp = new JPanel();
		JPanel namep = new JPanel();
		JPanel birthp = new JPanel();
		JPanel genderp = new JPanel();
		JPanel addressp = new JPanel();
		JPanel bookp = new JPanel();
		JPanel rebookp = new JPanel();
		
		// SQL 연결
		try {
			Class.forName("com.mysql.jdbc.Driver"); // MySQL 드라이버 로드
			conn = DriverManager.getConnection
					("jdbc:mysql://localhost:3306/psersonalprojet", "root","test123"); // JDBC 연결
			System.out.println("my info 파트 : DB 연결 완료");
			stmt = conn.createStatement();	// SQL문 처리용 Statement 객체 생성
			stmt1 = conn.createStatement();	// SQL문 처리용 Statement 객체 생성
			stmt2 = conn.createStatement();	// SQL문 처리용 Statement 객체 생성
			stmt3 = conn.createStatement();	// SQL문 처리용 Statement 객체 생성
		} catch (ClassNotFoundException e) {
			System.out.println("JDBC 드라이버 로드 에러");
		} catch (SQLException e) {
			System.out.println("DB 연결 오류");
		}

		// id 정보 출력
		idp.setLocation(200,105);
		idp.setSize(250,30);
		idp.setLayout(new FlowLayout());
		idp.setBackground(new Color(245,245,245));
		c.add(idp);
		
		JLabel idget = new JLabel();
		idget.setSize(200,25);
		idget.setText(id);
		idp.add(idget);
		
		
		// pw 정보 출력
		pwp.setLocation(200,145);
		pwp.setSize(250,30);
		pwp.setLayout(new FlowLayout());
		pwp.setBackground(new Color(245,245,245));
		c.add(pwp);
		
		JLabel pwget = new JLabel();
		pwget.setSize(200,25);
		try {
			ResultSet pwsrs = stmt.executeQuery("select pw from people where id = '"+id+"';");
			while (pwsrs.next()) {
				pwget.setText(pwsrs.getString("pw"));
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			System.out.println("my info : pw 파트 오류");
		}
		pwp.add(pwget);
		
		
		// 이름 정보 출력
		namep.setLocation(200,185);
		namep.setSize(250,30);
		namep.setLayout(new FlowLayout());
		namep.setBackground(new Color(245,245,245));
		c.add(namep);
		
		JLabel nameget = new JLabel();
		nameget.setSize(200,25);
		try {
			ResultSet namesrs = stmt.executeQuery("select name from people where id = '"+id+"';");
			while (namesrs.next()) {
				nameget.setText(namesrs.getString("name"));
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			System.out.println("my info : name 파트 오류");
		}
		namep.add(nameget);
		
		
		// 생일 정보 출력
		birthp.setLocation(200,225);
		birthp.setSize(250,30);
		birthp.setLayout(new FlowLayout());
		birthp.setBackground(new Color(245,245,245));
		c.add(birthp);
		
		JLabel birthget = new JLabel();
		birthget.setSize(200,25);
		try {
			ResultSet birthsrs = stmt.executeQuery("select birth from people where id = '"+id+"';");
			while (birthsrs.next()) {
				birthget.setText(birthsrs.getString("birth"));
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			System.out.println("my info : birth 파트 오류");
		}
		birthp.add(birthget);
		
		
		// 주소 정보 출력
		addressp.setLocation(200,305);
		addressp.setSize(250,57);
		addressp.setLayout(new FlowLayout());
		addressp.setBackground(new Color(245,245,245));
		c.add(addressp);
		
		JLabel addressget = new JLabel();
		addressget.setSize(200,25);
		try {
			ResultSet addresssrs = stmt.executeQuery("select address from people where id = '"+id+"';");
			while (addresssrs.next()) {
				addressget.setText(addresssrs.getString("address"));
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			System.out.println("my info : address 파트 오류");
		}
		addressp.add(addressget);
		
		
		// 도서 대여 정보 
		bookp.setLocation(200,370);
		bookp.setSize(250,30);
		bookp.setLayout(new FlowLayout());
		bookp.setBackground(new Color(245,245,245));
		c.add(bookp);
		
		JLabel bookin = new JLabel();
		bookin.setSize(200,25);
		bookp.add(bookin);
		String bookinfo = null;
		
		// 도서 반납일 정보 
		rebookp.setLocation(200,410);
		rebookp.setSize(250,30);
		rebookp.setLayout(new FlowLayout());
		rebookp.setBackground(new Color(245,245,245));
		c.add(rebookp);
		
		JLabel rein = new JLabel();
		rein.setSize(200,25);
		bookp.add(rein);
		String bookdate = null;
		
		try {
			ResultSet srs = stmt1.executeQuery("select * from book where id = '"+id+"';");
			System.out.println("select * from book where id = '"+id+"';");
			if(!srs.next()) {
				bookinfo = "대여 중인 도서 없음";
				bookdate = "-";
			} else {
				bookinfo = srs.getString("book_title");
				bookdate = srs.getString("backdate");
			}
			rein.setText(bookdate);
			bookin.setText(bookinfo);
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
			System.out.println("도서 정보 출력 오류");
		}
		
		//성별
		String gen = "";
		
		genderp.setLocation(200,265);
		genderp.setSize(250,30);
		genderp.setBackground(new Color(245,245,245));
		c.add(genderp);
		
		try {
			ResultSet gendersrs = stmt.executeQuery("select gender from people where id = '"+id+"';");
			while (gendersrs.next()) {
				if("여".equals(gendersrs.getString("gender"))) {
					gen ="image/girl.png";
				} else {
					gen ="image/man.png";
				}
				ImageIcon girlIcon = new ImageIcon(gen);
				Image girlimg = girlIcon.getImage();	// 아이콘 크기 수정을 위해 필요한 과정
				Image girlimgfin = girlimg.getScaledInstance(200, 30, java.awt.Image.SCALE_SMOOTH);
				ImageIcon girlIconfin = new ImageIcon(girlimgfin);
				
				JLabel girl = new JLabel(girlIconfin);
				girl.setSize(200,30);
				genderp.add(girl);	
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			System.out.println("my info : gender 파트 오류");
		}
		
		
		// 유료회원 가입 여부
		String ro = "";
		
		try {
			ResultSet royalsrs = stmt.executeQuery("select royal from people where id = '"+id+"';");
			while (royalsrs.next()) {
				if("O".equals(royalsrs.getString("royal"))) {
					ro ="image/royalok.PNG";
				} else {
					ro ="image/noroyal.PNG";
				}
				ImageIcon royalIcon = new ImageIcon(ro);
				Image royalimg = royalIcon.getImage();	// 아이콘 크기 수정을 위해 필요한 과정
				Image royalimgfin = royalimg.getScaledInstance(250, 30, java.awt.Image.SCALE_SMOOTH);
				ImageIcon royalIconfin = new ImageIcon(royalimgfin);
				
				JLabel royal = new JLabel(royalIconfin);
				royal.setSize(300,30);
				royal.setLocation(80,460);
				c.add(royal);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			System.out.println("my info : royal 파트 오류");
		}
				
		
		// 수정 버튼
		JButton edit = new JButton("수정");
		edit.setSize(80,30);
		edit.setLocation(220,510);
		edit.setFont(new Font("휴먼엑스포",Font.PLAIN, 18));
		edit.setBackground(new Color(255,218,185));
		edit.setForeground(new Color(105,105,105));
		c.add(edit);
		
		edit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Edit edit = new Edit(id);
				setVisible(false);
			}
		});
		
		// 뒤로 가기
		BackBTN back = new BackBTN(this);
		c.add(back);
				
		setVisible(true);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new myInfo(id);
	}
}
