package project;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

public class Main extends JFrame{
	static String id;
	Statement stmt = null;
	boolean lent=true;
	
	public Main(Statement stmt, String id){
		this.stmt = stmt;
		this.id = id;
		setSize(600,500);
		this.setResizable(false);
		setTitle("(두면 도서관)두면 도서관에 오신 것을 환영합니다.");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(null);
		c.setBackground(new Color(248,248,255));
		
		// 타이틀 만들기
		ImageIcon logoIcon = new ImageIcon("image/edu.png");
		Image img = logoIcon.getImage();	// 아이콘 크기 수정을 위해 필요한 과정
		Image imgfin = img.getScaledInstance(60, 60, java.awt.Image.SCALE_SMOOTH);
		ImageIcon liIconfin = new ImageIcon(imgfin);
		JLabel title = new JLabel("두면 도서관", liIconfin, SwingConstants.CENTER);
		title.setLocation(150,20);
		title.setSize(250,80);
		title.setFont(new Font("휴먼둥근헤드라인", Font.BOLD, 30));
		c.add(title);
				
		// 중간 설명 작성하기
		JLabel explain = new JLabel("<html>두면 도서관 온라인 회원들을 위한 서비스입니다.<br>"
				+ "원하시는 도서관 시스템을 선택해 주시기 바랍니다. 시민 참여 프로그램 신청은 페이지 하단에"
				+ " 기재된 번호로 유선 연락 부탁드립니다.</html>");
		explain.setFont(new Font("본고딕 KR", Font.BOLD, 12));
		explain.setLocation(100,75);
		explain.setSize(400,120);
		c.add(explain);
		
		// 도서 반납 안내 다이얼로그 출력 -> 반납 안하면 대여버튼 외 도서관 시스템 이용 불가.
		// 오늘 날짜
		Date now = new Date();
		
    	Calendar cal = Calendar.getInstance(); 
    	cal.setTime(now);
    	DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    	String today = df.format(cal.getTime());
		System.out.println(today);
		
		// 반납 일자(출력하기)
		try {
			ResultSet srs = stmt.executeQuery("select * from book where id = '"+id+"';");
			System.out.println("select * from book where id = '"+id+"';");
			
			if(srs.next()) {	//반납도서 존재할 경우
				String getday = srs.getString("backdate");
				String fin = getday.substring(0,4)+"-"+getday.substring(6,8)+"-"+getday.substring(10,12);
				try {
					Date toDate = df.parse(today);	//String 타입을 Date 타입으로 변환
					Date endDate = df.parse(fin);
					
					// 시간 차이를 시간,분,초를 곱한 값으로 나누면 하루 단위가 나옴
					long gap = endDate.getTime() - toDate.getTime();
					long gapDays = gap / (24 * 60 * 60 * 1000);

					System.out.println("날짜차이=" + gapDays);
					
					if(0 <= gapDays && gapDays <= 3) {
						System.out.println("3일 미만");
						JOptionPane.showMessageDialog(null, "현재 대여 중인 도서의 반납 일자가 3일 미만 남았습니다.\n 기간 내 반납이 필요합니다.", "반납하세요!", JOptionPane.PLAIN_MESSAGE);
					} else if(gapDays < 0){
						JOptionPane.showMessageDialog(null, "현재 대여 중인 도서의 반납 일자가 초과되었습니다.\n 반납이 필요합니다.", "도서 기간 초과", JOptionPane.ERROR_MESSAGE);
						lent = false;
					} else {
						System.out.println("도서 반납 기간 여유");
					}
					System.out.println(lent);
				} catch (ParseException e) {
					e.printStackTrace();
					System.out.println("날짜 계산 오류");
				}
			} else {System.out.println("도서 미대여 중");}
			
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
			System.out.println("도서 반납 일자 출력 오류");
		}
		
		
		// 버튼 라벨 설명
		myLabel searchEx = new myLabel("도서 정보 검색");
		myLabel recomEx = new myLabel("이 달의 도서 추천");
		myLabel lentEx = new myLabel("도서 대여 하기");
		myLabel memoEx = new myLabel("메   모   장");
		myLabel myinfoEx = new myLabel("회원 정보 수정");
		myLabel seatEx = new myLabel("독서실 자리 예약");
		
		
		searchEx.setLocation(40,230);
		recomEx.setLocation(172,230);
		lentEx.setLocation(317,230);
		memoEx.setLocation(53,325);
		myinfoEx.setLocation(180,325);
		seatEx.setLocation(310,325);
		
		c.add(searchEx);
		c.add(recomEx);
		c.add(lentEx);
		c.add(memoEx);
		c.add(myinfoEx);
		c.add(seatEx);
		
		// 도서 정보 검색 버튼
		JButton searchBtn = new JButton("Search");
		searchBtn.setSize(90,40);
		searchBtn.setLocation(40,190);
		c.add(searchBtn);		
		searchBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
			BookSearch booksearch =	new BookSearch(stmt, id);	
			setVisible(false);
			}
		});
		
		// 이달의 추천도서 버튼
		JButton recoBtn = new JButton("Hot");
		recoBtn.setSize(90,40);
		recoBtn.setLocation(180,190);
		c.add(recoBtn);	
		
		recoBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println(lent);
				if(lent == false) {
					JOptionPane.showMessageDialog(null, "도서 반납 후 이용 가능합니다.","도서 미반납 연체", JOptionPane.ERROR_MESSAGE);
				} else {
					Recommand Recommand =new Recommand(stmt, id);	
					setVisible(false);
				}
			}
		});
		
		// 도서 대여 버튼
		JButton reserveBtn = new JButton("Lent");
		reserveBtn.setSize(90,40);
		reserveBtn.setLocation(315,190);
		c.add(reserveBtn);
		
		reserveBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					ResultSet srs = stmt.executeQuery("select * from people where id = '"+id+"';");
					if(srs.next()) {
						if("O".equals(srs.getString("royal"))) {
							BookLent lent = new BookLent(stmt, id);
							setVisible(false);
						}else {
							JOptionPane.showMessageDialog(null, "유료회원만 이용 가능합니다.","유료회원 가능 코너", JOptionPane.ERROR_MESSAGE);
						}
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					System.out.println("도서예약 버튼: 유료회원 확인 오류");
				}
			}
		});
		
		// 메모시스템 버튼
		JButton MemoBtn = new JButton("Memo");
		MemoBtn.setSize(90,40);
		MemoBtn.setLocation(40,290);
		c.add(MemoBtn);	
		
		MemoBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(lent == false) {
					JOptionPane.showMessageDialog(null, "도서 반납 후 이용 가능합니다.","도서 미반납 연체", JOptionPane.ERROR_MESSAGE);
				} else {
					Memo memo = new Memo(stmt, id);
				setVisible(false);
				}
			}
		});
		
		// 회원정보 수정 버튼
		JButton InfoBtn = new JButton("My Info");
		InfoBtn.setSize(90,40);
		InfoBtn.setLocation(180,290);
		c.add(InfoBtn);	
		
		InfoBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				myInfo info = new myInfo(stmt, id);
				setVisible(false);
			}
		});
		
		// 독서실 자리예약 버튼
		JButton SeatBtn = new JButton("Seat");
		SeatBtn.setSize(90,40);
		SeatBtn.setLocation(315,290);
		c.add(SeatBtn);	
		
		SeatBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println(lent);
				if(lent == false) {
					JOptionPane.showMessageDialog(null, "도서 반납 후 이용 가능합니다.","도서 미반납 연체", JOptionPane.ERROR_MESSAGE);
					System.out.println("예약버튼 연체");
				} else {
				Seatting seatarea = new Seatting(stmt, id);
				setVisible(false);
				}
			}
		});
		
		// 공지사항
		myLabel infoEx = new myLabel("공지사항");
		infoEx.setLocation(468,230);
		c.add(infoEx);
		
		//공지사항 버튼
		JButton infoBtn = new JButton("Info");
		infoBtn.setSize(90,40);
		infoBtn.setLocation(450,190);
		c.add(infoBtn);	
		
		infoBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println(lent);
				if(lent == false) {
					JOptionPane.showMessageDialog(null, "도서 반납 후 이용 가능합니다.","도서 미반납 연체", JOptionPane.ERROR_MESSAGE);
					System.out.println("예약버튼 연체");
				} else {
				Information information = new Information(stmt, id);
				setVisible(false);
				}
			}
		});
		
		// 도서 신청
		myLabel askEx = new myLabel("도서신청");
		askEx.setLocation(468,325);
		c.add(askEx);
		
		//도서신청 버튼
		JButton askBtn = new JButton("Ask");
		askBtn.setSize(90,40);
		askBtn.setLocation(450,290);
		c.add(askBtn);	
		
		askBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println(lent);
				if(lent == false) {
					JOptionPane.showMessageDialog(null, "도서 반납 후 이용 가능합니다.","도서 미반납 연체", JOptionPane.ERROR_MESSAGE);
					System.out.println("예약버튼 연체");
				} else {
				Ask ask = new Ask(stmt, id);
				setVisible(false);
				}
			}
		});
		
		//로그아웃
		logout lg = new logout(this);
		c.add(lg);
		
		// 하단 정보 기재란 
		outtroLabel outline = new outtroLabel(180,405);
		outline.setBackground(new Color(248,248,255));
		c.add(outline);
		
		setVisible(true);
	}
}

class myLabel extends JLabel{
	myLabel(String name){
		super(name);
//		this.setText(name);
		this.setSize(140,40);
		this.setFont(new Font("본고딕", Font.BOLD, 13));
	}
}