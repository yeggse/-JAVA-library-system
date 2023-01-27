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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class i_add extends JDialog{
	static Statement stmt;
	static String id;
	JFrame jframe;
	
	i_add(Statement stmt, String id, JFrame jframe){
		super(jframe, true); // 모달
		this.stmt = stmt;
		this.id = id;
		this.jframe = jframe;
		setSize(400,500);
		this.setResizable(false);
		
		setTitle("(관리자) 공지사항 추가 시스템");
		Container c = getContentPane();
		c.setLayout(null);
		c.setBackground(new Color(224,224,224));
		
		JLabel title = new JLabel("공지사항 추가");
		title.setSize(200,30);
		title.setLocation(115,20);
		title.setFont(new Font("휴먼둥근헤드라인", Font.BOLD, 23));
		c.add(title);
		
		// 공지사항 타이틀
		JLabel ititle = new JLabel("제목");
		ititle.setSize(150,35);
		ititle.setLocation(40,70);
		ititle.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 17));
		c.add(ititle);
		
		JTextField ttxt = new JTextField(10);
		ttxt.setSize(260,35);
		ttxt.setLocation(92,70);
		c.add(ttxt);
		
		// 공지사항 내용
		JLabel main = new JLabel("내용");
		main.setSize(90,40);
		main.setLocation(40,115);
		main.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 17));
		c.add(main);
		
		JTextArea mtxt = new JTextArea();
		JScrollPane js = new JScrollPane(mtxt);
		js.setSize(360,215);
		js.setLocation(10,150);
		c.add(js);
		
		// 공지사항 작성자
		JLabel writer = new JLabel("작성자");
		writer.setSize(90,40);
		writer.setLocation(40,370);
		writer.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 17));
		c.add(writer);
		
		JTextField wtxt = new JTextField(10);
		wtxt.setSize(145,35);
		wtxt.setLocation(105,375);
		wtxt.setText("관리자");
		c.add(wtxt);
		
		// 확인 버튼
		JButton yes = new JButton("확인");
		yes.setSize(80,38);
		yes.setLocation(215,420);
		c.add(yes);
		
		yes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				int cnt = 0;
				
				try {
					// 공지 갯수 찾기
					ResultSet csrs = stmt.executeQuery("select max(no) from info");
					while(csrs.next()) {
						cnt = Integer.parseInt(csrs.getString(1))+1;
					}
					
					//입력하기
					if(ttxt.getText().equals("") || mtxt.getText().equals("") || wtxt.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "모든 정보를 기재해 주세요", "정보 누락", JOptionPane.WARNING_MESSAGE);
						System.out.println(cnt+"2");
					} else {
						stmt.executeUpdate("insert into info(no, title, main, writer) "
								+ "values('"+cnt+"','"+ttxt.getText()+"','"+mtxt.getText()+"','"+wtxt.getText()+"');");
						System.out.println("insert into info(no, title, main, writer) "
								+ "values('"+cnt+"','"+ttxt.getText()+"','"+mtxt.getText()+"','"+wtxt.getText()+"');");
						JOptionPane.showMessageDialog(null, "공지사항 추가가 완료되었습니다.", "공지 추가", JOptionPane.PLAIN_MESSAGE);
						setVisible(false);
						jframe.setVisible(false);
						Admin_info adinfo = new Admin_info(stmt, id);
						
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					System.out.println("공지 추가 에러");
				}
			}
		});
		
		
		// 취소 버튼
		JButton no = new JButton("취소");
		no.setSize(80,38);
		no.setLocation(95,420);
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
