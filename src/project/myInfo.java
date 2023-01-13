package project;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.*;
import javax.swing.*;

public class myInfo extends JFrame{
	myInfo(){
		setSize(500,600);
		this.setResizable(false);
		setTitle("(두면 도서관)나의 회원 정보 보기");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(null);
		c.setBackground(Color.white);
		
		// 타이틀 만들기
		ImageIcon logoIcon = new ImageIcon("image/me.png");
		Image img = logoIcon.getImage();	// 아이콘 크기 수정을 위해 필요한 과정
		Image imgfin = img.getScaledInstance(60, 60, java.awt.Image.SCALE_SMOOTH);
		ImageIcon logoIconfin = new ImageIcon(imgfin);
		JLabel title = new JLabel("내 정보", logoIconfin, SwingConstants.CENTER);
		title.setLocation(50,20);
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
		JLabel bookla = new JLabel("대여 중인 도서");
		JLabel rebookla = new JLabel("도서 반납 일자");
		
		idla.setSize(100,30);
		idla.setLocation(80,105);
		idla.setFont(new Font("함초롱돋움",Font.BOLD, 20));
		c.add(idla);
		
		pwla.setSize(100,30);
		pwla.setLocation(80,145);
		pwla.setFont(new Font("함초롱돋움",Font.BOLD, 20));
		c.add(pwla);
		
		namela.setSize(100,30);
		namela.setLocation(80,185);
		namela.setFont(new Font("함초롱돋움",Font.BOLD, 20));
		c.add(namela);
		
		birthla.setSize(100,30);
		birthla.setLocation(80,225);
		birthla.setFont(new Font("함초롱돋움",Font.BOLD, 20));
		c.add(birthla);
		
		genderla.setSize(100,30);
		genderla.setLocation(80,265);
		genderla.setFont(new Font("함초롱돋움",Font.BOLD, 20));
		c.add(genderla);
		
		addressla.setSize(100,30);
		addressla.setLocation(80,305);
		addressla.setFont(new Font("함초롱돋움",Font.BOLD, 20));
		c.add(addressla);
		
		bookla.setSize(150,30);
		bookla.setLocation(80,368);
		bookla.setFont(new Font("함초롱돋움",Font.BOLD, 17));
		c.add(bookla);
		
		rebookla.setSize(150,30);
		rebookla.setLocation(80,408);
		rebookla.setFont(new Font("함초롱돋움",Font.BOLD, 17));
		c.add(rebookla);
		
		//패널
		// DB의 정보들이 자동 출력되도록!!!!!!!!!!!!!!!!!!!!
		
		JPanel idp = new JPanel();
		JPanel pwp = new JPanel();
		JPanel namep = new JPanel();
		JPanel birthp = new JPanel();
		JPanel genderp = new JPanel();
		JPanel addressp = new JPanel();
		JPanel bookp = new JPanel();
		JPanel rebookp = new JPanel();
		
		idp.setLocation(200,105);
		idp.setSize(250,30);
		idp.setBackground(new Color(245,245,245));
		c.add(idp);
		
		pwp.setLocation(200,145);
		pwp.setSize(250,30);
		pwp.setBackground(new Color(245,245,245));
		c.add(pwp);
		
		namep.setLocation(200,185);
		namep.setSize(250,30);
		namep.setBackground(new Color(245,245,245));
		c.add(namep);
		
		birthp.setLocation(200,225);
		birthp.setSize(250,30);
		birthp.setBackground(new Color(245,245,245));
		c.add(birthp);
		
		addressp.setLocation(200,305);
		addressp.setSize(250,57);
		addressp.setBackground(new Color(245,245,245));
		c.add(addressp);
		
		bookp.setLocation(200,370);
		bookp.setSize(250,30);
		bookp.setBackground(new Color(245,245,245));
		c.add(bookp);
		
		rebookp.setLocation(200,410);
		rebookp.setSize(250,30);
		rebookp.setBackground(new Color(245,245,245));
		c.add(rebookp);
		
		//성별
		// DB의 정보들이 자동 출력되도록!!!!!!!!!!!!!!!!!!!!
		// 이미지 파일로 체크 표시 출력되면 좋을 듯

		ButtonGroup g = new ButtonGroup();
		JRadioButton girl = new JRadioButton("여자");
		JRadioButton boy = new JRadioButton("남자", true);
		girl.setFont(new Font("함초롱돋움",Font.BOLD, 15));
		boy.setFont(new Font("함초롱돋움",Font.BOLD, 15));
		girl.setLocation(290,265);
		boy.setLocation(200,265);
		girl.setSize(60,30);
		boy.setSize(60,30);
		
		g.add(boy);
		g.add(girl);
		c.add(boy);
		c.add(girl);
		
		genderp.setLocation(200,265);
		genderp.setSize(250,30);
		genderp.setBackground(new Color(245,245,245));
		c.add(genderp);
		
		// 유료회원 가입 여부
		// 이미지 파일로 체크 표시 출력되면 좋을 듯
		JCheckBox royal = new JCheckBox(" 유료회원 가입 여부");
		royal.setFont(new Font("함초롱돋움",Font.BOLD, 15));
		royal.setSize(170,30);
		royal.setLocation(80,460);
		c.add(royal);
				
		
		// 수정 버튼
		JButton edit = new JButton("수정");
		edit.setSize(80,30);
		edit.setLocation(220,510);
		edit.setFont(new Font("휴먼엑스포",Font.PLAIN, 18));
		edit.setBackground(new Color(255,218,185));
		edit.setForeground(new Color(105,105,105));
		c.add(edit);
		
		edit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Edit edit = new Edit();
				setVisible(false);
			}
		});
		
		// 뒤로 가기
		BackBTN back = new BackBTN(this);
		c.add(back);
				
		setVisible(true);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new myInfo();
	}
}
