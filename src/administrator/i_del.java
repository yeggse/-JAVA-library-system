package administrator;
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

public class i_del extends JDialog{
	static Statement stmt;
	static String id;
	static JFrame jframe;
	i_del(Statement stmt, String id, JFrame jframe){
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
		JLabel num = new JLabel("삭제하고자 하는 공지사항의 번호를 입력하세요.");
		num.setSize(300,35);
		num.setLocation(30,70);
		num.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 17));
		c.add(num);
		
		JTextField ntxt = new JTextField(10);
		ntxt.setSize(155,35);
		ntxt.setLocation(100,95);
		c.add(ntxt);
		
		// 확인 버튼
		JButton yes = new JButton("확인");
		yes.setSize(80,38);
		yes.setLocation(15,220);
		c.add(yes);
		
//		yes.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//				
//				try {
//					//입력하기
//					if(ttxt.getText().equals("") || mtxt.getText().equals("") || wtxt.getText().equals("") || ntxt.getText().equals("")) {
//						JOptionPane.showMessageDialog(null, "모든 정보를 기재해 주세요", "정보 누락", JOptionPane.WARNING_MESSAGE);
//					} else {
//						stmt.executeUpdate("update info set title = '"+ttxt.getText()+"', main = '"+mtxt.getText()+"', writer = '"+wtxt.getText()+"' where no = '"+ntxt.getText()+"';");
//						System.out.println("update info set title = '"+ttxt.getText()+"', main = '"+mtxt.getText()+"', writer = '"+wtxt.getText()+"' where no = '"+ntxt.getText()+"';");
//						JOptionPane.showMessageDialog(null, "공지사항 수정이 완료되었습니다.", "공지 수정", JOptionPane.PLAIN_MESSAGE);
//						setVisible(false);
//						jframe.setVisible(false);
//						Admin_info adinfo = new Admin_info(stmt, id);
//					}
//				} catch (SQLException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//					System.out.println("공지 수정 에러");
//				}
//			}
//		});
		
		
		// 취소 버튼
		JButton no = new JButton("취소");
		no.setSize(80,38);
		no.setLocation(95,220);
		c.add(no);
		no.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setVisible(false);
			}
		});
		
		setVisible(true);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new i_del(stmt, id, jframe);
	}

}
