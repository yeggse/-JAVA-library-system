package project;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.*;

import com.mysql.cj.protocol.Resultset;

import projectDialogs.SeattingEndDialog;


public class Seatting extends JFrame {
	Statement stmt = null;
	static String id;
	public Seatting(Statement stmt, String id){
		this.stmt = stmt;
		this.id = id;
		setSize(600,500);
		this.setResizable(false);
		setTitle("(두면 도서관)열람실 좌석 예약하기");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(null);
		c.setBackground(Color.white);
		
		// 타이틀 만들기
		ImageIcon logoIcon = new ImageIcon("image/study.png");
		Image img = logoIcon.getImage();	// 아이콘 크기 수정을 위해 필요한 과정
		Image imgfin = img.getScaledInstance(60, 60, java.awt.Image.SCALE_SMOOTH);
		ImageIcon logoIconfin = new ImageIcon(imgfin);
		JLabel title = new JLabel("열람실 좌석 예약", logoIconfin, SwingConstants.CENTER);
		title.setLocation(100,20);
		title.setSize(380,80);
		title.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 30));
		c.add(title);
				
		// 중간 설명 작성하기
		JLabel explain = new JLabel("<html> 원하시는 좌석을 예약하실 수 있습니다.<br>"
				+ "현재 다른 회원이 이용중인 좌석은 예약이 불가능합니다. </html>");
		explain.setFont(new Font("본고딕 KR", Font.BOLD, 13));
		explain.setLocation(140,50);
		explain.setSize(400,120);
		c.add(explain);	
		
		// 좌석 안내 패널
		JPanel panel = new JPanel();
		panel.setBackground(new Color(243,239,227));
		panel.setLocation(15,145);
		panel.setSize(555,270);
		panel.setLayout(null);
		c.add(panel);
		
		myPanel panel2 = new myPanel(10,10);
		panel.add(panel2);
		
		// 부속물 패널
		subPanel ent = new subPanel(450,5,90,30,"출입구");
		panel.add(ent);
		
		subPanel bar = new subPanel(420,50,120,140,"책장");
		panel.add(bar);
		
		subPanel bar2 = new subPanel(420,200,120,60,"책장");
		panel.add(bar2);
		
		// 좌석 버튼
		myButton sBtn = null;
		try {
			ResultSet srs = stmt.executeQuery("select count(*) from seat;");
			String cnt = null;
			
			while(srs.next()) {
				cnt = srs.getString(1);
			}
			
			int trun = Integer.parseInt(cnt);
			
			for(int i=1; i<trun+1; i++) {
				sBtn = new myButton(stmt, id, i);
				
				sBtn.setText(i+"");
				sBtn.setSize(100,60);
				String a = sBtn.getText();

				sBtn.addActionListener(new addEvent(stmt, id, a, sBtn));
				panel2.add(sBtn);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			System.out.println("버튼 생성 오류");
		}
		
		// 좌석 반환 버튼
		JButton endBtn = new JButton("이용종료");
		endBtn.setSize(85,25);
		endBtn.setLocation(490,425);
		endBtn.setBackground(new Color(255,215,0));
		endBtn.setForeground(Color.darkGray);
		endBtn.setFont(new Font("휴먼모음T", Font.BOLD, 15));
		c.add(endBtn);
		
		projectDialogs.SeattingEndDialog end = new SeattingEndDialog(this, stmt, id);
		endBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ResultSet srs;
				try {
					srs = stmt.executeQuery("select * from seat where id = '"+id+"';");
					if(srs.next()) {
						setVisible(false);
						end.setVisible(true);
					} else {
						JOptionPane.showMessageDialog(null, "이용 중인 좌석이 없습니다.", "좌석 미예약",JOptionPane.ERROR_MESSAGE);
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					System.out.println("이용완료 버튼 이벤트 오류");
				}		
			}
		});
		
		// 뒤로 가기
		BackBTN back = new BackBTN(id, stmt, this);
		c.add(back);
		
		setVisible(true);
	}

}

class myPanel extends JPanel{
	myPanel(int x, int y){
		setBackground(new Color(245,245,220));
		setSize(390,250); //390,80
		setLocation(x,y);
		setLayout(new GridLayout(8,3,10,10));
	}
}

class subPanel extends JPanel{
	subPanel(int x, int y, int w, int h, String s){
		setBackground(new Color(229,208,178));
		setSize(w,h);
		setLocation(x,y);
		setLayout(new FlowLayout());
		
		
		JLabel label = new JLabel(s);
		label.setForeground(new Color(106,87,80));
		label.setFont(new Font("굴림", Font.BOLD, 18));
		this.add(label);
	}
}

class myButton extends JButton{
	Statement stmt;
	String id;
	int i;
	myButton(Statement stmt, String id, int i){
		this.id = id;
		this.stmt = stmt;
		this.i = i;
		
		setForeground(Color.white);
		setFont(new Font("휴먼엑스포", Font.BOLD, 15));
		
		ResultSet bu;
		try {
			bu = stmt.executeQuery("select * from seat where seat_no = '"+i+"';");
			while(bu.next()) {
				if("O".equals(bu.getString("seat_lent"))) {
					setBackground(Color.GRAY);
				} else {
					setBackground(new Color(147,112,219));
				}	
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("좌석 mybutton 오류");
		}
		
	}
}

