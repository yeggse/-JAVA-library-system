package administrator;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class sub extends JPanel {
	static String id;
	static Statement stmt = null;
	// JTable
	Object ob[][] = new Object[0][6]; //데이터 표시에 열만 나오게 설정
	DefaultTableModel model;  // 데이터 저장 부분
	JTable table;
	JScrollPane js;
	String str[] = {"ID", "이름", "성별", "유료가입", "생일", "주소"}; // 컬럼 명
	sub(){
		//Statement stmt, String id
		this.stmt = stmt;
		this.id = id;
//		this.setResizable(false);
//		setTitle("(두면 도서관)관리자 페이지 : 회원");
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		Container c = getContentPane();
		setBackground(new Color(246, 234, 140));
		setLayout(null);
		setSize(850,460);
		// 타이틀 만들기
				ImageIcon logoIcon = new ImageIcon("image/mm.png");
				Image img = logoIcon.getImage();	// 아이콘 크기 수정을 위해 필요한 과정
				Image imgfin = img.getScaledInstance(70, 70, java.awt.Image.SCALE_SMOOTH);
				ImageIcon liIconfin = new ImageIcon(imgfin);
				JLabel title = new JLabel("회원 관리 페이지", liIconfin, SwingConstants.CENTER);
				title.setLocation(65,20);
				title.setSize(450,80);
				title.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 30));
//				title.setForeground(new Color(255,255,240));
				add(title);
				
				// 결과 도출 패널
				JPanel resultPanel = new JPanel();
				resultPanel.setSize(488,250);
				resultPanel.setLocation(45,113);
				resultPanel.setBackground(new Color(248, 202, 0));
				resultPanel.setLayout(null);
				add(resultPanel);
						
				// 결과 도출 그래프
				model = new DefaultTableModel(ob,str);	// 데이터 저장[][], 컬럼 명
				table = new JTable(model);
				js = new JScrollPane(table);
				js.setLocation(13,10);
				js.setSize(460,230);
				resultPanel.add(js);
				
				// 회원 검색
				JLabel sname =new JLabel("검색하고자 하는 회원의 이름을 기입하세요");
				sname.setSize(300,40);
				sname.setLocation(105,375);
				sname.setForeground(Color.gray);
				sname.setBackground(new Color(255,255,176));
				add(sname);
				
				JButton search =new JButton("찾기");
				search.setSize(100,38);
				search.setLocation(420,408);
				search.setBackground(new Color(253, 214, 146));
				add(search);
				
				JTextField txt = new JTextField(15);
				txt.setSize(330,38);
				txt.setLocation(85,410);
				add(txt);
				
				
	}
}



