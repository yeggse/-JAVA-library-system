package projectDialogs;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import project.Intro;

public class OutDia extends JDialog{
	static String id;
	Statement stmt = null;
	
	public OutDia(Statement stmt, String id){
		this.id = id;
		this.stmt = stmt;
		setSize(400,200);
		this.setResizable(false);
		setTitle("회원 탈퇴하기");
		Container c = getContentPane();
		c.setLayout(null);
		c.setBackground(new Color(224,238,238));
		
		//타이틀 
		JLabel title = new JLabel("회원 탈퇴를 진행 하시겠습니까?");
		title.setSize(350,30);
		title.setLocation(38,30);
		title.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 20));
		c.add(title);
		
		//탈퇴 버튼
		JButton yes = new JButton("회원탈퇴");
		yes.setSize(110,35);
		yes.setLocation(78,100);
		yes.setFont(new Font("휴먼둥근헤드라인",Font.PLAIN, 15));
		yes.setBackground(new Color(255,218,185));
		yes.setForeground(new Color(105,105,105));
		c.add(yes);
		
		yes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					ResultSet srs = stmt.executeQuery("select * from seat where id = '"+id+"';");
					if(srs.next()) {
						JOptionPane.showMessageDialog(null, "좌석 반납을 먼저 해 주세요.", "좌석 미반납", JOptionPane.INFORMATION_MESSAGE);
						setVisible(false);
					}else {
						ResultSet srs2 = stmt.executeQuery("select * from book where id = '"+id+"';");
						if(srs2.next()) {
							JOptionPane.showMessageDialog(null, "도서 반납을 먼저 해 주세요.", "도서 미반납", JOptionPane.INFORMATION_MESSAGE);
							setVisible(false);
						}else {
							stmt.executeUpdate("delete from people where id = '"+id+"';");
							System.out.println("delete from people where id = '"+id+"';");
							JOptionPane.showMessageDialog(null, "회원탈퇴가 완료되었습니다. \n이용해 주셔서 감사합니다.", "회원 탈퇴 완료", JOptionPane.PLAIN_MESSAGE);
							setVisible(false);
							project.Intro intro = new Intro();
						}
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					System.out.println("회원 탈퇴 완전 확인 오류");
				}

			}
		});
		
		
		//철회 버튼
		JButton no = new JButton("아니요");
		no.setSize(110,35);
		no.setLocation(203,100);
		no.setFont(new Font("휴먼둥근헤드라인",Font.PLAIN, 15));
		no.setBackground(new Color(255,218,185));
		no.setForeground(new Color(105,105,105));
		c.add(no);
		
		no.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JOptionPane.showMessageDialog(null, "회원 탈퇴가 취소되었습니다.", "회원 탈퇴 취소", JOptionPane.PLAIN_MESSAGE);
				setVisible(false);
			}
		});
		
		setVisible(true);
	}
}
