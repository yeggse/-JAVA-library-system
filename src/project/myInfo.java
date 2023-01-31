package project;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import projectDialogs.OutDia;

public class myInfo extends JFrame{
	static String id;
	Statement stmt = null;
	myInfo(Statement stmt, String id){
		this.stmt = stmt;
		this.id = id;
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
		JLabel pwla = new JLabel("유료 회원");
		JLabel namela = new JLabel("이름");
		JLabel birthla = new JLabel("생년월일");
		JLabel genderla = new JLabel("성별");
		JLabel addressla = new JLabel("주소");
		JLabel bookla = new JLabel("대여 중인 도서");
		JLabel rebookla = new JLabel("도서 반납 일자");
		JLabel askla = new JLabel("구매 신청 도서");

		
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
		
		askla.setSize(150,30);
		askla.setLocation(80,460);
		askla.setFont(new Font("함초롱돋움",Font.BOLD, 20));
		c.add(askla);
		
		//패널
		JPanel idp = new JPanel();
		JPanel pwp = new JPanel();
		JPanel namep = new JPanel();
		JPanel birthp = new JPanel();
		JPanel genderp = new JPanel();
		JPanel addressp = new JPanel();
		JPanel bookp = new JPanel();
		JPanel rebookp = new JPanel();
		
		// id 정보 출력
		idp.setLocation(200,105);
		idp.setSize(250,30);
		idp.setLayout(new FlowLayout());
		idp.setBackground(new Color(245,245,245));
		c.add(idp);
		
		JLabel idget = new JLabel();
		idget.setSize(200,25);
		idget.setText(id);
		idp.add(idget);
		
		
		// 유료회원 정보 출력
		pwp.setLocation(200,145);
		pwp.setSize(250,30);
		pwp.setLayout(new FlowLayout());
		pwp.setBackground(new Color(245,245,245));
		c.add(pwp);
		
		String ro = "";
		JLabel royalck = new JLabel();
		royalck.setSize(300,30);
		pwp.add(royalck);
		try {
			ResultSet royalsrs = stmt.executeQuery("select royal from people where id = '"+id+"';");
			while (royalsrs.next()) {
				if("O".equals(royalsrs.getString("royal"))) {
					royalck.setText("유료회원 가입 중");
				} else {
					royalck.setText("유료회원 미가입");
				}
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			System.out.println("my info : royal 파트 오류");
		}
		
		
		// 이름 정보 출력
		namep.setLocation(200,185);
		namep.setSize(250,30);
		namep.setLayout(new FlowLayout());
		namep.setBackground(new Color(245,245,245));
		c.add(namep);
		
		JLabel nameget = new JLabel();
		nameget.setSize(200,25);
		try {
			ResultSet namesrs = stmt.executeQuery("select name from people where id = '"+id+"';");
			while (namesrs.next()) {
				nameget.setText(namesrs.getString("name"));
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			System.out.println("my info : name 파트 오류");
		}
		namep.add(nameget);
		
		
		// 생일 정보 출력
		birthp.setLocation(200,225);
		birthp.setSize(250,30);
		birthp.setLayout(new FlowLayout());
		birthp.setBackground(new Color(245,245,245));
		c.add(birthp);
		
		JLabel birthget = new JLabel();
		birthget.setSize(200,25);
		try {
			ResultSet birthsrs = stmt.executeQuery("select birth from people where id = '"+id+"';");
			while (birthsrs.next()) {
				birthget.setText(birthsrs.getString("birth"));
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			System.out.println("my info : birth 파트 오류");
		}
		birthp.add(birthget);
		
		
		// 주소 정보 출력
		addressp.setLocation(200,305);
		addressp.setSize(250,57);
		addressp.setLayout(new FlowLayout());
		addressp.setBackground(new Color(245,245,245));
		c.add(addressp);
		
		JLabel addressget = new JLabel();
		addressget.setSize(200,25);
		try {
			ResultSet addresssrs = stmt.executeQuery("select address from people where id = '"+id+"';");
			while (addresssrs.next()) {
				addressget.setText(addresssrs.getString("address"));
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			System.out.println("my info : address 파트 오류");
		}
		addressp.add(addressget);
		
		
		// 도서 대여 정보 
		bookp.setLocation(200,370);
		bookp.setSize(250,30);
		bookp.setLayout(new FlowLayout());
		bookp.setBackground(new Color(245,245,245));
		c.add(bookp);
		
		JLabel bookin = new JLabel();
		bookin.setSize(200,25);
		bookp.add(bookin);
		String bookinfo = null;
		
		// 도서 반납일 정보 
		rebookp.setLocation(200,410);
		rebookp.setSize(250,30);
		rebookp.setLayout(new FlowLayout());
		rebookp.setBackground(new Color(245,245,245));
		c.add(rebookp);
		
		JLabel rein = new JLabel();
		rein.setSize(200,25);
		rebookp.add(rein);
		String bookdate = null;
		
		try {
			ResultSet srs = stmt.executeQuery("select * from book where id = '"+id+"';");
			System.out.println("select * from book where id = '"+id+"';");
			if(!srs.next()) {
				bookinfo = "대여 중인 도서 없음";
				bookdate = "-";
			} else {
				bookinfo = srs.getString("book_title");
				bookdate = srs.getString("backdate");
			}
			rein.setText(bookdate);
			bookin.setText(bookinfo);
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
			System.out.println("도서 정보 출력 오류");
		}
		
		//성별
		JLabel gen = new JLabel();
		String gender = null;
		
		genderp.setLocation(200,265);
		genderp.setSize(250,30);
		genderp.setBackground(new Color(245,245,245));
		c.add(genderp);
		genderp.add(gen);
		
		try {
			ResultSet gendersrs = stmt.executeQuery("select gender from people where id = '"+id+"';");
			while (gendersrs.next()) {
				if("여".equals(gendersrs.getString("gender"))) {
					gender ="여성";
				} else {
					gender ="남성";
				}
				
				gen.setText(gender);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			System.out.println("my info : gender 파트 오류");
		}
		
		
		// 도서 신청 내용 출력
		JPanel rp = new JPanel();
		rp.setLocation(200,460);
		rp.setSize(250,30);
		rp.setLayout(new FlowLayout());
		rp.setBackground(new Color(245,245,245));
		c.add(rp);
		
		Vector<String> arr = new Vector<String>();
		String ar[] = null;
		String gtext = null;
		try {
			ResultSet srs = stmt.executeQuery("select * from ask where a_id = '"+id+"';");
			while(srs.next()) {
				arr.add(srs.getString("a_title"));
			}
			ar = arr.toArray(new String[arr.size()]);
			gtext = Arrays.toString(ar);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			System.out.println("도서신청 오류");
		}
		
		JLabel ask = new JLabel(gtext);
		ask.setSize(200,25);
		rp.add(ask);
		
		// 수정 버튼
		JButton edit = new JButton("정보수정");
		edit.setSize(110,30);
		edit.setLocation(140,510);
		edit.setFont(new Font("휴먼둥근헤드라인",Font.PLAIN, 15));
		edit.setBackground(new Color(255,218,185));
		edit.setForeground(new Color(105,105,105));
		c.add(edit);
		
		edit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Edit edit = new Edit(stmt, id);
				setVisible(false);
			}
		});
		
		// 회원탈퇴 버튼
		JButton out = new JButton("회원탈퇴");
		out.setSize(110,30);
		out.setLocation(260,510);
		out.setFont(new Font("휴먼둥근헤드라인",Font.PLAIN, 15));
		out.setBackground(new Color(255,218,185));
		out.setForeground(new Color(105,105,105));
		c.add(out);
		
		out.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				projectDialogs.OutDia out = new OutDia(stmt, id);
			}
		});
		
		
		// 뒤로 가기
		BackBTN back = new BackBTN(id, stmt, this);
		c.add(back);
				
		setVisible(true);
	}
	private JCheckBox JCheckBox(String string) {
		// TODO Auto-generated method stub
		return null;
	}
}
