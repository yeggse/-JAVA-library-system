package administrator;

import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

public class Admin_info extends JFrame{
	static String id;
	static Statement stmt = null;
	// JTable
	Object ob[][] = new Object[0][4]; //데이터 표시에 열만 나오게 설정
	DefaultTableModel model;  // 데이터 저장 부분
	JTable table;
	JScrollPane js;
	String str[] = {"번호", "제목", "내용", "작성자"}; // 컬럼 명
	Admin_info(Statement stmt, String id){
		this.stmt = stmt;
		this.id = id;
		setSize(600,500);
		this.setResizable(false);
		setTitle("(두면 도서관)관리자 페이지 : 공지사항");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setBackground(new Color(191,213,232));
		c.setLayout(null);
		
		// 타이틀 만들기
		ImageIcon logoIcon = new ImageIcon("image/board.png");
		Image img = logoIcon.getImage();	// 아이콘 크기 수정을 위해 필요한 과정
		Image imgfin = img.getScaledInstance(60, 60, java.awt.Image.SCALE_SMOOTH);
		ImageIcon liIconfin = new ImageIcon(imgfin);
		JLabel title = new JLabel("공지사항 관리 페이지", liIconfin, SwingConstants.CENTER);
		title.setLocation(65,20);
		title.setSize(450,80);
		title.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 30));
		c.add(title);
		
		// 결과 도출 패널
		JPanel resultPanel = new JPanel();
		resultPanel.setSize(488,250);
		resultPanel.setLocation(45,113);
		resultPanel.setBackground(new Color(251,169,133));
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
		
		try {
//			// 검색할 때마다 출력값 초기화
//			for (int i = 0; i < model.getRowCount();) {
//	            model.removeRow(0);
//	        }
			
			// 공지 출력
			ResultSet srs = stmt.executeQuery("select * from info ;");
			System.out.println("select * from ifno ;");
			
			while(srs.next()) {
				String not = srs.getString("no");
				String titt = srs.getString("title");
				String maint = srs.getString("main");
				String writert = srs.getString("writer");
				
				Object datat[] = {not, titt, maint, writert};
				model.addRow(datat);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			System.out.println("공지 출력 에러");
		}
		
		
		// 추가 버튼
		JButton add = new JButton("추가");
		add.setBackground(new Color(117,137,191));
		add.setForeground(Color.white);
		add.setSize(100,35);
		add.setLocation(95,380);
		c.add(add);
		b_add aa = new b_add(stmt, id);
//		add.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//				aa.setVisible(true);
//			}
//		});
		
		// 수정 버튼
		JButton edi = new JButton("수정");
		edi.setSize(100,35);
		edi.setLocation(250,380);
		edi.setBackground(new Color(117,137,191));
		edi.setForeground(Color.white);
		c.add(edi);
		b_edi ee = new b_edi(stmt, id);
//		edi.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//				ee.setVisible(true);
//			}
//		});
		
		// 삭제 버튼
		JButton del = new JButton("삭제");
		del.setBackground(new Color(117,137,191));
		del.setForeground(Color.white);
		del.setSize(100,35);
		del.setLocation(400,380);
		c.add(del);
		b_del dd = new b_del(stmt, id);
//		del.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//				dd.setVisible(true);
//			}
//		});
		
		// 뒤로가기
		BackBTN back = new BackBTN(id, stmt, this);
		c.add(back);
		
		setVisible(true);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Admin_info(stmt, id);
	}

}
