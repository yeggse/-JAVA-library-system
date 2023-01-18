package projectDialogs;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class RetrunDialog extends JFrame{
	String id;
	Statement stmt = null;
	//36,55,101 : 남
	//255,235,183  :노
	public RetrunDialog(Statement stmt, String id) {
		this.id = id;
		this.stmt = stmt;
		setSize(350,400);
		this.setResizable(false);
		setTitle("도서 반납");
		Container c = getContentPane();
		c.setLayout(null);
		c.setBackground(new Color(245,239,230));
		
			
		//타이틀 
		JLabel title = new JLabel("대여 도서 반납");
		title.setSize(350,30);
		title.setLocation(85,30);
		title.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 23));
		c.add(title);
		
		//도서명 안내 라벨
		JLabel sub = new JLabel("대여 도서");
		sub.setLocation(25,88);
		sub.setSize(300,40);
		sub.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 17));
		c.add(sub);
		
		//반납 일자 안내 라벨
		JLabel day = new JLabel("반납 일자");
		day.setLocation(25,190);
		day.setSize(300,40);
		day.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 17));
		c.add(day);
		
		//대여 도서 출력 라벨
		mypanel b = new mypanel(45,125);
		c.add(b);
		JLabel bookLabel = new JLabel();
		bookLabel.setSize(300,40);
		bookLabel.setFont(new Font("함초롱돋움", Font.BOLD, 15));
		b.add(bookLabel);
		
		String bolabel="";
		
		//반납일자 출력 라벨
		mypanel r = new mypanel(45,225);
		c.add(r);
		JLabel returnday = new JLabel();
		returnday.setSize(300,40);
		returnday.setFont(new Font("함초롱돋움", Font.BOLD, 15));
		r.add(returnday);
		
		String relabel="";
		
		//출력
		try {
			ResultSet titlesrs = stmt.executeQuery("select * from book where id = '"+id+"';");
			System.out.println("select * from book where id = '"+id+"';");
				while(titlesrs.next()) {
					bolabel = titlesrs.getString("book_title");
					relabel = titlesrs.getString("backdate");
				}
			bookLabel.setText(bolabel);
			returnday.setText(relabel);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("반납 예정 라벨 오류");
		}
		
		// 아웃트로 설명
		JLabel check = new JLabel("정말 반납을 희망하십니까?");
		check.setOpaque(true);
		check.setForeground(new Color(125,110,131));
		check.setBackground(new Color(245,239,230));
		check.setFont(new Font("본고딕 KR", Font.BOLD, 13));
		check.setLocation(90,280);
		check.setSize(300,30);
		c.add(check);
		
		// 아니요 버튼
		JButton no = new JButton("반납 취소");
		no.setSize(95,35);
		no.setLocation(70,310);
		no.setBackground(new Color(125,110,131));
		no.setForeground(Color.white);
		no.setFont(new Font("본고딕 KR", Font.BOLD, 13));
		c.add(no);
		
		no.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setVisible(false);
			}
		});
		
		// 네 버튼
		JButton yes = new JButton("반납하기");
		yes.setSize(95,35);
		yes.setLocation(175,310);
		yes.setBackground(new Color(125,110,131));
		yes.setForeground(Color.white);
		yes.setFont(new Font("본고딕 KR", Font.BOLD, 13));
		c.add(yes);
		
		yes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					stmt.executeUpdate("update book set id = null, backdate = null, lentdate = null, book_pas = 'O' where id = '"+id+"';");
					System.out.println("update book set id = null, backdate = null, lentdate = null, book_pas = 'O' where id = '"+id+"';");
					JOptionPane.showMessageDialog(null, "반납이 완료되었습니다. \n이용해 주셔서 감사합니다.", "반납 완료", JOptionPane.INFORMATION_MESSAGE);
					setVisible(false);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					System.out.println("반납 확인 오류");
				}
			}
		});
		
		setVisible(false);
	}
}

class mypanel extends JPanel {
	mypanel(int x, int y){
		setSize(260,45);
		setLocation(x,y);
		setOpaque(true);
		setBackground(new Color(208,184,168));
	}
}
