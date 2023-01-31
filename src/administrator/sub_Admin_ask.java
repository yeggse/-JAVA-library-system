package administrator;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class sub_Admin_ask extends JPanel{
	static String id;
	static Statement stmt = null;
	JFrame jframe;
	
	//테이블
	JTable table;
	JScrollPane js;
	Object ob[][] = new Object[0][6];
	String str[] = {"번호","작성자","도서명","작가","출판사","이유"};	
	DefaultTableModel model;	// 데이터 저장할 수 있는 부분
	
	sub_Admin_ask(Statement stmt, String id, JFrame jframe){
		this.stmt = stmt;
		this.id = id;
		this.jframe = jframe;
		setSize(850,500);
		setBackground(new Color(163,201,199));
		setLayout(null);		
		
		// 타이틀 만들기
		ImageIcon logoIcon = new ImageIcon("image/confirm.png");
		Image img = logoIcon.getImage();	// 아이콘 크기 수정을 위해 필요한 과정
		Image imgfin = img.getScaledInstance(65, 65, java.awt.Image.SCALE_SMOOTH);
		ImageIcon liIconfin = new ImageIcon(imgfin);
		JLabel title = new JLabel("도서신청 관리 페이지", liIconfin, SwingConstants.CENTER);
		title.setLocation(200,20);
		title.setSize(450,80);
		title.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 30));
		add(title);
		
		// 결과 도출 패널
		JPanel resultPanel = new JPanel();
		resultPanel.setSize(688,330);
		resultPanel.setLocation(90,113);
		resultPanel.setBackground(new Color(121,189,154));
		resultPanel.setLayout(null);
		add(resultPanel);
		
		// 결과 테이블 세팅
		model = new DefaultTableModel(ob, str);
		table = new JTable(model);
		js = new JScrollPane(table);
		js.setLocation(10,10);
		js.setSize(660,310);
		resultPanel.add(js);
		
		// 내용 출력 기본 테이블
		try {
			ResultSet srs = stmt.executeQuery("select * from ask;");
			while(srs.next()) {
				String num = srs.getString("a_no");
				String idt = srs.getString("a_id");
				String titlet = srs.getString("a_title");
				String autht = srs.getString("a_author");
				String publist = srs.getString("a_publish");
				String reast = srs.getString("a_reason");
				
				Object data[] = {num, idt, titlet, autht, publist, reast};
				model.addRow(data);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("도서신청 테이블 출력 오류");
		} 
		
		// 클릭하기 이벤트
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				int row = table.getSelectedRow();
				TableModel data = table.getModel();
				String asknum = (String)data.getValueAt(row, 0);	//선택한 테이블의 행-열에 맞는 값 가져오기
				int count = 0;
				
				try {
					ResultSet resultsrs = stmt.executeQuery("select count(*) from result;");
					if(resultsrs.next()) {
						count = Integer.parseInt(resultsrs.getString(1))+1;
					}
					
					ResultSet ssrs = stmt.executeQuery("select * from ask where a_no = '"+asknum+"';");
					if(ssrs.next()) {
						String option[] = {"상세보기", "구매완료", "삭제하기"};
						int answer = JOptionPane.showOptionDialog(null, "무엇을 하시겠습니까?", "선택", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,null,option,option[0]);
						if(answer==JOptionPane.YES_OPTION) {	// 상세보기
							s_a_detail ade = new s_a_detail(stmt, id, asknum);
							
						} else if(answer==JOptionPane.NO_OPTION) {	//구매완료
							stmt.executeUpdate("delete from ask where a_no ='"+asknum+"';");
							JOptionPane.showMessageDialog(null, "구매한 책으로 설정되어, 리스트에서 삭제되었습니다.","삭제 완료", JOptionPane.INFORMATION_MESSAGE);
							setVisible(false);
							sub_Admin_Main admain = new sub_Admin_Main(stmt, id);
							jframe.dispose();
						} else if(answer==JOptionPane.CANCEL_OPTION) {	//삭제하기
							// 팝업띄워서 이유 받기. 
							String reasons[] = {"보유도서 존재", "해당 장르 수량 초과", "내용 기재 불충분", "단종 도서", "부적합 도서", "공공성 없음", "예산 초과", "신청 이유 불명확", "기타"};
							String deldi = (String)JOptionPane.showInputDialog(null,"삭제 이유를 선택하세요.","삭제 이유", JOptionPane.QUESTION_MESSAGE,null,reasons, reasons[2]);
							
							
							System.out.println(count);
							if(deldi != null) {
								System.out.println("ok");
								String text = "insert into result(no, a_no, a_id, a_reason, a_title, a_publish, a_author, deli_reason) "
										+ "values('"+count+"','"+asknum+"','"+ssrs.getString("a_id")+"','"+ssrs.getString("a_reason")+"','"+ssrs.getString("a_title")+"','"+
										ssrs.getString("a_publish")+"','"+ssrs.getString("a_author")+"','"+deldi+"');";
								System.out.println(text);
								actin act = new actin(stmt, id, text);	//result DB에 이유 저장
								act.burn();
								
								stmt.executeUpdate("delete from ask where a_no ='"+asknum+"';");	//ask DB에서 삭제하기
								JOptionPane.showMessageDialog(null, "삭제 이유 설정되어, 리스트에서 삭제되었습니다.","삭제 완료", JOptionPane.INFORMATION_MESSAGE);
								setVisible(false);
								jframe.dispose();
								sub_Admin_Main admain = new sub_Admin_Main(stmt, id);
							} 
						}
						
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					System.out.println("테이블 클릭 이벤트 오류");
				}
			}
		});
		
		
		setVisible(true);
	}
}

class actin{
	String text;
	String id;
	Statement stmt;
	actin(Statement stmt, String id, String text){
		this.text = text;
		this.stmt = stmt;
		this.id = id;
	}
	void burn() {
		try {
			stmt.executeUpdate(text);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("burn오류");
		}
	}
}
