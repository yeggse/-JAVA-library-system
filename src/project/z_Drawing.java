package project;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class z_Drawing {}

// Main, intro 에서 사용
// 가운데 정렬 하느방법..? (joinguide.setHorizontalAlignment(SwingConstants.CENTER); 아닌가)
class outtroLabel extends JLabel{
	outtroLabel(int x, int y){
		setText("<html>인천광역시 계양구 부평구"
				+ " <br>tel. 82+032-123-4567        fax. 32-123-4568 </html>");
		setFont(new Font("본고딕 KR", Font.BOLD, 11));
		setOpaque(true);
		setForeground(new Color(41,90,221));
		setSize(200,30);
		setLocation(x, y);
	}
}


// 전체에서 사용됨(뒤로가기 버튼)
class BackBTN extends JButton{
	String name;
//	Statemet stmt;
	BackBTN(Statement stmt,JFrame jframe){
		setText("뒤로가기");
		setSize(80,25);
		setLocation(15,10);
		setBackground(new Color(28,188,156));
		setForeground(Color.white);
		setFont(new Font("휴먼모음T", Font.BOLD, 13));
				
		addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Main main = new Main(stmt, name);
				jframe.setVisible(false);
			}
		});
	}
}

//Main에서 사용
//로그아웃
class logout extends JButton{
	logout(JFrame jframe){
		setText("로그아웃");
		setSize(80,25);
		setLocation(480,10);
		setBackground(new Color(210,218,255));
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

//좌석 이벤트
class addEvent implements ActionListener{
	String id;
	String a;
	Statement stmt;
	JButton sBtn;
	addEvent(Statement stmt, String id, String a, JButton sBtn){
		this.id = id;
		this.stmt = stmt;
		this.a = a;
		this.sBtn = sBtn;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		try {
			ResultSet csrs = stmt.executeQuery("select * from seat where id = '"+id+"';");
			System.out.println("select * from seat where id = '"+id+"';");
			if(csrs.next()) {
				JOptionPane.showMessageDialog(null, "사용중인 좌석이 있습니다. \n이용 완료 후 좌석을 예약해 주세요.", "중복 예약", JOptionPane.WARNING_MESSAGE);
			} else {
				ResultSet srs = stmt.executeQuery("select * from seat where seat_no = '"+a+"';");
				System.out.println("select * from seat where seat_no = '"+a+"';");
				if(srs.next()) {
					if("O".equals(srs.getString("seat_lent"))) {
						JOptionPane.showMessageDialog(null, "이미 사용중인 좌석입니다. \n 다른 좌석을 선택하세요.", "실패", JOptionPane.WARNING_MESSAGE);
					} else {
						stmt.executeUpdate("update seat set seat_lent = 'O', id ='"+id+"' where seat_no = '"+a+"';");
						JOptionPane.showMessageDialog(null, "좌석 예약을 완료했습니다.", "예약 완료", JOptionPane.PLAIN_MESSAGE);
					}
				}	
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			System.out.println("좌석 이용 버튼 에러");
		}
		
	}
	
}