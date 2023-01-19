package administrator;

import java.awt.Container;
import java.awt.Font;
import java.awt.Image;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Admin_member extends JFrame {
	static String id;
	static Statement stmt = null;
	Admin_member(Statement stmt, String id){
		this.stmt = stmt;
		this.id = id;
		setSize(600,500);
		this.setResizable(false);
		setTitle("(두면 도서관)관리자 페이지 : 회원");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(null);
		
		// 타이틀 만들기
		ImageIcon logoIcon = new ImageIcon("image/mm.png");
		Image img = logoIcon.getImage();	// 아이콘 크기 수정을 위해 필요한 과정
		Image imgfin = img.getScaledInstance(60, 60, java.awt.Image.SCALE_SMOOTH);
		ImageIcon liIconfin = new ImageIcon(imgfin);
		JLabel title = new JLabel("회원 관리 페이지", liIconfin, SwingConstants.CENTER);
		title.setLocation(65,20);
		title.setSize(450,80);
		title.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 30));
//		title.setForeground(new Color(255,255,240));
		c.add(title);
		
		
		// 뒤로가기
		BackBTN back = new BackBTN(id, stmt, this);
		c.add(back);
		
		setVisible(true);
	}

}
