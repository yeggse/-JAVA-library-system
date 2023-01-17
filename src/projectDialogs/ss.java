package projectDialogs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import project.BookSearch;

public class ss extends JFrame {
	// JTable
	Object ob[][] = new Object[0][5]; //데이터 표시에 열만 나오게 설정
	DefaultTableModel model;  // 데이터 저장 부분
	JTable table;
	JScrollPane js;
	String str[] = {"도 서 명", "출 판 사", "작 가", "위 치","대여여부"}; // 컬럼 명
	
	//DB연동
	Connection con = null;
	PreparedStatement pstmt = null;	//sql구문 실행
	ResultSet rs = null; // select구문 사용시 필수
	
	Connection conn;
	Statement stmt = null;
	Statement stmt1 = null;
	Statement stmt2 = null;
	Statement stmt3 = null;
	static String id;
	int count;
	
	ss(){
		super("타이틀 입력");
		
		//Jtable 가운데 배치
		model = new DefaultTableModel(ob,str);	// 데이터 저장[][], 컬럼 명
		table = new JTable(model);
		js = new JScrollPane(table);
		add(js);
		setBounds(250,250,300,300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		// DB연결
		// sql 연결
		try {
			Class.forName("com.mysql.jdbc.Driver"); // MySQL 드라이버 로드
			conn = DriverManager.getConnection
					("jdbc:mysql://localhost:3306/psersonalprojet", "root","test123"); // JDBC 연결
			System.out.println("좌석 : DB 연결 완료");
			stmt = conn.createStatement();	// SQL문 처리용 Statement 객체 생성
			stmt1 = conn.createStatement();	// SQL문 처리용 Statement 객체 생성
			stmt2 = conn.createStatement();	// SQL문 처리용 Statement 객체 생성
			stmt3 = conn.createStatement();	// SQL문 처리용 Statement 객체 생성
		} catch (ClassNotFoundException e) {
			System.out.println("JDBC 드라이버 로드 에러");
		} catch (SQLException e) {
			System.out.println("DB 연결 오류");
		}
		
		// DB출력
		String sql = "select * from book";
		try {
			pstmt=con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String tit = rs.getString("title");
				String publ = rs.getString("title");
				String auth = rs.getString("title");
				String loca = rs.getString("title");
				
				Object data[] = {tit, publ, auth, loca};
				model.addRow(data);
			}
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		
	}
		
		
	
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new ss();
	}
	
	

	
}