package administrator;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class s_a_detail extends JDialog {
	static Statement stmt;
	static String id;
	static String asknum;
	String btxt;
	String ptxt;
	String atxt;
	String rtxt;
	String mtxt;
	
	s_a_detail(Statement stmt, String id, String asknum){
		this.stmt = stmt;
		this.id = id;
		this.asknum = asknum;
		setSize(400,450);
		this.setResizable(false);
		setTitle("공지 상세");
		Container c = getContentPane();
		c.setLayout(null);
		c.setBackground(new Color(245,239,230));
		
		// 출력 내용 확정
		try {
			ResultSet srs = stmt.executeQuery("select * from ask where a_no = '"+asknum+"';");
			if(srs.next()) {
				btxt = srs.getString("a_title");
				ptxt = srs.getString("a_publish");
				atxt = srs.getString("a_author");
				rtxt = srs.getString("a_reason");
				mtxt = srs.getString("a_id");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("도서신청 내용 출력 오류");
		}
		
		//타이틀 
		JLabel title = new JLabel("신청 도서 상세 확인");
		title.setSize(350,30);
		title.setLocation(90,30);
		title.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 23));
		c.add(title);
		
		//제목 라벨
		myla sub = new myla("도서명 :");
		sub.setLocation(55,80);
		c.add(sub);
		
		myla pu = new myla("출판사 :");
		pu.setLocation(55,125);
		c.add(pu);
		
		myla au = new myla("작 가 :");
		au.setLocation(55,170);
		c.add(au);
		
		myla re = new myla("이 유 :");
		re.setLocation(55,215);
		c.add(re);
		
		myla man = new myla("신청자 :");
		man.setLocation(55,260);
		c.add(man);
		
		myla ga = new myla("장 르 :");
		ga.setLocation(55,305);
		c.add(ga);
		
		// 내용 라벨
		prin book = new prin(80);
		book.setText(btxt);
		c.add(book);
		
		prin pus = new prin(125);
		pus.setText(ptxt);
		c.add(pus);
		
		prin aus = new prin(170);
		aus.setText(atxt);
		c.add(aus);
		
		prin res = new prin(215);
		res.setText(rtxt);
		c.add(res);
		
		prin mans = new prin(260);
		mans.setText(mtxt);
		c.add(mans);
				
		// 장르 출력
		Vector<String> arr = new Vector<String>();
		String ar[] = null;
		String gtext = null;
		try {
			ResultSet srs = stmt.executeQuery("select * from ask where a_no = '"+asknum+"';");
			if(srs.next()) {
				if(srs.getString("nov").equals("O")) {
					arr.add("소설");
				}
				if(srs.getString("ki").equals("O")) {
					arr.add("아동");
				}
				if(srs.getString("mo").equals("O")) {
					arr.add("재테크");				}
				if(srs.getString("ch").equals("O")) {
					arr.add("자기계발");
				}
				if(srs.getString("po").equals("O")) {
					arr.add("시/수필");
				}
				if(srs.getString("nf").equals("O")) {
					arr.add("비문학");
				}
				ar = arr.toArray(new String[arr.size()]);
				gtext = Arrays.toString(ar);
				System.out.println(gtext);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			System.out.println("배열오류");
		}
	
		prin gans = new prin(305);
		gans.setText(gtext);
		c.add(gans);
		
		
		JButton okBtn = new JButton("확인");
		okBtn.setLocation(140,350);
		okBtn.setSize(100,40);
		okBtn.setFont(new Font("함초롱돋움", Font.BOLD, 15));
		okBtn.setForeground(Color.white);
		okBtn.setBackground(new Color(131,139,139));
		c.add(okBtn);
		okBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setVisible(false);
			}
		});
		
		
		setVisible(true);
	}
}

class myla extends JLabel{
	myla(String name){
		this.setText(name);
		this.setSize(300,40);
		this.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 17));
		this.setOpaque(false);
		this.setForeground(Color.black);
		this.setBackground(Color.cyan);
	}
}

class prin extends JLabel{
	prin(int y){
		this.setSize(300,40);
		this.setFont(new Font("굴림", Font.BOLD, 16));
		this.setLocation(150,y);
	}
}
