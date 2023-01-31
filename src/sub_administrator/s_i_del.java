package sub_administrator;

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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import administrator.Admin_info;

public class s_i_del extends JDialog{
	static Statement stmt;
	static String id;
	JFrame jframe;
	s_i_del(Statement stmt, String id, JFrame jframe){
		this.stmt = stmt;
		this.id = id;
		this.jframe = jframe;
		setSize(300,300);
		this.setResizable(false);
		setTitle("(관리자) 공지 삭제 시스템");
		Container c = getContentPane();
		c.setLayout(null);
		c.setBackground(new Color(224,224,224));
		
		JLabel title = new JLabel("공지사항 삭제");
		title.setSize(200,30);
		title.setLocation(70,20);
		title.setFont(new Font("휴먼둥근헤드라인", Font.BOLD, 23));
		c.add(title);
		
		// 삭제 대상 공지사항 번호
		JLabel num = new JLabel("<html><h4 style='text-align : center;'>삭제하고자 하는 <br>공지사항의 번호를 입력하세요.</h4></html>");
		num.setSize(300,100);
		num.setLocation(65,50);
		num.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		c.add(num);
		
		JTextField ntxt = new JTextField(10);
		ntxt.setSize(155,35);
		ntxt.setLocation(70,125);
		c.add(ntxt);
		
		// 확인 버튼
		JButton yes = new JButton("확인");
		yes.setSize(80,38);
		yes.setLocation(45,180);
		c.add(yes);
		
		yes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				try {
					//삭제하기
					if(ntxt.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "삭제를 원하는 공지사항의 번호를 기재해 주세요", "기재 누락", JOptionPane.WARNING_MESSAGE);
					} else {
						ResultSet srs = stmt.executeQuery("select * from info where no = '"+ntxt.getText()+"';");
						if(srs.next()) {
							stmt.executeUpdate("delete from info where no = '"+ntxt.getText()+"';");
							System.out.println("delete from info where no = '"+ntxt.getText()+"';");
							JOptionPane.showMessageDialog(null, "공지사항 삭제가 완료되었습니다.", "공지 삭제", JOptionPane.PLAIN_MESSAGE);
							setVisible(false);
							jframe.dispose();
							sub_Admin_Main admain = new sub_Admin_Main(stmt, id);
						} else {
							JOptionPane.showMessageDialog(null, "존재하지 않는 번호입니다. \n 번호를 확인해 주세요.", "번호 입력 오류", JOptionPane.ERROR_MESSAGE);
						}
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					System.out.println("공지 삭제 에러");
				}
			}
		});
		
		
		// 취소 버튼
		JButton no = new JButton("취소");
		no.setSize(80,38);
		no.setLocation(155,180);
		c.add(no);
		no.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setVisible(false);
			}
		});
		
	}
	
}
