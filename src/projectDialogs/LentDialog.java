package projectDialogs;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import project.BookLent;

public class LentDialog extends JDialog {
	String info=null;
	static String id;
	String bookno;
	Statement stmt = null;
	JFrame jframe;
	
	 public LentDialog(JFrame jframe, Statement stmt, String id, String bookno){
//		super(jframe, true);	// 모달다이얼로그 JFrame jframe, 
		this.id = id;
		this.stmt = stmt;
		this.bookno = bookno;
		this.jframe = jframe;
		setSize(400,250);
		this.setResizable(false);
		setTitle("도서 대여하기");
		Container c = getContentPane();
		c.setLayout(null);
		c.setBackground(new Color(224,238,238));
		
		//타이틀 
		JLabel title = new JLabel("도서 대여");
		title.setSize(390,40);
		title.setLocation(140,20);
		title.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 23));
		c.add(title);
		
		//대여 도서 정보 라벨
		JLabel bookLabel = new JLabel("선택 도서 : ");
		bookLabel.setLocation(35,55);
		bookLabel.setSize(300,40);
		bookLabel.setFont(new Font("함초롱돋움", Font.BOLD, 15));
		c.add(bookLabel);
		
		String label="";
		try {
			ResultSet titlesrs = stmt.executeQuery("select * from book where book_no = '"+bookno+"';");
			System.out.println("select * from book where book_no = '"+bookno+"';");
			while(titlesrs.next()) {
				label = titlesrs.getString("book_title");
			}
			bookLabel.setText("선택 도서 : "+label);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("대여도서 안내라벨 오류");
		}
		
		
		//일자 라벨
		DateCal today = new DateCal();
		String daynow = today.TotoDay(info);
		
		JLabel reLabel = new JLabel("오늘 날짜 : "+daynow);
		reLabel.setLocation(35,85);
		reLabel.setSize(200,40);
		reLabel.setFont(new Font("함초롱돋움", Font.BOLD, 15));
		c.add(reLabel);
		
		DateCal reday = new DateCal();
		String dayreturn = reday.ReturnDay(info);
		
		JLabel reinfoLabel = new JLabel("도서 반납일은 "+dayreturn+" 입니다.");
		reinfoLabel.setLocation(85,110);
		reinfoLabel.setSize(300,40);
		reinfoLabel.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 13));
		reinfoLabel.setOpaque(true);
		reinfoLabel.setForeground(new Color(255,127,80));
		reinfoLabel.setBackground(new Color(224,238,238));
		c.add(reinfoLabel);
		
		//확인 라벨
		JLabel ask = new JLabel();
		ask.setText("선택하신 도서를 대여하시겠습니까?");
		ask.setSize(200,40);
		ask.setLocation(70,135);
		c.add(ask);
		
		//취소 버튼
		JButton no = new JButton("대여 취소");
		no.setLocation(80,168);
		no.setSize(100,30);
		no.setFont(new Font("함초롱돋움", Font.BOLD, 15));
		no.setForeground(Color.white);
		no.setBackground(new Color(131,139,139));
		c.add(no);
		
		no.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setVisible(false);
			}
		});
		
		// 대여 확정 버튼
		JButton okBtn = new JButton("대여 확정");
		okBtn.setLocation(200,168);
		okBtn.setSize(100,30);
		okBtn.setFont(new Font("함초롱돋움", Font.BOLD, 15));
		okBtn.setForeground(Color.white);
		okBtn.setBackground(new Color(131,139,139));
		c.add(okBtn);
		
		okBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					ResultSet psrs = stmt.executeQuery("select * from book where id = '"+id+"';");
					if(psrs.next()) {
						JOptionPane.showMessageDialog(null, "이미 대여 중인 도서입니다. \n다른 도서를 대여해 주세요.", "중복 대여", JOptionPane.WARNING_MESSAGE);
						setVisible(false);
					} else {
						stmt.executeUpdate("update book set id = '"+id+"', backdate = '"+dayreturn+"', lentdate = '"+daynow+"', book_pas = 'X' where book_no = '"+bookno+"';");
						System.out.println("update book set id = '"+id+"', backdate = '"+dayreturn+"', lentdate = '"+daynow+"', book_pas = 'X' where book_no = '"+bookno+"';");
						JOptionPane.showMessageDialog(null, "대여가 완료되었습니다. \n반납일 내 도서 반납을 완료해 주세요.", "대여 완료", JOptionPane.INFORMATION_MESSAGE);
						setVisible(false);
						jframe.setVisible(false);
						BookLent lent = new BookLent(stmt, id);
					}
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					System.out.println("도서 대여 확정 버튼 오류");
				}
			}
		});
		setVisible(true);
	 }
}

class DateCal {
	String ReturnDay(String txt) {
//		Date now = new Date();
//		
//    	Calendar cal = Calendar.getInstance(); 
//    	cal.setTime(now);
//    		
//    	cal.add(Calendar.DATE, 14); 
//    	DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//    	String strDate = df.format(cal.getTime());
//    	txt = strDate;
//    	return txt;
		
    	Date now = new Date();
    			
    	Calendar cal = Calendar.getInstance(); 
    	cal.setTime(now);
    		
    	cal.add(Calendar.DATE, 14); 
    	DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    	String strDate = df.format(cal.getTime());
    	txt = strDate;
    	
    	String array[] = strDate.split("-");
    	for(int i=0;i<array.length;i++) {
    		System.out.println(array[i]);
    	}
    	String year = array[0];
    	String month = array[1];
    	String day = array[2];
    	String enddate = year + "년 " + month + "월 " + day + "일";	
    	return enddate;
    	}
	
	String TotoDay(String text) {
		Date now = new Date();
		
    	Calendar cal = Calendar.getInstance(); 
    	cal.setTime(now);
    	
    	DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    	String strDate = df.format(cal.getTime());
    	text = strDate;
    	
    	String array[] = strDate.split("-");
    	for(int i=0;i<array.length;i++) {
    		System.out.println(array[i]);
    	}
    	String year = array[0];
    	String month = array[1];
    	String day = array[2];
    	String enddate = year + "년 " + month + "월 " + day + "일";	
    	return enddate;
//    	int year = cal.get(Calendar.YEAR);
//    	int month = cal.get(Calendar.MONTH) + 1; //0부터 시작하기 때문에 1더해준다
//    	int day = cal.get(Calendar.DAY_OF_MONTH);
//    	String today = year + "년 " + month + "월 " + day + "일";	
//    	text=today;
//    	return text;
	}
}