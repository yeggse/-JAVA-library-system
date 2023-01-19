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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class b_edi extends JDialog{
	Statement stmt;
	String id;
	
	b_edi(Statement stmt, String id){
		this.stmt = stmt;
		this.id = id;
		setSize(300,300);
		this.setResizable(false);
		setTitle("(관리자) 도서 수정 시스템");
		Container c = getContentPane();
		c.setLayout(null);
		c.setBackground(new Color(224,224,224));
		
		JLabel title = new JLabel("도서 수정 시스템");
		title.setSize(200,30);
		title.setLocation(50,20);
		title.setFont(new Font("휴먼둥근헤드라인", Font.BOLD, 23));
		c.add(title);
		
		// 수정하고 싶은 도서 명
		JLabel book = new JLabel("수정 대상 책");
		book.setSize(150,35);
		book.setLocation(30,70);
		c.add(book);
		
		JTextField btxt = new JTextField(10);
		btxt.setSize(155,35);
		btxt.setLocation(100,95);
		c.add(btxt);
		
		// 위치 - 수정
		JLabel loca = new JLabel("변경될 위치");
		loca.setSize(90,30);
		loca.setLocation(30,140);
		c.add(loca);
		
		JTextField ltxt = new JTextField(10);
		ltxt.setSize(155,35);
		ltxt.setLocation(100,160);
		c.add(ltxt);
		
		//확인 버튼
		JButton yes = new JButton("확인");
		yes.setSize(70,35);
		yes.setLocation(165,215);
		c.add(yes);
		yes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					if(ltxt.getText().equals("")||btxt.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "모든 값을 입력하세요.", "입력값 누락", JOptionPane.ERROR_MESSAGE);
					}else {
						ResultSet srs = stmt.executeQuery("select * from book where book_title='"+btxt.getText()+"';");
						System.out.println("select * from book where book_title='"+btxt.getText()+"';");
						if(!srs.next()) {
							JOptionPane.showMessageDialog(null, "책 이름을 확인하세요.", "입력값 오류", JOptionPane.ERROR_MESSAGE);
						} else {
							stmt.executeUpdate("update book set book_location='"+ltxt.getText()+"' where book_title='"+btxt.getText()+"';");
							System.out.println("update book set book_location='"+ltxt.getText()+"' where book_title='"+btxt.getText()+"';");
							JOptionPane.showMessageDialog(null, "책 정보 수정이 완료되었습니다.", "수정 완료", JOptionPane.INFORMATION_MESSAGE);
							setVisible(false);
						}
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					System.out.println("도서 수정 확인 버튼 오류");
				}
			}
		});
		
		// 취소 버튼
		JButton no = new JButton("취소");
		no.setSize(70,35);
		no.setLocation(65,215);
		c.add(no);
		no.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setVisible(false);
			}
		});
		
		
		setVisible(false);
	}
}
