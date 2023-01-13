package project;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.*;
import javax.swing.*;

public class Memo extends JFrame{
	Memo(){
		setSize(600,500);
		this.setResizable(false);
		setTitle("(두면 도서관)메모장");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(null);
		c.setBackground(new Color(255,250,250));
		
		// 타이틀 만들기
		ImageIcon logoIcon = new ImageIcon("image/notice.png");
		Image img = logoIcon.getImage();	// 아이콘 크기 수정을 위해 필요한 과정
		Image imgfin = img.getScaledInstance(60, 60, java.awt.Image.SCALE_SMOOTH);
		ImageIcon logoIconfin = new ImageIcon(imgfin);
		JLabel title = new JLabel("메모 시스템", logoIconfin, SwingConstants.CENTER);
		title.setLocation(100,20);
		title.setSize(380,80);
		title.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 30));
		c.add(title);
				
		// 중간 설명 작성하기
		JLabel explain = new JLabel("<html>* 원하시는 내용이 있다면, 아래에 기재할 수 있습니다.<br>"
				+ "* 메모 시스템은 회원님들의 일시적인 활용을 위해서 생성된 기능으로, 기재하신 내용은 저장되지 않으니 유의하시기 바랍니다.</html>");
		explain.setFont(new Font("본고딕 KR", Font.BOLD, 13));
		explain.setLocation(90,65);
		explain.setSize(430,120);
		c.add(explain);	
		
		// 메모 area
		JPanel panel = new JPanel();
		panel.setSize(520,288);
		panel.setOpaque(true);
		panel.setLocation(30,170);
		panel.setBackground(new Color(221,160,221));
		panel.setLayout(null);
		c.add(panel);
		
		JTextArea area = new JTextArea();
		area.setLocation(3,3);
		area.setSize(514,282);
		panel.add(area);
		
		
		// 뒤로 가기
		BackBTN back = new BackBTN(this);
		c.add(back);
		
		setVisible(true);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Memo();
	}
}
