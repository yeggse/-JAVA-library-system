package projectDialogs;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class LentDialog extends JDialog {
	String info=null;
	
	 public LentDialog(){
		setSize(350,400);
		this.setResizable(false);
		setTitle("회원가입");
		Container c = getContentPane();
		c.setLayout(null);
		c.setBackground(new Color(224,238,238));
			
		JLabel title = new JLabel("도서 대여 정보 입력");
		title.setSize(350,30);
		title.setLocation(50,30);
		title.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 23));
		c.add(title);
			
		JLabel nameLabel = new JLabel("이름");
		nameLabel.setLocation(45,80);
		nameLabel.setSize(80,40);
		nameLabel.setFont(new Font("함초롱돋움", Font.BOLD, 15));
		c.add(nameLabel);
			
		JLabel idLabel = new JLabel("ID");
		idLabel.setLocation(45,160);
		idLabel.setSize(80,40);
		idLabel.setFont(new Font("함초롱돋움", Font.BOLD, 15));
		c.add(idLabel);
			
		
		
		DateCal today = new DateCal();
		String daynow = today.TotoDay(info);
		
		
		JLabel reLabel = new JLabel("오늘 날짜 : "+daynow);
		reLabel.setLocation(45,230);
		reLabel.setSize(200,40);
		reLabel.setFont(new Font("함초롱돋움", Font.BOLD, 15));
		c.add(reLabel);
		
		DateCal returnday = new DateCal();
		String dayreturn = returnday.ReturnDay(info);
		
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
		nameTXT.setLocation(70,115);
		nameTXT.setSize(200,35);
		c.add(nameTXT);
			
		// ID 입력란
		JTextField idTXT = new JTextField(10);
		idTXT.setLocation(70,188);
		idTXT.setSize(200,35);
		c.add(idTXT);
			
		// 대여 확정 버튼
		// 이름 ID 연결하고, 이벤트로 DB에 대여 되었다고 연결하기!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		// 반납일자도!!!!!!!!!!!!!!!!!!!!!!
		JButton okBtn = new JButton("대여");
		okBtn.setLocation(130,310);
		okBtn.setSize(80,30);
		okBtn.setFont(new Font("함초롱돋움", Font.BOLD, 15));
		okBtn.setForeground(Color.white);
		okBtn.setBackground(new Color(131,139,139));
		c.add(okBtn);
			
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