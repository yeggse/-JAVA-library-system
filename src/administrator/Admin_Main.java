package administrator;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import project.Intro;
import project.z_Drawing;

public class Admin_Main extends JFrame {
	static String id;
	static Statement stmt = null;
	
	public Admin_Main(Statement stmt, String id){
		this.stmt = stmt;
		this.id = id;
		setSize(600,500);
		this.setResizable(false);
		setTitle("(두면 도서관)관리자 페이지 입니다.");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(null);
		c.setBackground(Color.darkGray);
		
		// 타이틀 만들기
		ImageIcon logoIcon = new ImageIcon("image/edu.png");
		Image img = logoIcon.getImage();	// 아이콘 크기 수정을 위해 필요한 과정
		Image imgfin = img.getScaledInstance(60, 60, java.awt.Image.SCALE_SMOOTH);
		ImageIcon liIconfin = new ImageIcon(imgfin);
		JLabel title = new JLabel("두면 도서관 관리자 페이지", liIconfin, SwingConstants.CENTER);
		title.setLocation(65,20);
		title.setSize(450,80);
		title.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 30));
		title.setForeground(new Color(255,255,240));
		c.add(title);
				
		// 중간 설명 작성하기
		JLabel explain = new JLabel("<html>두면 도서관 관리자 페이지입니다.<br>"
				+ "해당 페이지는 도서관 관리자만 확인할 수 있습니다. "
				+ "도서관 시스템 구성 중 수정하고자 하는 파트를 선택해 주시기 바랍니다.</html>");
		explain.setFont(new Font("본고딕 KR", Font.BOLD, 12));
		explain.setLocation(100,75);
		explain.setSize(400,120);
		explain.setForeground(new Color(255,255,240));
		c.add(explain);	
		
		// 버튼 설명
		JLabel Book = new JLabel("도서 관리 시스템");
		JLabel Member = new JLabel("회원 관리 시스템");
		
		Book.setFont(new Font("본고딕", Font.BOLD, 15));
		Member.setFont(new Font("본고딕", Font.BOLD, 15));
		
		Book.setLocation(110,350);
		Book.setSize(140,40);
		Book.setForeground(new Color(255,255,240));
		Member.setLocation(360,350);
		Member.setSize(140,40);
		Member.setForeground(new Color(255,255,240));
		
		c.add(Book);
		c.add(Member);
		
		// 버튼 생성
		JButton bookBtn = new JButton();
		bookBtn.setLocation(70,190);
		bookBtn.setSize(190,160);
		bookBtn.setBackground(new Color(106,96,169));
		c.add(bookBtn);
		
		bookBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Admin_Book adbook = new Admin_Book(stmt, id);
				setVisible(false);
			}
		});
		
		JButton memBtn = new JButton();
		memBtn.setLocation(320,190);
		memBtn.setSize(190,160);
		memBtn.setBackground(new Color(251,209,75));
		c.add(memBtn);
		
		memBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Admin_member admem = new Admin_member(stmt, id);
				setVisible(false);
			}
		});
		
		//로그아웃
		logout lg = new logout(this);
		c.add(lg);
		
		// 하단 정보 기재란 
		outtroLabel outline = new outtroLabel(180,405);
		outline.setBackground(Color.darkGray);
		c.add(outline);
		
		setVisible(true);
	}
	
}

class logout extends JButton{
	logout(JFrame jframe){
		setText("로그아웃");
		setSize(80,25);
		setLocation(480,10);
		setBackground(Color.DARK_GRAY);
		setForeground(new Color(135,88,255));
		setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 11));
				
		addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Intro intro = new Intro();
				jframe.setVisible(false);
			}
		});
	}
}
class outtroLabel extends JLabel{
	outtroLabel(int x, int y){
		setText("<html><html><h4 style='text-align : center;'>인천광역시 계양구 부평구"
				+ " <br>tel. 82+032-123-4567        fax. 32-123-4568 </h4></html>");
		setFont(new Font("본고딕 KR", Font.BOLD, 11));
		setOpaque(true);
		setForeground(new Color(220,220,238));
		setSize(400,50);
		setLocation(x, y);
	}
}