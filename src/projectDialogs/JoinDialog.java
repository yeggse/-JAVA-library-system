  package projectDialogs;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;

public class JoinDialog extends JDialog{
	String royalCheck;
	String genderRe;
	boolean dchec;
	JTextField idfeild;
	JPasswordField pwfeild;
	JTextField namefeild;
	JTextField birthfeild;
	JTextArea addressfeild;
	JCheckBox agreeOrNot;
	JLabel dd;
	JCheckBox royal;
	public JoinDialog(JFrame f){
		project.Intro s = (project.Intro) f;
		setSize(350,550);
		this.setResizable(false);
		setTitle("회원가입");
		Container c = getContentPane();
		c.setLayout(null);
		c.setBackground(new Color(232,232,232));
		
		JLabel title = new JLabel("회 원 가 입");
		title.setSize(150,30);
		title.setLocation(110,20);
		title.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 23));
		c.add(title);
		
		//기본정보 라벨
		JLabel idLabel = new JLabel("ID");
		JLabel pwLabel = new JLabel("PW");
		JLabel nameLabel = new JLabel("이름");
		JLabel birthLabel = new JLabel("생년월일");
		JLabel birthsubLabel = new JLabel("(특수문자없이 6자리)");
		JLabel genderLabel = new JLabel("성별");
		JLabel addressLabel = new JLabel("주소");
		
		idLabel.setLocation(45,60);
		idLabel.setSize(80,40);
		idLabel.setFont(new Font("본고딕 KR", Font.BOLD, 15));
		
		pwLabel.setLocation(45,90);
		pwLabel.setSize(80,40);
		pwLabel.setFont(new Font("본고딕 KR", Font.BOLD, 15));
		
		nameLabel.setLocation(45,125);
		nameLabel.setSize(80,40);
		nameLabel.setFont(new Font("본고딕 KR", Font.BOLD, 15));
		
		birthLabel.setLocation(45,155);
		birthLabel.setSize(110,40);
		birthLabel.setFont(new Font("본고딕 KR", Font.BOLD, 15));
		
		birthsubLabel.setLocation(25,170);
		birthsubLabel.setSize(140,40);
		birthsubLabel.setFont(new Font("본고딕 KR", Font.PLAIN, 10));
		
		genderLabel.setLocation(45,190);
		genderLabel.setSize(80,40);
		genderLabel.setFont(new Font("본고딕 KR", Font.BOLD, 15));
		
		addressLabel.setLocation(45,223);
		addressLabel.setSize(80,40);
		addressLabel.setFont(new Font("본고딕 KR", Font.BOLD, 15));
		
		c.add(idLabel);
		c.add(pwLabel);
		c.add(nameLabel);
		c.add(birthLabel);
		c.add(birthsubLabel);
		c.add(genderLabel);
		c.add(addressLabel);
		
		//id 필드
		idfeild = new JTextField(8);
		idfeild.setLocation(145,65);
		idfeild.setSize(115,28);
		c.add(idfeild);
		idfeild.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				JTextField nf = (JTextField)e.getSource();
				if(nf.getText().length()>7) {
					e.consume();
				}
				
				// e.getKeyChar() >= 0x61 && e.getKeyChar() <= 0x7A : 영문(소문자) 
				// e.getKeyChar() >=0x41 && e.getKeyChar() <= 0x5A : 영문(대문자)
				// e.getKeyChar() >= 0x30 && e.getKeyChar() <= 0x39 : 숫자
				if (!((e.getKeyChar() >= 0x61 && e.getKeyChar() <= 0x7A) || (e.getKeyChar() >=0x41 && e.getKeyChar() <= 0x5A)
						|| (e.getKeyChar() >= 0x30 && e.getKeyChar() <= 0x39) || e.getKeyChar() == 8)) {
					JOptionPane.showMessageDialog(null, "영문 혹은 숫자만 입력하세요", "입력 오류", JOptionPane.WARNING_MESSAGE);
					e.consume();
				}
			}
		});
		
		//pw 필드
		pwfeild = new JPasswordField(8);
		pwfeild.setLocation(145,98);
		pwfeild.setSize(140,28);
		c.add(pwfeild);
		pwfeild.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				JTextField nf = (JTextField)e.getSource();
				if(nf.getText().length()>12) {
					e.consume();
				}
			}
		});
		
		//이름 필드
		namefeild = new JTextField(8);
		namefeild.setLocation(145,130);
		namefeild.setSize(140,28);
		c.add(namefeild);
		
		namefeild.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				JTextField nf = (JTextField)e.getSource();
				if(nf.getText().length()>7) {
					e.consume();
				}
			}
		});
		
		//생일 필드
		birthfeild = new JTextField(8);
		birthfeild.setLocation(145,163);
		birthfeild.setSize(140,28);
		c.add(birthfeild);
		
		birthfeild.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				JTextField bth = (JTextField)e.getSource();
				if(bth.getText().length()>5) {
					e.consume();
				}
				
				char c = e.getKeyChar();
				if (!Character.isDigit(c) && e.getKeyChar()!=8) {	// 백스페이스, 숫자만 입력가능하도록 
					e.consume();
					JOptionPane.showMessageDialog(null, "숫자만 입력하세요", "입력 오류", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		
		//주소 필드
		addressfeild = new JTextArea();
		addressfeild.setLocation(145,235);
		addressfeild.setSize(140,70);
		c.add(addressfeild);
		
		// 성별 선택버튼
		ButtonGroup g = new ButtonGroup();
		JRadioButton girl = new JRadioButton("여자");
		JRadioButton boy = new JRadioButton("남자", true);
		girl.setLocation(215,200);
		boy.setLocation(145,200);
		girl.setSize(50,30);
		boy.setSize(50,30);
		
		g.add(boy);
		g.add(girl);
		
		c.add(boy);
		c.add(girl);
		
		
		// 유료회원 가입 여부
		royal = new JCheckBox("유료회원에 가입하시겠습니까?");
		royal.setSize(200,30);
		royal.setLocation(45,315);
		c.add(royal);

		// 이용약관
		Agreement ag = new Agreement();

		JTextArea agree = new JTextArea();
		agree.setText(ag.text);
		JScrollPane scroll = new JScrollPane(agree);
		scroll.setLocation(40,355);
		scroll.setSize(240,60);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		c.add(scroll);
		
		agreeOrNot = new JCheckBox("약관 동의");
		agreeOrNot.setSize(95,30);
		agreeOrNot.setLocation(200,420);
		c.add(agreeOrNot);
		
		// 확인 버튼
		JButton CheckBtn = new JButton("확인");
		CheckBtn.setSize(60,30);
		CheckBtn.setLocation(130,460);
		CheckBtn.setBackground(new Color(94,94,94));
		CheckBtn.setForeground(Color.white);
		c.add(CheckBtn);
		
		// 중복 확인 버튼
		JButton doub = new JButton("중복");
		doub.setSize(60,28);
		doub.setLocation(263,65);
		c.add(doub);

		dd = new JLabel("A");

		doub.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					ResultSet srs = s.stmt.executeQuery("select * from people where id = '"+idfeild.getText()+"';");
					if(srs.next()) {
						JOptionPane.showMessageDialog(null, "이미 사용중인 ID입니다. \n다른 ID를 사용하세요.", "중복 사용", JOptionPane.ERROR_MESSAGE);
						idfeild.setText(null);
					} else if(idfeild.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "ID를 입력하세요.", "빈 값 입력", JOptionPane.ERROR_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null, "사용 가능한 ID입니다.", "사용 가능", JOptionPane.INFORMATION_MESSAGE);
						idfeild.setEnabled(false);
						dd.setText("B");
					}
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					System.out.println("중복확인 이벤트 오류");
				}
			}
		});
		
		
		// DB저장 이벤트 
		CheckBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//중복 확인
				if(dd.getText().equals("A")) {
					dchec = false;
				} else {
					dchec = true;
				}
				
				
				// 실제 이벤트	
				if(idfeild.getText().equals("") || pwfeild.getText().equals("") || namefeild.getText().equals("") 
						|| (!girl.isSelected() && !boy.isSelected())
						|| birthfeild.getText().equals("") || !agreeOrNot.isSelected()) {
					JOptionPane.showMessageDialog(null, "회원가입 실패 \n모든정보 입력하세요.", "실패", JOptionPane.WARNING_MESSAGE);
				} else if(dchec == false){
					JOptionPane.showMessageDialog(null, "ID 중복 확인을 실시해 주세요.", "중복확인", JOptionPane.WARNING_MESSAGE);
				} else {
					if(!royal.isSelected()) {
						int answer = JOptionPane.showConfirmDialog(null, "유료 회원만 도서 대여가 가능합니다. \n 정말 유료 회원 등록을 하지 않겠습니까?", "유료 회원 안내", JOptionPane.YES_NO_OPTION);
						if(answer==JOptionPane.YES_OPTION){  //사용자가 yes를 눌렀을 경우
							royalCheck = "X";
						} else{  //사용자가 Yes 이외의 값을 눌렀을 경우
							royal.isSelected();
							royalCheck = "O";
						}
					}
					
					// gender 체크 표시
					if(girl.isSelected()) {
						genderRe = "여";
					} else {
						genderRe = "남";
					}
					
					// royal 체크 여부
					if(royal.isSelected()) {
						royalCheck = "O";
					} else {
						royalCheck = "X";
					}
					
					try {
						String query = "insert into people values('"+idfeild.getText()+"', '"+pwfeild.getText()+"', '"+namefeild.getText()+"', '"+genderRe+
								"', '"+addressfeild.getText()+"', '"+royalCheck+"', '"+birthfeild.getText()+"', null);";
						
						s.stmt.executeUpdate(query);
						setVisible(false);
						JOptionPane.showMessageDialog(null, "회원가입이 완료되었습니다. \n 로그인 하세요.", "회원가입 완료", JOptionPane.INFORMATION_MESSAGE);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						System.out.println("DB저장에서 오류남유");
					}
				}
			}
		});
		
		setVisible(false);
	}
	
	public void thenew() {	// intro에서 새로 연결할 때, id 필드 입력받을 수 있도록 설정 + 초기화
		idfeild.setEnabled(true);
		idfeild.setText(null);
		pwfeild.setText(null);
		namefeild.setText(null);
		birthfeild.setText(null);
		addressfeild.setText(null);
		royal.setSelected(false);
		agreeOrNot.setSelected(false);
		dd.setText("A");
	}
	
}


