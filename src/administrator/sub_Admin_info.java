package administrator;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class sub_Admin_info extends JPanel{
	static String id;
	static Statement stmt = null;
	// JTable
	Object ob[][] = new Object[0][4]; //데이터 표시에 열만 나오게 설정
	DefaultTableModel model;  // 데이터 저장 부분
	JTable table;
	JScrollPane js;
	String str[] = {"번호", "제목", "내용", "작성자"}; // 컬럼 명
	sub_Admin_info(Statement stmt, String id){
		this.stmt = stmt;
		this.id = id;
		setSize(600,500);
		setBackground(new Color(191,213,232));
		setLayout(null);
		
		// 타이틀 만들기
		ImageIcon logoIcon = new ImageIcon("image/board.png");
		Image img = logoIcon.getImage();	// 아이콘 크기 수정을 위해 필요한 과정
		Image imgfin = img.getScaledInstance(60, 60, java.awt.Image.SCALE_SMOOTH);
		ImageIcon liIconfin = new ImageIcon(imgfin);
		JLabel title = new JLabel("공지사항 관리 페이지", liIconfin, SwingConstants.CENTER);
		title.setLocation(65,20);
		title.setSize(450,80);
		title.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 30));
		add(title);
		
		// 결과 도출 패널
		JPanel resultPanel = new JPanel();
		resultPanel.setSize(488,280);
		resultPanel.setLocation(45,108);
		resultPanel.setBackground(new Color(94,126,155));
		resultPanel.setLayout(null);
		add(resultPanel);
		
		// 결과 도출 그래프
		model = new DefaultTableModel(ob,str);	// 데이터 저장[][], 컬럼 명
		table = new JTable(model);
		js = new JScrollPane(table);
		js.setLocation(13,10);
		js.setSize(460,260);
		resultPanel.add(js);
		
		try {
			// 공지 출력
			ResultSet srs = stmt.executeQuery("select * from info ;");
			System.out.println("select * from info ;");
			
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
		
		// 클릭하기 이벤트 생성
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				int row = table.getSelectedRow(); 	// 선택한 셀 번호 계산하기
				TableModel data = table.getModel();	// 테이블의 모델 객체 가져오기
				
				String infono = (String)data.getValueAt(row, 0);	//선택한 테이블 row값을 이용해서 memberDTo 객체 생성
				System.out.println(infono);
				try {
					ResultSet srs = stmt.executeQuery("select * from info where no ='"+infono+"';");
					if(srs.next()) {
						//다이얼로그 열기
						String option[] = {"수정하기", "상세보기"};
						int answer = JOptionPane.showOptionDialog(null,"해당 공지에 대한 활동을 선택해 주세요.","선택하세요.", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,null,option, option[0]);
						if(answer==JOptionPane.YES_OPTION){  //사용자가 yes를 눌렀을 경우, 수정 오픈
							s_i_edi_table iedi = new s_i_edi_table(stmt, id, infono);
							iedi.setVisible(true);
						} else if(answer==JOptionPane.NO_OPTION){  //사용자가 no 값을 눌렀을 경우, 상세보기 오픈
							s_i_detail_table idet = new s_i_detail_table(stmt, id, infono);
							idet.setVisible(true);
						}
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					System.out.println("info 테이블 클릭 이벤트 오류");
				}
			}
		});
		
		
		// 추가 버튼
		btnDesig add = new btnDesig("추가", 95);
		add(add);
		s_i_add aa = new s_i_add(stmt, id);
		add.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				aa.setVisible(true);
			}
		});
		
		// 수정 버튼
		btnDesig edi = new btnDesig("수정", 250);
		add(edi);
		s_i_edi ee = new s_i_edi(stmt, id);
		edi.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ee.setVisible(true);
			}
		});
		
		// 삭제 버튼
		btnDesig del = new btnDesig("삭제", 400);
		add(del);
		s_i_del dd = new s_i_del(stmt, id);
		del.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dd.setVisible(true);
			}
		});

		
		setVisible(true);
	}

}

class btnDesig extends JButton{
	btnDesig(String name, int x){
		this.setText(name);
		this.setBackground(new Color(117,137,191));
		this.setForeground(Color.white);
		this.setSize(100,35);
		this.setLocation(x, 405);
		this.setFont(new Font("맑은 고딕", Font.BOLD, 18));
	}
}