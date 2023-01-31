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
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class sub_p_del extends JDialog {
	Statement stmt;
	String id;
	String pono;
	sub_p_del(Statement stmt, String id, String pono){
		this.stmt = stmt;
		this.id = id;
		this.pono = pono;
		
		setSize(250,250);
		this.setResizable(false);
		setTitle("(관리자) 회원 삭제 시스템");
		Container c = getContentPane();
		c.setLayout(null);
		c.setBackground(new Color(224,224,224));
		
		JLabel title = new JLabel("회원 삭제 시스템");
		title.setSize(200,40);
		title.setLocation(18,30);
		title.setFont(new Font("휴먼둥근헤드라인", Font.BOLD, 24));
		c.add(title);
		
		// 중간 안내
		JLabel mid = new JLabel();
		mid.setSize(250,100);
		mid.setLocation(35,40);
		mid.setFont(new Font("굴림체", Font.BOLD, 16));
		c.add(mid);
		String mm = "";
		
		try {
			ResultSet srs = stmt.executeQuery("select * from people where id = '"+pono+"';");
			if(srs.next()) {
				mm = srs.getString("name");
			}
			mid.setText("정말 "+mm+" 회원님을");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("탈퇴 라벨 오류");
		}
		
		JLabel mid2 = new JLabel();
		mid2.setSize(250,100);
		mid2.setLocation(50,60);
		mid2.setText("탈퇴시키겠습니까?");
		mid2.setFont(new Font("굴림체", Font.BOLD, 16));
		c.add(mid2);

		//확인 버튼
		meButton yes = new meButton("확인");
		yes.setLocation(125,155);
		c.add(yes);
		yes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					stmt.executeUpdate("delete from people where id = '"+pono+"';");
					System.out.println("delete from people where id = '"+pono+"';");
					JOptionPane.showMessageDialog(null, "회원 삭제가 완료되었습니다.", "삭제 완료", JOptionPane.INFORMATION_MESSAGE);
					setVisible(false);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					System.out.println("회원 삭제 확인 버튼 오류");
				}
			}
		});
		
		// 취소 버튼
		meButton no = new meButton("취소");
		no.setLocation(25,155);
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

class meButton extends JButton{
	meButton(String name){
		this.setText(name);
		this.setSize(90,35);
		this.setBackground(new Color(139,134,135));
		this.setForeground(Color.white);
		this.setFont(new Font("맑은 고딕", Font.BOLD, 19));
	}
}


