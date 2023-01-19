package administrator;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Image;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Admin_Book extends JFrame{
	static String id;
	static Statement stmt = null;
	Admin_Book(Statement stmt, String id){
		this.stmt = stmt;
		this.id = id;
		setSize(600,500);
		this.setResizable(false);
		setTitle("(두면 도서관)관리자 페이지 : 도서");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(null);
		
		// 타이틀 만들기
		ImageIcon logoIcon = new ImageIcon("image/bb.png");
		Image img = logoIcon.getImage();	// 아이콘 크기 수정을 위해 필요한 과정
		Image imgfin = img.getScaledInstance(60, 60, java.awt.Image.SCALE_SMOOTH);
		ImageIcon liIconfin = new ImageIcon(imgfin);
		JLabel title = new JLabel("도서 관리 페이지", liIconfin, SwingConstants.CENTER);
		title.setLocation(65,20);
		title.setSize(450,80);
		title.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 30));
//		title.setForeground(new Color(255,255,240));
		c.add(title);
		
		
		// 표 출력하는 공간 확보
		JPanel area = new JPanel();
		area.setOpaque(true);
		area.setSize(488,250);
		area.setLocation(45,113);
		area.setBackground(new Color(37,49,109));
		c.add(area);
		
		// 버튼
		JButton add = new JButton("추가");
		add.setSize(100,35);
		add.setLocation(95,380);
		c.add(add);
		
		JButton edi = new JButton("수정");
		edi.setSize(100,35);
		edi.setLocation(250,380);
		c.add(edi);
		
		JButton del = new JButton("삭제");
		del.setSize(100,35);
		del.setLocation(400,380);
		c.add(del);
		
		
		setVisible(true);	
	}
}
