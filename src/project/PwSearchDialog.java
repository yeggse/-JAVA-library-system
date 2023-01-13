package project;
import java.awt.*;
import java.sql.*;
import java.util.*;
import javax.swing.*;

public class PwSearchDialog extends JDialog {
	String searchRes = "검색결과가 출력됩니다.";
	
	PwSearchDialog(){
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
		
		JTextField idfeild = new JTextField(8);
		JTextField namefeild = new JTextField(8);
		idfeild.setLocation(102,140);
		idfeild.setSize(130,28);
		namefeild.setLocation(102,180);
		namefeild.setSize(130,28);
		c.add(idfeild);
		c.add(namefeild);
		
		// 찾기 버튼 생성
			// DB와 연결해서 이벤트 생성해야 함!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		JButton searchBtn = new JButton("Search");
		searchBtn.setSize(80,25);
		searchBtn.setLocation(180,220);
		searchBtn.setBackground(new Color(94,94,94));
		searchBtn.setForeground(Color.white);
		c.add(searchBtn);
		
		// 검색 결과 도출하는 칸
			// 검색결과가 출력되어야 함.(버튼과 연결되어서)
		JPanel resultPan = new JPanel();
		resultPan.setBackground(new Color(232,232,232));
		resultPan.setLocation(18,260);
		resultPan.setSize(250,80);
		c.add(resultPan);
		
		JLabel result = new JLabel();
		result.setLocation(40,40);
		result.setSize(250,80);
		result.setText(searchRes);
		resultPan.add(result);
		
		setVisible(false);
	}


}
