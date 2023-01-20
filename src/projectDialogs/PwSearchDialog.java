package projectDialogs;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.*;
import javax.swing.*;

import project.Intro;

public class PwSearchDialog extends JDialog {
	
	public PwSearchDialog(JFrame f){
		project.Intro s = (project.Intro)f; 
		setSize(300,400);
		this.setResizable(false);
		setTitle("비밀번호 찾기 시스템");
		Container c = getContentPane();
		c.setLayout(null);
		c.setBackground(new Color(224,224,224));
		
		JLabel title = new JLabel("비밀번호 찾기");
		title.setSize(200,30);
		title.setLocation(65,20);
		title.setFont(new Font("휴먼둥근헤드라인", Font.BOLD, 23));
		c.add(title);
		
		JPanel up = new JPanel();
		up.setBackground(new Color(52,74,94));
		up.setLocation(0,0);
		up.setSize(300,6);
		c.add(up);
		
		JLabel detail = new JLabel();
		detail.setText("<html>* 검색을 위해 아이디, 이름 입력하세요."
				+ "<br> * 정보를 입력한 후 찾기 버튼을 눌러주세요. "
				+ "<br> * 아이디가 존재하지 않는 경우, 회원가입이<br> 필요합니다.</html>");
		detail.setSize(249,150);
		detail.setLocation(15,23);
		c.add(detail);
		
		// 아이디, 이름 입력 받기
		JLabel idLabel = new JLabel("ID");
		JLabel nameLabel = new JLabel("NAME");
		idLabel.setLocation(45,130);
		idLabel.setSize(50,50);
		idLabel.setFont(new Font("본고딕 KR", Font.BOLD, 15));
		nameLabel.setLocation(45,165);
		nameLabel.setSize(50,50);
		nameLabel.setFont(new Font("본고딕 KR", Font.BOLD, 15));
		
		c.add(idLabel);
		c.add(nameLabel);
		
		JTextField id = new JTextField(8);
		JTextField name = new JTextField(8);
		id.setLocation(102,140);
		id.setSize(130,28);
		name.setLocation(102,180);
		name.setSize(130,28);
		c.add(id);
		c.add(name);
		
		// 비밀번호 수정 버튼 생성
		JButton setBtn = new JButton("new PW Set");
		setBtn.setSize(110,25);
		setBtn.setLocation(130,220);
		setBtn.setBackground(new Color(94,94,94));
		setBtn.setForeground(Color.white);
		c.add(setBtn);
	
		// 검색 결과 도출하는 칸
		JPanel resultPan = new JPanel();
		resultPan.setBackground(new Color(232,232,232));
		resultPan.setLocation(18,260);
		resultPan.setSize(250,80);
		resultPan.setLayout(null);
		c.add(resultPan);
				
		JLabel newpw = new JLabel();
		newpw.setLocation(5,5);
		newpw.setSize(250,35);
		newpw.setText("새로운 비밀번호");
		resultPan.add(newpw);
		
		JLabel repw = new JLabel();
		repw.setLocation(5,40);
		repw.setSize(250,35);
		repw.setText("비밀번호 재입력");
		resultPan.add(repw);
		
		JPasswordField np = new JPasswordField(10);
		np.setSize(135,30);
		np.setLocation(110,8);
		resultPan.add(np);
		
		JPasswordField ap = new JPasswordField(10);
		ap.setSize(135,30);
		ap.setLocation(110,48);
		resultPan.add(ap);
		
		setBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ResultSet pwsrs;
				try {
					pwsrs = s.stmt.executeQuery("select * from people where id = '"+id.getText()+"' and name = '"+name.getText()+"';");
					if(id.getText().equals("")||name.getText().equals("")||np.getText().equals("")||ap.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "모든 정보를 입력해 주세요.", "오류", JOptionPane.WARNING_MESSAGE);
					} else {
						if(!pwsrs.next()) {
							JOptionPane.showMessageDialog(null, "정보가 존재하지 않습니다. \n 입력한 값을 확인하세요.", "오류", JOptionPane.WARNING_MESSAGE);
						} else {
							if(!np.getText().equals(ap.getText())) {
								JOptionPane.showMessageDialog(null, "입력한 비밀번호 값이 다릅니다.", "입력 오류", JOptionPane.WARNING_MESSAGE);
							}else {
								try {
									s.stmt.executeUpdate("update people set pw = '"+np.getText()+"' where id = '"+id.getText()+"' and name ='"+name.getText()+"';");
									JOptionPane.showMessageDialog(null, "비밀번호 재설정이 완료되었습니다. \n다시 로그인 해 주세요.", "비밀번호 재설정 완료", JOptionPane.INFORMATION_MESSAGE);
									setVisible(false);
								} catch (SQLException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
									System.out.println("재설정 버튼 오류");
								}
							}
						}
					}
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
					System.out.println("버튼 전체 오류");
				}
			}
		});
		
		setVisible(false);
	}

}

