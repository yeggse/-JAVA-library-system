package projectDialogs;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class LentDialog extends JDialog {
	String info=null;
	static String id;
	String bookno;
//	Connection conn;
	Statement stmt = null;
//	Statement stmt1 = null;
//	Statement stmt2 = null;
//	Statement stmt3 = null;
	
	 public LentDialog(Statement stmt, String id, String bookno){
		this.id = id;
		this.stmt = stmt;
		this.bookno = bookno;
		setSize(350,400);
		this.setResizable(false);
		setTitle("도서 대여하기");
		Container c = getContentPane();
		c.setLayout(null);
		c.setBackground(new Color(224,238,238));
		
		//타이틀 
		JLabel title = new JLabel("도서 대여 정보 입력");
		title.setSize(350,30);
		title.setLocation(50,30);
		title.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 23));
		c.add(title);
		
		//대여 도서 안내 라벨
		JLabel bookLabel = new JLabel("선택 도서 : ");
		bookLabel.setLocation(25,60);
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
		
		//이름 라벨
		JLabel nameLabel = new JLabel("이름");
		nameLabel.setLocation(45,100);
		nameLabel.setSize(80,40);
		nameLabel.setFont(new Font("함초롱돋움", Font.BOLD, 15));
		c.add(nameLabel);
			
		JLabel idLabel = new JLabel("ID");
		idLabel.setLocation(45,178);
		idLabel.setSize(80,35);
		idLabel.setFont(new Font("함초롱돋움", Font.BOLD, 15));
		c.add(idLabel);
			
		DateCal today = new DateCal();
		String daynow = today.TotoDay(info);
		
		JLabel reLabel = new JLabel("오늘 날짜 : "+daynow);
		reLabel.setLocation(45,240);
		reLabel.setSize(200,40);
		reLabel.setFont(new Font("함초롱돋움", Font.BOLD, 15));
		c.add(reLabel);
		
		DateCal reday = new DateCal();
		String dayreturn = reday.ReturnDay(info);
		
		JLabel reinfoLabel = new JLabel("도서 반납일은 "+dayreturn+" 입니다.");
		reinfoLabel.setLocation(55,270);
		reinfoLabel.setSize(300,40);
		reinfoLabel.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 13));
		reinfoLabel.setOpaque(true);
		reinfoLabel.setForeground(new Color(255,127,80));
		reinfoLabel.setBackground(new Color(224,238,238));
		c.add(reinfoLabel);
			
		// 이름 입력란
		JTextField nameTXT = new JTextField(10);
		nameTXT.setLocation(70,130);
		nameTXT.setSize(200,35);
		c.add(nameTXT);
			
		// ID 입력란
		JTextField idTXT = new JTextField(10);
		idTXT.setLocation(70,203);
		idTXT.setSize(200,35);
		c.add(idTXT);
			
		// 대여 확정 버튼
		JButton okBtn = new JButton("대여 확정");
		okBtn.setLocation(110,310);
		okBtn.setSize(100,30);
		okBtn.setFont(new Font("함초롱돋움", Font.BOLD, 15));
		okBtn.setForeground(Color.white);
		okBtn.setBackground(new Color(131,139,139));
		c.add(okBtn);
		
		okBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ResultSet srs;
				ResultSet lsrs;
				try {
					srs = stmt.executeQuery("select * from people where id = '"+id+"';");
					if(srs.next()) {
						if(idTXT.getText().equals("")||nameTXT.getText().equals("")) {
							JOptionPane.showMessageDialog(null, "모든 정보를 입력해 주세요.", "정보 기재 누락", JOptionPane.WARNING_MESSAGE);
						} else if(!id.equals(idTXT.getText())||!nameTXT.getText().equals(srs.getString("name"))) {
							JOptionPane.showMessageDialog(null, "올바른 정보를 입력해 주세요. \n입력한 값을 확인하시기 바랍니다.", "기재 오류", JOptionPane.WARNING_MESSAGE);
						} else {
							stmt.executeUpdate("update book set id = '"+id+"', backdate = '"+dayreturn+"', lentdate = '"+daynow+"', book_pas = 'X' where book_no = '"+bookno+"';");
							System.out.println("update book set id = '"+id+"', backdate = '"+dayreturn+"', lentdate = '"+daynow+"', book_pas = 'X' where book_no = '"+bookno+"';");
							JOptionPane.showMessageDialog(null, "대여가 완료되었습니다. \n반납일 내 도서 반납을 완료해 주세요.", "대여 완료", JOptionPane.INFORMATION_MESSAGE);
							setVisible(false);
						}
					}

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					System.out.println("도서 대여 확정 버튼 오류");
				}
			}
		});
		
		setVisible(false);
	 }
}

class DateCal {
	String ReturnDay(String txt) {
    	Date now = new Date();
    			
    	Calendar cal = Calendar.getInstance(); 
    	cal.setTime(now);
    		
    	cal.add(Calendar.DATE, 14); 
    	DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    	String strDate = df.format(cal.getTime());
    	txt = strDate;
    	return txt;
    	}
	
	String TotoDay(String text) {
		Date now = new Date();
		
    	Calendar cal = Calendar.getInstance(); 
    	cal.setTime(now);
    		
    	int year = cal.get(Calendar.YEAR);
    	int month = cal.get(Calendar.MONTH) + 1; //0부터 시작하기 때문에 1더해준다
    	int day = cal.get(Calendar.DAY_OF_MONTH);
    	String today = year + "년 " + month + "월 " + day + "일 ";	
    	text=today;
    	return text;
	}
}