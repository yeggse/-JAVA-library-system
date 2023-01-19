package project;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.*;
import javax.swing.*;

public class Memo extends JFrame{
	Statement stmt = null;
	String id;
	Memo(Statement stmt, String id){
		this.stmt = stmt;
		this.id = id;
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
				+ "* 메모 시스템은 회원님들의 편의를 위해서 활용된 기능으로 본인만 확인할 수 있습니다.</html>");
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
		String get = "";
		try {
			ResultSet srs = stmt.executeQuery("select * from people where id = '"+id+"';");
			if(srs.next()) {
				get = srs.getString("memo");
			}
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
			System.out.println("메모 불러오기 오류");
		}
		
		area.setText(get);
		
		// 저장 버튼
		JButton save = new JButton("저장");
		save.setSize(90,35);
		save.setLocation(490,5);
		c.add(save);
		
		save.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					stmt.executeUpdate("update people set memo = '"+area.getText()+"' where id = '"+id+"';");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					System.out.println("메모 저장 이벤트 실패");
				}
			}
		});
	
		// 삭제 버튼
		JButton dle = new JButton("전체 삭제");
		dle.setSize(90,35);
		dle.setLocation(490,55);
		c.add(dle);
		
		dle.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					stmt.executeUpdate("update people set memo = null where id = '"+id+"';");
					area.setText(null);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					System.out.println("메모 삭제test 이벤트 실패");
				}
			}
		});
		
		// 뒤로 가기
		BackBTN back = new BackBTN(id, stmt, this);
		c.add(back);
		
		setVisible(true);
	}
}
