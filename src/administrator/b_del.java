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

public class b_del extends JDialog{
	Statement stmt;
	String id;
	b_del(Statement stmt, String id){
		this.stmt = stmt;
		this.id = id;
		
		setSize(300,300);
		this.setResizable(false);
		setTitle("(관리자) 도서 삭제 시스템");
		Container c = getContentPane();
		c.setLayout(null);
		c.setBackground(new Color(224,224,224));
		
		JLabel title = new JLabel("도서 삭제 시스템");
		title.setSize(200,30);
		title.setLocation(50,20);
		title.setFont(new Font("휴먼둥근헤드라인", Font.BOLD, 23));
		c.add(title);
		
		// 삭제하고 싶은 도서 명
		JLabel book = new JLabel("삭제 대상 도서");
		book.setSize(150,35);
		book.setLocation(30,70);
		c.add(book);
		
		JTextField btxt = new JTextField(10);
		btxt.setSize(200,35);
		btxt.setLocation(50,100);
		c.add(btxt);
		
		// 삭제하고 싶은 도서 번호
		JLabel loca = new JLabel("삭제 대상 도서 번호");
		loca.setSize(150,35);
		loca.setLocation(30,140);
		c.add(loca);
		
		JTextField ntxt = new JTextField(10);
		ntxt.setSize(200,35);
		ntxt.setLocation(50,170);
		c.add(ntxt);
		
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
					if(ntxt.getText().equals("")||btxt.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "모든 값을 입력하세요.", "입력 값 누락", JOptionPane.ERROR_MESSAGE);
					}else {
						ResultSet srs = stmt.executeQuery("select * from book where book_title like '%"+btxt.getText()+"%' and book_no = '"+ntxt.getText()+"';");
						System.out.println("select * from book where book_title like '%"+btxt.getText()+"%' and book_no = '"+ntxt.getText()+"';");
						if(!srs.next()) {
							JOptionPane.showMessageDialog(null, "입력한 정보를 확인하세요. \n 도서명은 일부만 기재해도 됩니다.", "입력 값 오류", JOptionPane.ERROR_MESSAGE);
						} else {
							stmt.executeUpdate("delete from book where book_no = '"+ntxt.getText()+"';");
							System.out.println("update book set book_location='"+ntxt.getText()+"' where book_title='"+btxt.getText()+"';");
							JOptionPane.showMessageDialog(null, "책 정보 삭제가 완료되었습니다.", "삭제 완료", JOptionPane.INFORMATION_MESSAGE);
							setVisible(false);
						}
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					System.out.println("도서 삭제 확인 버튼 오류");
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
