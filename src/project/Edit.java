package project;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Edit extends JFrame {
//	Connection conn;
	Statement stmt = null;
	static String id;
	String royalCheck;
	Edit(Statement stmt, String id){
		this.stmt = stmt;
		this.id = id;
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
		JPanel idp = new JPanel();
		JPanel pwp = new JPanel();
		JPanel namep = new JPanel();
		JPanel birthp = new JPanel();
		JPanel genderp = new JPanel();
		JPanel addressp = new JPanel();
		
		// id 정보 
		idp.setLocation(200,115);
		idp.setSize(250,30);
		idp.setBackground(new Color(245,245,245));
		c.add(idp);
		
		JLabel idget = new JLabel();
		idget.setSize(200,25);
		idget.setText(id);
		idp.add(idget);
		
		// pw 정보 수정
		pwp.setLocation(200,155);
		pwp.setSize(250,30);
		pwp.setLayout(null);
		pwp.setBackground(new Color(245,245,245));
		c.add(pwp);
		
		JPasswordField pwfeild = new JPasswordField(8);
		pwfeild.setLocation(15,1);
		pwfeild.setSize(220,28);
		pwp.add(pwfeild);
		
		// 이름 정보 
		namep.setLocation(200,195);
		namep.setSize(250,30);
		namep.setLayout(new FlowLayout());
		namep.setBackground(new Color(245,245,245));
		c.add(namep);
		
		JLabel nameget = new JLabel();
		nameget.setSize(200,25);
		namep.add(nameget);
		
		// 생일 정보 
		birthp.setLocation(200,235);
		birthp.setSize(250,30);
		birthp.setLayout(new FlowLayout());
		birthp.setBackground(new Color(245,245,245));
		c.add(birthp);
		
		JLabel birthget = new JLabel();
		birthget.setSize(200,25);
		birthp.add(birthget);
		
		// 주소 정보 수정
		addressp.setLocation(200,320);
		addressp.setSize(250,77);
		addressp.setLayout(null);
		addressp.setBackground(new Color(245,245,245));
		c.add(addressp);
		
		JTextArea addressfeild = new JTextArea();
		addressfeild.setLocation(25,3);
		addressfeild.setSize(200,70);
		addressp.add(addressfeild);
		
		
		//성별
		JLabel gen = new JLabel();
		String gender = null;
		
		genderp.setLocation(200,275);
		genderp.setSize(250,30);
		genderp.setBackground(new Color(245,245,245));
		c.add(genderp);
		genderp.add(gen);
		
		
		// 유료회원 가입 여부
		JCheckBox royal = new JCheckBox(" 유료회원으로 가입하시겠습니까?");
		royal.setFont(new Font("함초롱돋움",Font.BOLD, 15));
		royal.setSize(270,30);
		royal.setLocation(80,420);
		c.add(royal);
		
		// 정보 불러오기
		try {
			ResultSet srs = stmt.executeQuery("select * from people where id = '"+id+"';");
			while (srs.next()) {
				if("여".equals(srs.getString("gender"))) {
					gender ="여성";
				} else {
					gender ="남성";
				}
				gen.setText(gender);
				addressfeild.setText(srs.getString("address"));
				birthget.setText(srs.getString("birth"));
				nameget.setText(srs.getString("name"));
				if("O".equals(srs.getString("royal"))) {
					royal.setSelected(true);
				}
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			System.out.println("eidt : gender 파트 오류");
		}
		
		// 확인 버튼
		JButton edit = new JButton("확인");
		edit.setSize(80,30);
		edit.setLocation(200,490);
		edit.setFont(new Font("휴먼엑스포",Font.PLAIN, 18));
		edit.setBackground(new Color(0,100,0));
		edit.setForeground(Color.white);
		c.add(edit);

		edit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				// royal 체크 여부
				if(royal.isSelected()) {
					royalCheck = "O";
				} else {
					royalCheck = "X";
				}
				
				String query = "update people set pw = '"+pwfeild.getText()+"', address = '"+addressfeild.getText()+"', royal = '"+royalCheck+"' where id = '"+id+"';";
				if(pwfeild.getText().equals("")	|| addressfeild.getText().equals("") ) {
					JOptionPane.showMessageDialog(null, "수정 실패 \n모든정보 입력하세요.", "실패", JOptionPane.WARNING_MESSAGE);
				} else {
					try {
						stmt.executeUpdate(query);
						setVisible(false);
						if(royal.isSelected()) {
							JOptionPane.showMessageDialog(null, "내 정보 수정이 완료되었습니다. \n 유료 회원은 비용이 청구되며, 도서 대여가 가능합니다.", "정보수정 완료", JOptionPane.INFORMATION_MESSAGE);
						} else {
							JOptionPane.showMessageDialog(null, "내 정보 수정이 완료되었습니다. ", "정보수정 완료", JOptionPane.INFORMATION_MESSAGE);
						}
						
						Main main = new Main(stmt, id);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						System.out.println("정보 수정 : DB저장에서 오류남유");
					}
				}
			}
		});
		
		// 뒤로 가기
		BackBTN back = new BackBTN(id, stmt, this);
		c.add(back);
				
		setVisible(true);
	}
}
