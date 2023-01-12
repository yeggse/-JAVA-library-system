package project;
import java.awt.*;
import java.sql.*;
import java.util.*;
import javax.swing.*;

public class Intro extends JFrame{
	Intro(){
		setSize(400,500);
		this.setResizable(false);
		setTitle("두면 도서관 관리 시스템");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(null);
		c.setBackground(Color.white);
		
		// 타이틀 만들기
		ImageIcon libraryIcon = new ImageIcon("image/lib.png");
		Image img = libraryIcon.getImage();	// 아이콘 크기 수정을 위해 필요한 과정
		Image imgfin = img.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH);
		ImageIcon liIconfin = new ImageIcon(imgfin);
		JLabel title = new JLabel("두면 도서관", liIconfin, SwingConstants.CENTER);
		title.setLocation(70,20);
		title.setSize(250,80);
		title.setFont(new Font("본고딕 KR", Font.BOLD, 30));
		c.add(title);
		
		// 중간 설명 작성하기
		// 코드를 하나로 합칠 수 있는 방법? (한 줄로 작성할 수 있는 방법?)
		JLabel explain = new JLabel("두면 도서관에 방문해 주셔서 감사합니다.");
		JLabel explain2 = new JLabel("도서 예약 등 다양한 도서관 시스템을 이용하기 위해서는");
		JLabel explain3 = new JLabel("로그인이 필요합니다.");
		explain.setLocation(40,70);
		explain.setSize(250,100);
		explain2.setLocation(40,85);
		explain2.setSize(320,100);
		explain3.setLocation(40,100);
		explain3.setSize(320,100);
		c.add(explain);
		c.add(explain2);
		c.add(explain3);
		
		// ID PW 입력창 만들기
		JTextField id = new JTextField(20);
		JPasswordField pw = new JPasswordField(20);
		ImageIcon idIcon = new ImageIcon("image/user.png");
		Image IDimg = idIcon.getImage();	// 아이콘 크기 수정을 위해 필요한 과정
		Image IDfin = IDimg.getScaledInstance(35, 35, java.awt.Image.SCALE_SMOOTH);
		ImageIcon IDIconfin = new ImageIcon(IDfin);
		
		ImageIcon pwIcon = new ImageIcon("image/pw.png");
		Image PWimg = pwIcon.getImage();	// 아이콘 크기 수정을 위해 필요한 과정
		Image PWfin = PWimg.getScaledInstance(35, 35, java.awt.Image.SCALE_SMOOTH);
		ImageIcon PWIconfin = new ImageIcon(PWfin);
		
		JLabel idLabel = new JLabel(IDIconfin, SwingConstants.CENTER);
		JLabel pwLabel = new JLabel(PWIconfin, SwingConstants.CENTER);
		idLabel.setSize(40,40);
		idLabel.setLocation(50,180);
		pwLabel.setSize(40,40);
		pwLabel.setLocation(50,230);
		id.setSize(200,30);
		pw.setSize(200,30);
		id.setLocation(100,185);
		pw.setLocation(100, 235);
		c.add(idLabel);
		c.add(pwLabel);
		c.add(id);
		c.add(pw);
		
		// 로그인, 비밀번호 찾기 버튼 생성
			// 각 버튼 마다 이벤트 생성해야 함!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		JButton login = new JButton("로그인");
		JButton pwsearch = new JButton("PW 찾기");
		login.setBackground(Color.gray);
		pwsearch.setBackground(Color.gray);
		login.setSize(80,25);
		login.setLocation(220,275);
		pwsearch.setSize(90,25);
		pwsearch.setLocation(125,275);
		
		c.add(login);
		c.add(pwsearch);
		
		JLabel pwguide = new JLabel("비밀번호를 잊으셨나요?");
		pwguide.setFont(new Font("본고딕 KR", Font.ITALIC, 10));
		pwguide.setOpaque(true);
		pwguide.setForeground(Color.gray);
		pwguide.setBackground(Color.white);
		pwguide.setLocation(115,295);
		pwguide.setSize(110,30);
		c.add(pwguide);
		
		// 회원가입 버튼 생성
			// 버튼 이벤트 생성해야 함!!!!!!!!!!!!!!!!!!!!!
		JButton join = new JButton("회원가입");
		join.setBackground(Color.LIGHT_GRAY);
		join.setSize(90,25);
		join.setLocation(195,340);
		c.add(join);
		
		JLabel joinguide = new JLabel("<html> 회원가입 후 <br>시스템을 이용하세요 </html>");
		joinguide.setFont(new Font("본고딕 KR", Font.BOLD, 11));
		joinguide.setSize(200,30);
		joinguide.setLocation(25,335);
		c.add(joinguide);
		
		setVisible(true);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Intro();
	}

}
