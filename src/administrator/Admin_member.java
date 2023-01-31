package administrator;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
import javax.swing.table.TableModel;

public class Admin_member extends JFrame {
	static String id;
	static Statement stmt = null;
	// JTable
	Object ob[][] = new Object[0][6]; //데이터 표시에 열만 나오게 설정
	DefaultTableModel model;  // 데이터 저장 부분
	JTable table;
	JScrollPane js;
	String str[] = {"ID", "이름", "성별", "유료가입", "생일", "주소"}; // 컬럼 명
	
	Admin_member(Statement stmt, String id){
		this.stmt = stmt;
		this.id = id;
		setSize(600,500);
		this.setResizable(false);
		setTitle("(두면 도서관)관리자 페이지 : 회원");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setBackground(new Color(246, 234, 140));
		c.setLayout(null);
		
		// 타이틀 만들기
		ImageIcon logoIcon = new ImageIcon("image/mm.png");
		Image img = logoIcon.getImage();	// 아이콘 크기 수정을 위해 필요한 과정
		Image imgfin = img.getScaledInstance(70, 70, java.awt.Image.SCALE_SMOOTH);
		ImageIcon liIconfin = new ImageIcon(imgfin);
		JLabel title = new JLabel("회원 관리 페이지", liIconfin, SwingConstants.CENTER);
		title.setLocation(65,20);
		title.setSize(450,80);
		title.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 30));
		c.add(title);
		
		// 결과 도출 패널
		JPanel resultPanel = new JPanel();
		resultPanel.setSize(488,250);
		resultPanel.setLocation(45,113);
		resultPanel.setBackground(new Color(248, 202, 0));
		resultPanel.setLayout(null);
		c.add(resultPanel);
				
		// 결과 도출 그래프
		model = new DefaultTableModel(ob,str);	// 데이터 저장[][], 컬럼 명
		table = new JTable(model);
		js = new JScrollPane(table);
		js.setLocation(13,10);
		js.setSize(460,230);
		resultPanel.add(js);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		// 기본 도출
		try {
			// 회원 명단 출력
			ResultSet srs = stmt.executeQuery("select * from people ;");
			System.out.println("select * from people ;");
			
			while(srs.next()) {
				String idt = srs.getString("id");
				String namet = srs.getString("name");
				String gent = srs.getString("gender");
				String royalt = srs.getString("royal");
				String birtht = srs.getString("birth");
				String addt = srs.getString("address");
				
				Object datat[] = {idt, namet, gent, royalt, birtht, addt};
				model.addRow(datat);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			System.out.println("회원 기본 출력 에러");
		}
		
		// 회원 검색
		JLabel sname =new JLabel("검색하고자 하는 회원의 이름을 기입하세요");
		sname.setSize(300,40);
		sname.setLocation(105,375);
		sname.setForeground(Color.gray);
		sname.setBackground(new Color(255,255,176));
		c.add(sname);
		
		JButton search =new JButton("찾기");
		search.setSize(100,38);
		search.setLocation(420,408);
		search.setBackground(new Color(253, 214, 146));
		c.add(search);
		
		JTextField txt = new JTextField(15);
		txt.setSize(330,38);
		txt.setLocation(85,410);
		c.add(txt);
		
		// 회원 검색 이벤트
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
					
					// 출력 회원 수 도출
					ResultSet csrs = stmt.executeQuery("select count(*) from people where name  like '%"+txt.getText()+"%';");
					while(csrs.next()) {
						cnt = csrs.getString(1);
					}
					
					// 기능 시작
					ResultSet srsr = stmt.executeQuery("select * from people where name  like '%"+txt.getText()+"%';");
					System.out.println("select * from people where name = '%"+txt.getText()+"%';");
					
						if(cnt.equals("0")) {
							JOptionPane.showMessageDialog(null, "존재하지 않는 이름입니다.", "미존재 회원", JOptionPane.INFORMATION_MESSAGE);
						} else {
							while(srsr.next()) {	
								System.out.println("dd");
								String idd = srsr.getString("id");
								String named = srsr.getString("name");
								String gend = srsr.getString("gender");
								String royald = srsr.getString("royal");
								String birthd = srsr.getString("birth");
								String addd = srsr.getString("address");
								
								Object datad[] = {idd, named, gend, royald, birthd, addd};
								model.addRow(datad);
							}
						}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					System.out.println("회원 검색 버튼 오류");
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
						
						// 출력 회원 수 도출
						ResultSet csrs = stmt.executeQuery("select count(*) from people where name  like '%"+txt.getText()+"%';");
						while(csrs.next()) {
							cnt = csrs.getString(1);
						}
						
						// 기능 시작
						ResultSet srsr = stmt.executeQuery("select * from people where name  like '%"+txt.getText()+"%';");
						System.out.println("select * from people where name = '%"+txt.getText()+"%';");
						
							if(cnt.equals("0")) {
								JOptionPane.showMessageDialog(null, "존재하지 않는 이름입니다.", "미존재 회원", JOptionPane.INFORMATION_MESSAGE);
							} else {
								while(srsr.next()) {	
									System.out.println("dd");
									String idd = srsr.getString("id");
									String named = srsr.getString("name");
									String gend = srsr.getString("gender");
									String royald = srsr.getString("royal");
									String birthd = srsr.getString("birth");
									String addd = srsr.getString("address");
									
									Object datad[] = {idd, named, gend, royald, birthd, addd};
									model.addRow(datad);
								}
							}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						System.out.println("회원 검색 엔터 오류");
					}
				}
			}
		});
			
		
		
		// 회원 클릭시, 회원 탈퇴 다이알로그 출력
		JFrame memframe = this;
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				 //선택한 셀의 행 번호계산 
				  int row = table.getSelectedRow();
				  
				  //테이블의 모델객체 얻어오기
				  TableModel data = table.getModel();
				  
				  //선택한 테이블의 row의 모든 값을 이용하여 MemberDTO객체 생성하기
				  String memno = (String)data.getValueAt(row,0);
				  System.out.println(memno);
				  
				  p_del del = new p_del(stmt, id, memno, memframe);
				  del.setVisible(true);
			}
		});
		
		// 뒤로가기
		BackBTN back = new BackBTN(id, stmt, this);
		c.add(back);
		
		setVisible(true);
	}

}
