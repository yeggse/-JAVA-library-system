package project;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import projectDialogs.LentDialog;
import projectDialogs.infoDia;

public class Information extends JFrame {
	static Statement stmt = null;
	static String id;

	// JTable
	Object ob[][] = new Object[0][3]; //데이터 표시에 열만 나오게 설정
	DefaultTableModel model;  // 데이터 저장 부분
	JTable table;
	JScrollPane js;
	String str[] = {"no","제목", "작성자"}; // 컬럼 명
	
	Information(Statement stmt, String id){
		this.stmt = stmt;
		this.id = id;
		setSize(600,500);
		this.setResizable(false);
		setTitle("(두면 도서관)공지사항");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(null);
		c.setBackground(new Color(255,250,250));
		
		// 타이틀 만들기
		ImageIcon logoIcon = new ImageIcon("image/notice.png");
		Image img = logoIcon.getImage();	// 아이콘 크기 수정을 위해 필요한 과정
		Image imgfin = img.getScaledInstance(60, 60, java.awt.Image.SCALE_SMOOTH);
		ImageIcon logoIconfin = new ImageIcon(imgfin);
		JLabel title = new JLabel("공지사항", logoIconfin, SwingConstants.CENTER);
		title.setLocation(100,20);
		title.setSize(380,80);
		title.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 30));
		c.add(title);
				
		// 중간 설명 작성하기
		JLabel explain = new JLabel("<html>두면 도서관의 공지 사항을 확인할 수 있습니다.</html>");
		explain.setFont(new Font("본고딕 KR", Font.BOLD, 13));
		explain.setLocation(160,55);
		explain.setSize(430,120);
		c.add(explain);	
		
		// 테이블 area
		JPanel panel = new JPanel();
		panel.setSize(520,305);
		panel.setOpaque(true);
		panel.setLocation(30,140);
		panel.setBackground(new Color(221,160,221));
		panel.setLayout(null);
		c.add(panel);
		
		model = new DefaultTableModel(ob,str);	// 데이터 저장[][], 컬럼 명
		table = new JTable(model);
		js = new JScrollPane(table);
		js.setLocation(5,5);
		js.setSize(510,295);
		panel.add(js);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// 기본 도출
		try {
			ResultSet srs = stmt.executeQuery("select * from info;");
			System.out.println("select * from info ;");
			
			while(srs.next()) {
				String no = srs.getString("no");
				String titlet = srs.getString("title");
				String writer = srs.getString("writer");
				
				Object datat[] = {no, titlet, writer};
				model.addRow(datat);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			System.out.println("공지 기본 출력 에러");
		}
		
		
		// 공지사항 자세히 보기
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				ResultSet bsrs;
				ResultSet psrs;
					try {
						int row = table.getSelectedRow();
						  
						 //테이블의 모델객체 얻어오기
						 TableModel data = table.getModel();
							  
						 //선택한 테이블의 row의 모든 값을 이용하여 MemberDTO객체 생성하기
						 String infono = (String)data.getValueAt(row,0);
						 System.out.println(infono);
						 
						 psrs = stmt.executeQuery("select * from info where no = '"+infono+"';");
						 System.out.println("select * from info where no = '"+infono+"';");
						 if(psrs.next()) {
							projectDialogs.infoDia infodia = new infoDia(stmt, id, infono);
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						System.out.println("도서 검색 버튼 내 마우스 이벤트 에러");
					}
			}
		});
		
		
		setVisible(true);
		// 뒤로 가기
		c.add(new BackBTN(id, stmt, this));
	}
}
