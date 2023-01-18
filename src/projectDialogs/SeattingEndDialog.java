package projectDialogs;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import project.Main;

public class SeattingEndDialog extends JDialog {
	static String id;
	Statement stmt = null;
	public SeattingEndDialog(Statement stmt, String id){
		this.stmt = stmt;
		this.id =id;
		setSize(350,350);
		this.setResizable(false);
		setTitle("좌석 사용 완료하기");
		Container c = getContentPane();
		c.setLayout(null);
		c.setBackground(new Color(224,238,238));
			
		JLabel title = new JLabel("좌석 이용 완료");
		title.setSize(350,30);
		title.setLocation(100,30);
		title.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 23));
		c.add(title);
			
		JLabel nameLabel = new JLabel("이름");
		nameLabel.setLocation(45,80);
		nameLabel.setSize(80,40);
		nameLabel.setFont(new Font("함초롱돋움", Font.BOLD, 15));
		c.add(nameLabel);
			
		JLabel idLabel = new JLabel("ID");
		idLabel.setLocation(45,160);
		idLabel.setSize(80,40);
		idLabel.setFont(new Font("함초롱돋움", Font.BOLD, 15));
		c.add(idLabel);
		
		// 이름 입력란
		JTextField nameTXT = new JTextField(10);
		nameTXT.setLocation(70,115);
		nameTXT.setSize(200,35);
		c.add(nameTXT);
					
		// ID 입력란
		JTextField idTXT = new JTextField(10);
		idTXT.setLocation(70,188);
		idTXT.setSize(200,35);
		c.add(idTXT);
		
		// 완료 확정 버튼
		JButton okBtn = new JButton("이용 완료");
		okBtn.setLocation(110,250);
		okBtn.setSize(110,30);
		okBtn.setFont(new Font("함초롱돋움", Font.BOLD, 15));
		okBtn.setForeground(Color.white);
		okBtn.setBackground(new Color(131,139,139));
		c.add(okBtn);
		
		okBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					ResultSet namecheck = stmt.executeQuery("select * from people where id = '"+id+"';");
					if(namecheck.next()) {
						if(nameTXT.getText().equals("") || idTXT.getText().equals("") ) {
							JOptionPane.showMessageDialog(null, "이용 완료 실패 \n모든 정보를 입력하세요.", "실패", JOptionPane.WARNING_MESSAGE);
						} else if(!nameTXT.getText().equals(namecheck.getString("name")) || !idTXT.getText().equals(id)) {
							JOptionPane.showMessageDialog(null, "이용 완료 실패 \n올바른 정보를 입력하세요.", "실패", JOptionPane.WARNING_MESSAGE);
						} else {
							ResultSet usecheck = stmt.executeQuery("select * from seat where id = '"+id+"';");
							System.out.println("select * from seat where id = '"+id+"';");
							if(!usecheck.next()) {
								JOptionPane.showMessageDialog(null, "사용 중인 좌석이 없습니다.", "오류", JOptionPane.WARNING_MESSAGE);
								System.out.println("aa");
							} else {
								stmt.executeUpdate("update seat set seat_lent = 'X', id = null where id = '"+id+"';");
								JOptionPane.showMessageDialog(null, "좌석을 종료합니다.", "좌석 이용 완료", JOptionPane.PLAIN_MESSAGE);
								setVisible(false);
								outroDialog out = new outroDialog(id);
							}
						}
					}


				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					System.out.println("좌석 이용 완료 버튼 에러");
				}
				
			}
		});
		
		setVisible(true);
	}

}

class outroDialog extends JDialog{
	static String id;
	outroDialog(String id){
		this.id = id;
		setSize(350,350);
		this.setResizable(false);
		setTitle("좌석 사용 완료");
		Container c = getContentPane();
		c.setLayout(null);
		c.setBackground(new Color(224,238,238));
			
		JLabel title = new JLabel("좌석 이용 완료 확인");
		title.setSize(400,30);
		title.setLocation(50,60);
		title.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 23));
		c.add(title);
		
		// 중간 설명 작성하기
		JLabel explain = new JLabel("<html>좌석 사용을 완료합니다. <br>"
				+ "이용해 주셔서 감사합니다. </html>");
		explain.setFont(new Font("본고딕 KR", Font.BOLD, 20));
		explain.setLocation(40,100);
		explain.setSize(400,120);
		c.add(explain);	
		
		// 확인 확정 버튼
		JButton okBtn = new JButton("이용 완료");
		okBtn.setLocation(90,220);
		okBtn.setSize(150,30);
		okBtn.setFont(new Font("함초롱돋움", Font.BOLD, 18));
		okBtn.setForeground(Color.white);
		okBtn.setBackground(new Color(131,139,139));
		c.add(okBtn);
		
		okBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setVisible(false);
			}
		});
		
		setVisible(true);
	}
}
