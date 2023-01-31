package sub_administrator;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import project.Intro;

public class sub_Admin_Main extends JFrame {
	static String id;
	static Statement stmt = null;
//	JPanel mai;
	public sub_Admin_Main(Statement stmt, String id){
		this.stmt = stmt;
		this.id = id;
		JFrame jframe = this;
		
		setSize(900,750);
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
		title.setLocation(245,20);
		title.setSize(450,80);
		title.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 30));
		title.setForeground(new Color(255,255,240));
		c.add(title);
				
		// 중간 설명 작성하기
		JLabel explain = new JLabel("<html>두면 도서관 관리자 페이지입니다. "
				+ "해당 페이지는 도서관 관리자만 확인할 수 있습니다. "
				+ "도서관 시스템 구성 중 수정하고자 하는 파트를 선택해 주시기 바랍니다.</html>");
		explain.setFont(new Font("본고딕 KR", Font.BOLD, 17));
		explain.setLocation(180,75);
		explain.setSize(550,120);
		explain.setForeground(new Color(255,255,240));
		c.add(explain);	
		
		// 메인 패널
		JPanel main = new JPanel();
		main.setSize(850,40);
		main.setLocation(15,190);
		main.setLayout(null);
		main.setBackground(Color.white);
		c.add(main);
		
		//메뉴 바 만들기
		JMenuBar mb = new JMenuBar();
		mb.setSize(850,40);
		mb.setLocation(0,0);
		JMenu mem = new JMenu("Member");
		JMenu info = new JMenu("Information");
		JMenu book = new JMenu("Book");
		JMenu ask = new JMenu("Ask");
		
		mb.add(mem);
		mb.add(info);
		mb.add(book);
		mb.add(ask);
		
		main.add(mb);
		
		JPanel mai = new JPanel();
		mai.setSize(850,460);
		mai.setLocation(15,230);
		mai.setLayout(null);
		c.add(mai);
		
		JMenuItem mc = new JMenuItem("Mem_check");
		mem.add(mc);
		mc.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				mai.removeAll();
				sub_Mem_check sub = new sub_Mem_check(stmt, id);
				mai.setBackground(new Color(246, 234, 140));
				sub.setLocation(0,0);
				mai.add(sub);
			}
		});
		
		JMenuItem bc = new JMenuItem("Book_check");
		book.add(bc);
		bc.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				mai.removeAll();
				sub_Book_check sub = new sub_Book_check(stmt, id, jframe);
				mai.setBackground(new Color(255, 218, 185));
				sub.setLocation(0,0);
				mai.add(sub);
			}
		});
		
		JMenuItem ba = new JMenuItem("Book_add");
		book.add(ba);
		ba.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				mai.removeAll();
				sub_Book_check sub = new sub_Book_check(stmt, id, jframe);
				mai.setBackground(new Color(255, 218, 185));
				sub.setLocation(0,0);
				mai.add(sub);
				
				s_b_add aa = new s_b_add(stmt, id, jframe);
				aa.setVisible(true);
			}
		});
		
		JMenuItem be = new JMenuItem("Book_edit");
		book.add(be);
		be.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				mai.removeAll();
				sub_Book_check sub = new sub_Book_check(stmt, id, jframe);
				mai.setBackground(new Color(255, 218, 185));
				sub.setLocation(0,0);
				mai.add(sub);
				
				s_b_edi ee = new s_b_edi(stmt, id, jframe);
				ee.setVisible(true);
			}
		});
		
		JMenuItem bd = new JMenuItem("Book_delete");
		book.add(bd);
		bd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				mai.removeAll();
				sub_Book_check sub = new sub_Book_check(stmt, id, jframe);
				mai.setBackground(new Color(255, 218, 185));
				sub.setLocation(0,0);
				mai.add(sub);
				
				s_b_del dd = new s_b_del(stmt, id, jframe);
				dd.setVisible(true);
			}
		});
		
		
		JMenuItem ic = new JMenuItem("Info__check");
		info.add(ic);
		ic.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				mai.removeAll();
				sub_Admin_info sub = new sub_Admin_info(stmt, id, jframe);
				mai.setBackground(new Color(191,213,232));
				sub.setLocation(0,0);
				mai.add(sub);
			}
		});
		
		
		JMenuItem ia = new JMenuItem("Info_add");
		info.add(ia);
		ia.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				mai.removeAll();
				sub_Admin_info sub = new sub_Admin_info(stmt, id, jframe);
				mai.setBackground(new Color(191,213,232));
				sub.setLocation(0,0);
				mai.add(sub);
				
				s_i_add aa = new s_i_add(stmt, id, jframe);
				aa.setVisible(true);
			}
		});
		
		JMenuItem ie = new JMenuItem("Info_edit");
		info.add(ie);
		ie.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				mai.removeAll();
				sub_Admin_info sub = new sub_Admin_info(stmt, id, jframe);
				mai.setBackground(new Color(191,213,232));
				sub.setLocation(0,0);
				mai.add(sub);
				
				s_i_edi ee = new s_i_edi(stmt, id, jframe);
				ee.setVisible(true);
			}
		});
		
		JMenuItem ide = new JMenuItem("Info_delete");
		info.add(ide);
		ide.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				mai.removeAll();
				sub_Admin_info sub = new sub_Admin_info(stmt, id, jframe);
				mai.setBackground(new Color(191,213,232));
				sub.setLocation(0,0);
				mai.add(sub);
				
				s_i_del dd = new s_i_del(stmt, id, jframe);
				dd.setVisible(true);
			}
		});
		
		JMenuItem ac = new JMenuItem("Ask_check");
		ask.add(ac);
		ac.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				mai.removeAll();
				sub_Admin_ask sub = new sub_Admin_ask(stmt, id, jframe);
				mai.setBackground(new Color(163,201,199));
				sub.setLocation(0,0);
				mai.add(sub);
			}
		});
		
		
		//로그아웃
		logout lg = new logout(this);
		c.add(lg);
		
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
