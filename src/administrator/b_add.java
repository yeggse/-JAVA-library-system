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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class b_add extends JDialog{
	Statement stmt;
	String id;
	JFrame jframe;
	
	b_add(Statement stmt, String id, JFrame jframe){
		this.stmt = stmt;
		this.id = id;
		this.jframe = jframe;
		setSize(300,400);
		this.setResizable(false);
		setTitle("(관리자) 도서 추가 시스템");
		Container c = getContentPane();
		c.setLayout(null);
		c.setBackground(new Color(224,224,224));
		
		JLabel title = new JLabel("도서 추가 시스템");
		title.setSize(200,30);
		title.setLocation(50,20);
		title.setFont(new Font("휴먼둥근헤드라인", Font.BOLD, 23));
		c.add(title);
		
		
		// 첵 이름
		JLabel book = new JLabel("책 이름");
		book.setSize(90,35);
		book.setLocation(30,70);
		c.add(book);
		
		JTextField btxt = new JTextField(10);
		btxt.setSize(155,35);
		btxt.setLocation(100,85);
		c.add(btxt);
		
		// 저자명
		JLabel atu = new JLabel("저자명");
		atu.setSize(90,40);
		atu.setLocation(30,130);
		c.add(atu);
		
		JTextField atxt = new JTextField(10);
		atxt.setSize(155,35);
		atxt.setLocation(100,145);
		c.add(atxt);
		
		// 출판사
		JLabel publi = new JLabel("출판사");
		publi.setSize(90,40);
		publi.setLocation(30,190);
		c.add(publi);
		
		JTextField ptxt = new JTextField(10);
		ptxt.setSize(155,35);
		ptxt.setLocation(100,205);
		c.add(ptxt);
		
		// 위치
		JLabel loca = new JLabel("위 치");
		loca.setSize(90,40);
		loca.setLocation(30,250);
		c.add(loca);
		
		JTextField ltxt = new JTextField(10);
		ltxt.setSize(155,35);
		ltxt.setLocation(100,265);
		c.add(ltxt);
		
		// 확인 버튼
		JButton yes = new JButton("확인");
		yes.setSize(70,35);
		yes.setLocation(165,310);
		c.add(yes);
		
		yes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				String cnt = null;
				
				try {
					// 책 수 찾기
					ResultSet csrs = stmt.executeQuery("select count(*) from book");
					while(csrs.next()) {
						cnt = csrs.getString(1);
					}
					
					int num = Integer.parseInt(cnt);
					//입력하기
					if(btxt.getText().equals("") || atxt.getText().equals("") || ltxt.getText().equals("") || ptxt.getText().equals("") ) {
						JOptionPane.showMessageDialog(null, "모든 정보를 기재해 주세요", "정보 누락", JOptionPane.WARNING_MESSAGE);
						System.out.println(cnt+"2");
					} else {
						stmt.executeUpdate("insert into book(book_no, book_title, book_publisher, book_author, book_location, book_pas) "
								+ "values('"+(num+1)+"','"+btxt.getText()+"','"+ptxt.getText()+"','"+atxt.getText()+"','"+ltxt.getText()+"','O');");
						System.out.println("insert into book(book_no, book_title, book_publisher, book_author, book_location, book_pas) "
								+ "values('"+(num+1)+"','"+btxt.getText()+"','"+ptxt.getText()+"','"+atxt.getText()+"','"+ltxt.getText()+"','O');");
						JOptionPane.showMessageDialog(null, "책 추가가 완료되었습니다.", "정보 입력", JOptionPane.PLAIN_MESSAGE);
						setVisible(false);
						jframe.dispose();
						Admin_Book adbook = new Admin_Book(stmt, id);
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					System.out.println("책 추가 에러");
				}
			}
		});
		
		
		// 취소 버튼
		JButton no = new JButton("취소");
		no.setSize(70,35);
		no.setLocation(65,310);
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
