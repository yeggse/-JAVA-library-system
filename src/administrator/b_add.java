package administrator;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class b_add extends JDialog{
	b_add(JFrame f){
		project.Intro s = (project.Intro)f; 
		setSize(300,400);
		this.setResizable(false);
		setTitle("(관리자) 도서 추가 시스템");
		Container c = getContentPane();
		c.setLayout(null);
		c.setBackground(new Color(224,224,224));
		
		JLabel title = new JLabel("도서 추가 시스템");
		title.setSize(200,30);
		title.setLocation(65,20);
		title.setFont(new Font("휴먼둥근헤드라인", Font.BOLD, 23));
		c.add(title);
		
		JPanel panel = new JPanel();
		panel.setSize(250,300);
	}
}
