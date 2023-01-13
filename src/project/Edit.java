package project;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;

public class Edit extends JFrame {
	Edit(){
		setSize(500,600);
		this.setResizable(false);
		setTitle("(두면 도서관)나의 회원 정보 수정");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(null);
		c.setBackground(Color.white);
		
		// 타이틀 만들기
		ImageIcon logoIcon = new ImageIcon("image/ed.png");
		Image img = logoIcon.getImage();	// 아이콘 크기 수정을 위해 필요한 과정
		Image imgfin = img.getScaledInstance(60, 60, java.awt.Image.SCALE_SMOOTH);
		ImageIcon logoIconfin = new ImageIcon(imgfin);
		JLabel title = new JLabel("내 정보 수정", logoIconfin, SwingConstants.CENTER);
		title.setLocation(40,30);
		title.setSize(380,80);
		title.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 30));
		c.add(title);
		
		// 레이블
		JLabel idla = new JLabel("ID");
		JLabel pwla = new JLabel("PW");
		JLabel namela = new JLabel("이름");
		JLabel birthla = new JLabel("생년월일");
		JLabel genderla = new JLabel("성별");
		JLabel addressla = new JLabel("주소");
		
		idla.setSize(100,30);
		idla.setLocation(80,115);
		idla.setFont(new Font("함초롱돋움",Font.BOLD, 20));
		c.add(idla);
		
		pwla.setSize(100,30);
		pwla.setLocation(80,155);
		pwla.setFont(new Font("함초롱돋움",Font.BOLD, 20));
		c.add(pwla);
		
		namela.setSize(100,30);
		namela.setLocation(80,195);
		namela.setFont(new Font("함초롱돋움",Font.BOLD, 20));
		c.add(namela);
		
		birthla.setSize(100,30);
		birthla.setLocation(80,235);
		birthla.setFont(new Font("함초롱돋움",Font.BOLD, 20));
		c.add(birthla);
		
		genderla.setSize(100,30);
		genderla.setLocation(80,275);
		genderla.setFont(new Font("함초롱돋움",Font.BOLD, 20));
		c.add(genderla);
		
		addressla.setSize(100,30);
		addressla.setLocation(80,320);
		addressla.setFont(new Font("함초롱돋움",Font.BOLD, 20));
		c.add(addressla);
		
		//패널
		// DB의 정보들이 자동 출력되도록!!!!!!!!!!!!!!!!!!!!
		
		JPanel idp = new JPanel();
		JPanel pwp = new JPanel();
		JPanel namep = new JPanel();
		JPanel birthp = new JPanel();
		JPanel genderp = new JPanel();
		JPanel addressp = new JPanel();
		
		idp.setLocation(200,115);
		idp.setSize(250,30);
		idp.setBackground(new Color(245,245,245));
		c.add(idp);
		
		pwp.setLocation(200,155);
		pwp.setSize(250,30);
		pwp.setBackground(new Color(245,245,245));
		c.add(pwp);
		
		namep.setLocation(200,195);
		namep.setSize(250,30);
		namep.setBackground(new Color(245,245,245));
		c.add(namep);
		
		birthp.setLocation(200,235);
		birthp.setSize(250,30);
		birthp.setBackground(new Color(245,245,245));
		c.add(birthp);
		
		addressp.setLocation(200,320);
		addressp.setSize(250,77);
		addressp.setBackground(new Color(245,245,245));
		c.add(addressp);
		
		
		//성별. 수정 불가
		// DB의 정보들이 자동 출력되도록!!!!!!!!!!!!!!!!!!!!
		// 이미지 파일로 체크 표시 출력되면 좋을 듯

		ButtonGroup g = new ButtonGroup();
		JRadioButton girl = new JRadioButton("여자");
		JRadioButton boy = new JRadioButton("남자", true);
		girl.setFont(new Font("함초롱돋움",Font.BOLD, 15));
		boy.setFont(new Font("함초롱돋움",Font.BOLD, 15));
		girl.setLocation(290,275);
		boy.setLocation(200,275);
		girl.setSize(60,30);
		boy.setSize(60,30);
		
		g.add(boy);
		g.add(girl);
		c.add(boy);
		c.add(girl);
		
		genderp.setLocation(200,275);
		genderp.setSize(250,30);
		genderp.setBackground(new Color(245,245,245));
		c.add(genderp);
		
		// 유료회원 가입 여부
		JCheckBox royal = new JCheckBox(" 유료회원으로 가입하시겠습니까?");
		royal.setFont(new Font("함초롱돋움",Font.BOLD, 15));
		royal.setSize(270,30);
		royal.setLocation(80,420);
		c.add(royal);
		
		// 확인 버튼
		// 변경된 데이터 DB 업데이트 및 메인으로 돌아가기!!!!!!!!!!!!!!!!!!!!!!!!!
		
		JButton edit = new JButton("확인");
		edit.setSize(80,30);
		edit.setLocation(200,490);
		edit.setFont(new Font("휴먼엑스포",Font.PLAIN, 18));
		edit.setBackground(new Color(0,100,0));
		edit.setForeground(Color.white);
		c.add(edit);
		
		// 뒤로 가기
		BackBTN back = new BackBTN(this);
		c.add(back);
				
		setVisible(true);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Edit();
	}

}
