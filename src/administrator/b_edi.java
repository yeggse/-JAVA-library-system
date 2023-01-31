package administrator;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class b_edi extends JDialog{
	Statement stmt;
	String id;
	JFrame jframe;
	
	b_edi(Statement stmt, String id, JFrame jframe){
		this.stmt = stmt;
		this.id = id;
		this.jframe = jframe;
		
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
		JLabel book = new JLabel("수정할 책 이름");
		book.setSize(150,35);
		book.setLocation(30,70);
		c.add(book);
		
		Vector<String> arr = new Vector<String>();
		String arrlist[] = null;
		try {
			ResultSet srs = stmt.executeQuery("select * from book;");
			
			while(srs.next()) {
				arr.add(srs.getString("book_title"));
			}
			arrlist = arr.toArray(new String[arr.size()]);
			
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
			System.out.println("list 오류");
		}
		
		JList<String> list = new JList<String>(arrlist);
		JScrollPane sp = new JScrollPane(list);
		sp.setSize(250,40);
		sp.setLocation(20,100);
		c.add(sp);
		
		
		// 위치 - 수정
		JLabel loca = new JLabel("변경될 위치");
		loca.setSize(90,30);
		loca.setLocation(30,140);
		c.add(loca);
		
		JTextField ltxt = new JTextField(10);
		ltxt.setSize(250,35);
		ltxt.setLocation(20,175);
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
				System.out.println(list.getSelectedValue());
				try {
					if(ltxt.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "변경될 위치를을 입력하세요.", "입력값 누락", JOptionPane.ERROR_MESSAGE);
					}else {
						System.out.println(list.getSelectedValue());
						ResultSet srs = stmt.executeQuery("select * from book where book_title='"+list.getSelectedValue()+"';");
						System.out.println("select * from book where book_title='"+list.getSelectedValue()+"';");
						if(!srs.next()) {
							JOptionPane.showMessageDialog(null, "책 이름을 확인하세요.", "입력값 오류", JOptionPane.ERROR_MESSAGE);
						} else {
							stmt.executeUpdate("update book set book_location='"+ltxt.getText()+"' where book_title='"+list.getSelectedValue()+"';");
							System.out.println("update book set book_location='"+ltxt.getText()+"' where book_title='"+list.getSelectedValue()+"';");
							JOptionPane.showMessageDialog(null, "책 정보 수정이 완료되었습니다.", "수정 완료", JOptionPane.INFORMATION_MESSAGE);
							setVisible(false);
							jframe.dispose();
							Admin_Book adbook = new Admin_Book(stmt, id);
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
