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

public class i_edi extends JDialog{
	static Statement stmt;
	static String id;
	static JFrame jframe;
	
	i_edi(Statement stmt, String id, JFrame jframe){
		super(jframe, true); // 모달
		this.stmt = stmt;
		this.id = id;
		this.jframe = jframe;
		setSize(400,500);
		this.setResizable(false);
		setTitle("(관리자) 공지사항 수정 시스템");
		Container c = getContentPane();
		c.setLayout(null);
		c.setBackground(new Color(224,224,224));
		
		JLabel title = new JLabel("공지사항 수정");
		title.setSize(200,30);
		title.setLocation(115,20);
		title.setFont(new Font("휴먼둥근헤드라인", Font.BOLD, 23));
		c.add(title);
		
		// 수정 번호
		JLabel num = new JLabel("번호");
		num.setSize(190,35);
		num.setLocation(30,70);
		num.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 17));
		c.add(num);
		
		JTextField ntxt = new JTextField(10);
		ntxt.setSize(70,35);
		ntxt.setLocation(80,75);
		c.add(ntxt);
		
		//수정될 관리자
		JLabel writer = new JLabel("작성자");
		writer.setSize(190,35);
		writer.setLocation(175,70);
		writer.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 17));
		c.add(writer);
		
		JTextField wtxt = new JTextField(10);
		wtxt.setSize(135,35);
		wtxt.setLocation(235,75);
		c.add(wtxt);		
		
		
		// 수정될 제목
		JLabel ttitle = new JLabel("공지 제목");
		ttitle.setSize(190,30);
		ttitle.setLocation(30,125);
		ttitle.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 17));
		c.add(ttitle);
		
		JTextField ttxt = new JTextField(10);
		ttxt.setSize(250,35);
		ttxt.setLocation(120,130);
		c.add(ttxt);
		
		//수정될 내용
		JLabel main = new JLabel("공지 내용");
		main.setSize(90,40);
		main.setLocation(30,165);
		main.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 17));
		c.add(main);
		
		JTextArea mtxt = new JTextArea();
		JScrollPane js = new JScrollPane(mtxt);
		js.setSize(360,215);
		js.setLocation(10,200);
		c.add(js);
		
		// 확인 버튼
		JButton yes = new JButton("확인");
		yes.setSize(80,38);
		yes.setLocation(215,420);
		c.add(yes);
		
		yes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				try {
					//입력하기
					if(ttxt.getText().equals("") || mtxt.getText().equals("") || wtxt.getText().equals("") || ntxt.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "모든 정보를 기재해 주세요", "정보 누락", JOptionPane.WARNING_MESSAGE);
					} else {
						ResultSet srs = stmt.executeQuery("select * from info where no = '"+ntxt.getText()+"';");
						if(srs.next()) {
							stmt.executeUpdate("update info set title = '"+ttxt.getText()+"', main = '"+mtxt.getText()+"', writer = '"+wtxt.getText()+"' where no = '"+ntxt.getText()+"';");
							System.out.println("update info set title = '"+ttxt.getText()+"', main = '"+mtxt.getText()+"', writer = '"+wtxt.getText()+"' where no = '"+ntxt.getText()+"';");
							JOptionPane.showMessageDialog(null, "공지사항 수정이 완료되었습니다.", "공지 수정", JOptionPane.PLAIN_MESSAGE);
							setVisible(false);
							jframe.setVisible(false);
							Admin_info adinfo = new Admin_info(stmt, id);
						}else {
							JOptionPane.showMessageDialog(null, "존재하지 않는 번호입니다. \n 번호를 확인해 주세요.", "번호 입력 오류", JOptionPane.ERROR_MESSAGE);
						}
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					System.out.println("공지 수정 에러");
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
