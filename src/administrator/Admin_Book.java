package administrator;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

public class Admin_Book extends JFrame{
	static String id;
	static Statement stmt = null;
	// JTable
	Object ob[][] = new Object[0][5]; //데이터 표시에 열만 나오게 설정
	DefaultTableModel model;  // 데이터 저장 부분
	JTable table;
	JScrollPane js;
	String str[] = {"번호", "도 서 명", "출 판 사", "작 가", "위 치"}; // 컬럼 명
	
	Admin_Book(Statement stmt, String id){
		this.stmt = stmt;
		this.id = id;
		setSize(600,500);
		this.setResizable(false);
		setTitle("(두면 도서관)관리자 페이지 : 도서");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setBackground(new Color(255, 218, 185));
		c.setLayout(null);
		
		// 타이틀 만들기
		ImageIcon logoIcon = new ImageIcon("image/bb.png");
		Image img = logoIcon.getImage();	// 아이콘 크기 수정을 위해 필요한 과정
		Image imgfin = img.getScaledInstance(60, 60, java.awt.Image.SCALE_SMOOTH);
		ImageIcon liIconfin = new ImageIcon(imgfin);
		JLabel title = new JLabel("도서 관리 페이지", liIconfin, SwingConstants.CENTER);
		title.setLocation(65,20);
		title.setSize(450,80);
		title.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 30));
		c.add(title);
		
		
		// 결과 도출 패널
		JPanel resultPanel = new JPanel();
		resultPanel.setSize(488,250);
		resultPanel.setLocation(45,97);
		resultPanel.setBackground(new Color(251,169,133));
		resultPanel.setLayout(null);
		c.add(resultPanel);
		
		// 기본 결과 도출 그래프
		model = new DefaultTableModel(ob,str);	// 데이터 저장[][], 컬럼 명
		table = new JTable(model);
		js = new JScrollPane(table);
		js.setLocation(13,10);
		js.setSize(460,230);
		resultPanel.add(js);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		try {
			// 검색할 때마다 출력값 초기화
			for (int i = 0; i < model.getRowCount();) {
	            model.removeRow(0);
	        }
			
			// 찾은 책 출력
			ResultSet srs = stmt.executeQuery("select * from book ;");
			System.out.println("select * from book ;");
			
			while(srs.next()) {
				String not = srs.getString("book_no");
				String titt = srs.getString("book_title");
				String publt = srs.getString("book_publisher");
				String autht = srs.getString("book_author");
				String locat = srs.getString("book_location");
				
				Object datat[] = {not, titt, publt, autht, locat};
				model.addRow(datat);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			System.out.println("도서 출력 에러");
		}
		
		// 도서 검색
		JLabel sname =new JLabel("검색하고자 하는 도서명을 기입하세요");
		sname.setSize(300,40);
		sname.setLocation(105,344);
		sname.setForeground(Color.gray);
		sname.setBackground(new Color(255,255,176));
		c.add(sname);
		
		JButton search =new JButton("찾기");
		search.setSize(85,38);
		search.setLocation(420,375);
		search.setForeground(Color.white);
		search.setFont(new Font("맑은 고딕", Font.BOLD, 17));
		search.setBackground(new Color(237,146,126));
		c.add(search);
		
		JTextField txt = new JTextField(15);
		txt.setSize(330,38);
		txt.setLocation(85,375);
		c.add(txt);
		
		// 도서 검색 버튼 이벤트
		search.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String cnt = null;
				try {
					// 검색할 때마다 출력값 초기화
					for (int i = 0; i < model.getRowCount();) {
			            model.removeRow(0);
			        }
					
					// 출력 도서 수 도출
					ResultSet csrs = stmt.executeQuery("select count(*) from book where book_title like '%"+txt.getText()+"%';");
					while(csrs.next()) {
						cnt = csrs.getString(1);
					}
					
					// 기능 시작
					ResultSet srsr = stmt.executeQuery("select * from book where book_title like '%"+txt.getText()+"%';");
					System.out.println("select * from book where book_title like '%"+txt.getText()+"%';");
					
					if(txt.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "찾고자 하는 도서명을 입력해 주세요.", "미입력 오류", JOptionPane.WARNING_MESSAGE);
					} else {
						if(cnt.equals("0")) {
							JOptionPane.showMessageDialog(null, "존재하지 않는 도서입니다.", "미존재 도서", JOptionPane.INFORMATION_MESSAGE);
						} else {
							while(srsr.next()) {	
								System.out.println("dd");
								String not = srsr.getString("book_no");
								String titt = srsr.getString("book_title");
								String publt = srsr.getString("book_publisher");
								String autht = srsr.getString("book_author");
								String locat = srsr.getString("book_location");
								
								Object datat[] = {not, titt, publt, autht, locat};
								model.addRow(datat);
							}
						}
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					System.out.println("도서 검색 버튼 오류");
				}
				
			}
		});
		
		
		// 엔터로 검색했을 때!
		txt.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if(e.getKeyCode()==KeyEvent.VK_ENTER) {
					String cnt = null;
					try {
						// 검색할 때마다 출력값 초기화
						for (int i = 0; i < model.getRowCount();) {
				            model.removeRow(0);
				        }
						
						// 출력 도서 수 도출
						ResultSet csrs = stmt.executeQuery("select count(*) from book where book_title like '%"+txt.getText()+"%';");
						while(csrs.next()) {
							cnt = csrs.getString(1);
						}
						
						// 기능 시작
						ResultSet srsr = stmt.executeQuery("select * from book where book_title like '%"+txt.getText()+"%';");
						System.out.println("select * from book where book_title like '%"+txt.getText()+"%';");
						
						if(txt.getText().equals("")) {
							JOptionPane.showMessageDialog(null, "찾고자 하는 도서명을 입력해 주세요.", "미입력 오류", JOptionPane.WARNING_MESSAGE);
						} else {
							if(cnt.equals("0")) {
								JOptionPane.showMessageDialog(null, "존재하지 않는 도서입니다.", "미존재 도서", JOptionPane.INFORMATION_MESSAGE);
							} else {
								while(srsr.next()) {	
									System.out.println("dd");
									String not = srsr.getString("book_no");
									String titt = srsr.getString("book_title");
									String publt = srsr.getString("book_publisher");
									String autht = srsr.getString("book_author");
									String locat = srsr.getString("book_location");
									
									Object datat[] = {not, titt, publt, autht, locat};
									model.addRow(datat);
								}
							}
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						System.out.println("도서 검색 엔터 오류");
					}
				}
			}
		});
		
		
		// 추가 버튼
		bookButton add = new bookButton("추가", 95);
		c.add(add);
		b_add aa = new b_add(stmt, id);
		add.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				aa.setVisible(true);
			}
		});
		
		// 수정 버튼
		bookButton edi = new bookButton("수정", 250);
		c.add(edi);
		b_edi ee = new b_edi(stmt, id);
		edi.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ee.setVisible(true);
			}
		});
		
		// 삭제 버튼
		bookButton del = new bookButton("삭제", 400);
		c.add(del);
		b_del dd = new b_del(stmt, id);
		del.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dd.setVisible(true);
			}
		});
		
		// 뒤로가기
		BackBTN back = new BackBTN(id, stmt, this);
		c.add(back);
		
		
		setVisible(true);	
	}
}

class bookButton extends JButton{
	bookButton(String name, int x){
		this.setLocation(x,420);
		this.setText(name);
		this.setSize(100,35);
		this.setBackground(new Color(237,136,130));
		this.setForeground(Color.white);
		this.setFont(new Font("맑은 고딕", Font.BOLD, 17));
	}
}