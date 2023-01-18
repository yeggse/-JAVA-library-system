package project;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class z_Drawing {}

// Main, intro 에서 사용
// 가운데 정렬 하느방법..? (joinguide.setHorizontalAlignment(SwingConstants.CENTER); 아닌가)
class outtroLabel extends JLabel{
	outtroLabel(int x, int y){
		setText("<html>인천광역시 계양구 부평구"
				+ " <br>tel. 82+032-123-4567        fax. 32-123-4568 </html>");
		setFont(new Font("본고딕 KR", Font.BOLD, 11));
		setOpaque(true);
		setForeground(new Color(41,90,221));
		setSize(200,30);
		setLocation(x, y);
	}
}


// 전체에서 사용됨(뒤로가기 버튼)
class BackBTN extends JButton{
	String name;
	BackBTN(JFrame jframe){
		setText("뒤로가기");
		setSize(80,25);
		setLocation(15,10);
		setBackground(new Color(28,188,156));
		setForeground(Color.white);
		setFont(new Font("휴먼모음T", Font.BOLD, 13));
				
		addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Main main = new Main(name);
				jframe.setVisible(false);
			}
		});
	}
}

class logout extends JButton{
	logout(JFrame jframe){
		setText("로그아웃");
		setSize(80,25);
		setLocation(480,10);
		setBackground(new Color(210,218,255));
		setForeground(new Color(135,88,255));
		setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 11));
				
		addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Intro intro = new Intro();
				jframe.setVisible(false);
			}
		});
	}
}

class SqlConnect {
	Connection conn;
	Statement stmt = null;
	String query=null;
	SqlConnect(){
		// SQL 연결
		try {
			Class.forName("com.mysql.jdbc.Driver"); // MySQL 드라이버 로드
			conn = DriverManager.getConnection
					("jdbc:mysql://localhost:3306/psersonalprojet", "root","test123"); // JDBC 연결
			System.out.println("DB 연결 완료");
			stmt = conn.createStatement();	// SQL문 처리용 Statement 객체 생성
		} catch (ClassNotFoundException e) {
			System.out.println("JDBC 드라이버 로드 에러");
		} catch (SQLException e) {
			System.out.println("DB 연결 오류");
		}
	}
	
	String add(String part, String idget) {
		return "select "+part+" from people where id = '"+idget+"');";
	}
}