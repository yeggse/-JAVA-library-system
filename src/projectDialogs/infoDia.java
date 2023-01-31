package projectDialogs;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class infoDia extends JDialog{
	static String id;
	static Statement stmt = null;
	static JFrame jframe;
	String text;
	String txt;
	String infono;
	
	public infoDia( Statement stmt, String id, String infono) {
		this.id = id;
		this.stmt = stmt;
		this.infono = infono;
		setSize(400,450);
		this.setResizable(false);
		setTitle("공지 상세");
		Container c = getContentPane();
		c.setLayout(null);
		c.setBackground(new Color(245,239,230));
		
		// 출력 내용 확정
		try {
			ResultSet srs = stmt.executeQuery("select * from info where no = '"+infono+"';");
			if(srs.next()) {
				text = srs.getString("title");
				txt = srs.getString("main");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("info 내용 출력 오류");
		}
		
		//타이틀 
		JLabel title = new JLabel("공지사항 상세 확인");
		title.setSize(350,30);
		title.setLocation(100,20);
		title.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 23));
		c.add(title);
		
		//공지 제목 라벨
		JLabel sub = new JLabel("제목 :");
		sub.setLocation(25,57);
		sub.setSize(300,40);
		sub.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 17));
		c.add(sub);
		
		//공지 제목 출력
		JLabel gett = new JLabel();
		gett.setText(text);
		gett.setLocation(100,57);
		gett.setSize(300,40);
		gett.setFont(new Font("굴림", Font.BOLD, 16));
		c.add(gett);
				
		//공지 내용 라벨
		JLabel main = new JLabel("내용");
		main.setLocation(25,95);
		main.setSize(300,40);
		main.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 17));
		c.add(main);
		
		// 출력 패널
		JPanel panel = new JPanel();
		panel.setSize(360,270);
		panel.setLocation(10,128);
		panel.setLayout(null);
		panel.setBackground(Color.black);
		c.add(panel);
		
		JTextArea area = new JTextArea();
		JScrollPane js = new JScrollPane(area);
		js.setLocation(5,5);
		js.setSize(350,260);
		panel.add(js);
		area.setText(txt);
		area.setEditable(false);
		
		
		setVisible(true);
	}
	
}
