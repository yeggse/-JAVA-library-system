package projectDialogs;

import java.awt.*;
import javax.swing.*;

public class JoinDialog extends JDialog{
	public JoinDialog(){
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
		
		
		//기본정보 입력
		JTextField idfeild = new JTextField(8);
		JPasswordField pwfeild = new JPasswordField(8);
		JTextField namefeild = new JTextField(8);
		JTextField birthfeild = new JTextField(8);
		JTextArea addressfeild = new JTextArea();
		
		idfeild.setLocation(145,65);
		idfeild.setSize(140,28);
		pwfeild.setLocation(145,98);
		pwfeild.setSize(140,28);
		namefeild.setLocation(145,130);
		namefeild.setSize(140,28);
		birthfeild.setLocation(145,163);
		birthfeild.setSize(140,28);
		addressfeild.setLocation(145,235);
		addressfeild.setSize(140,70);
		
		ButtonGroup g = new ButtonGroup();
		JRadioButton girl = new JRadioButton("여자");
		JRadioButton boy = new JRadioButton("남자", true);
		girl.setLocation(215,200);
		boy.setLocation(145,200);
		girl.setSize(50,30);
		boy.setSize(50,30);
		
		g.add(boy);
		g.add(girl);
		
		c.add(idfeild);
		c.add(pwfeild);
		c.add(namefeild);
		c.add(birthfeild);
		c.add(boy);
		c.add(girl);
		c.add(addressfeild);
		
		// 유료회원 가입 여부
		JCheckBox royal = new JCheckBox(" 유료회원에 가입하시겠습니까?");
		royal.setSize(200,30);
		royal.setLocation(45,315);
		c.add(royal);

		// 이용약관
			//////스크롤바가 들어가질 않음..!!!!!!!!!!!!!!!
		Agreement ag = new Agreement();
		
		JTextArea agree = new JTextArea();
		JScrollPane scroll = new JScrollPane(agree);
		agree.setText(ag.text);
		agree.setLocation(40,355);
		agree.setSize(240,60);
//		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		this.getContentPane().add(scroll);
		
		JCheckBox agreeOrNot = new JCheckBox("약관 동의");
		agreeOrNot.setSize(95,30);
		agreeOrNot.setLocation(200,420);
		c.add(agreeOrNot);
		
		// 확인 버튼
			// DB저장 이벤트 필요함!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
			// 로그인 메인 화면 출력해야함!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		JButton CheckBtn = new JButton("확인");
		CheckBtn.setSize(60,30);
		CheckBtn.setLocation(130,460);
		CheckBtn.setBackground(new Color(94,94,94));
		CheckBtn.setForeground(Color.white);
		c.add(CheckBtn);
		
		
		setVisible(false);
	}
	
}

