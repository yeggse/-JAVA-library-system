package project;

import java.awt.Color;
import java.awt.Container;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Ask extends JFrame {
	static String id;
	static Statement stmt = null;
	Ask(Statement stmt, String id){
		this.stmt = stmt;
		this.id = id;
		setSize(400,600);
		this.setResizable(false);
		setTitle("도서 신청");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(null);
		c.setBackground(new Color(248,248,255));
		
		// 타이틀 만들기
		ImageIcon logoIcon = new ImageIcon("image/edu.png");
		Image img = logoIcon.getImage();	// 아이콘 크기 수정을 위해 필요한 과정
		Image imgfin = img.getScaledInstance(60, 60, java.awt.Image.SCALE_SMOOTH);
		ImageIcon liIconfin = new ImageIcon(imgfin);
		JLabel title = new JLabel("도서 신청", liIconfin, SwingConstants.CENTER);
		title.setLocation(80,20);
		title.setSize(250,80);
		title.setFont(new Font("휴먼둥근헤드라인", Font.BOLD, 30));
		c.add(title);
		
		// 원하시는 도서를 입력해 주세요.
		
		// 도서명
		JLabel book = new JLabel("도서명");
		book.setSize(150,35);
		book.setLocation(40,110);
		c.add(book);
		
		JTextField btxt = new JTextField(10);
		btxt.setSize(155,35);
		btxt.setLocation(100,115);
		c.add(btxt);
		
		// 도서 출판사
		JLabel pub = new JLabel("출판사");
		pub.setSize(150,35);
		pub.setLocation(40,160);
		c.add(pub);
		
		JTextField ptxt = new JTextField(10);
		ptxt.setSize(155,35);
		ptxt.setLocation(100,165);
		c.add(ptxt);
		
		// 저자
		JLabel aut = new JLabel("저자");
		aut.setSize(150,35);
		aut.setLocation(40,210);
		c.add(aut);
		
		JTextField atxt = new JTextField(10);
		atxt.setSize(155,35);
		atxt.setLocation(100,215);
		c.add(atxt);
		
		// 신청사유
		JLabel rea = new JLabel("신청 이유");
		rea.setSize(150,35);
		rea.setLocation(40,260);
		c.add(rea);
		
		JTextField rtxt = new JTextField(100);
		rtxt.setSize(205,35);
		rtxt.setLocation(100,265);
		c.add(rtxt);
		
		// 장르
		JLabel gan = new JLabel("도서 장르");
		gan.setSize(150,35);
		gan.setLocation(40,310);
		c.add(gan);
		
		JCheckBox nov = new JCheckBox("소설");
		nov.setLocation(100,315);
		nov.setSize(90,35);
		c.add(nov);
		
		JCheckBox ki = new JCheckBox("아동");
		ki.setLocation(190,315);
		ki.setSize(70,35);
		c.add(ki);
		
		JCheckBox mo = new JCheckBox("재테크");
		mo.setLocation(260,315);
		mo.setSize(70,35);
		c.add(mo);
		
		JCheckBox ch = new JCheckBox("자기계발");
		ch.setLocation(100,350);
		ch.setSize(90,35);
		c.add(ch);
		
		JCheckBox po = new JCheckBox("시/수필");
		po.setLocation(190,350);
		po.setSize(70,35);
		c.add(po);
		
		JCheckBox nf = new JCheckBox("비문학");
		nf.setLocation(260,350);
		nf.setSize(70,35);
		c.add(nf);
		
		// 체크박스 DB에도 넣기!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		
////		 확인 이벤트 생성하기
//		JButton yes = new JButton("확인");
//		yes.setSize(70,35);
//		yes.setLocation(160,405);
//		c.add(yes);
//		yes.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//				// 번호 자동으로 생성해서 부여하기 : cnt
//				// 체크박스 확인하기!!!!!!!!!!!!!!!!!!
//				
//				if(btxt.getText().equals("") || ptxt.getText().equals("") || atxt.getText().equals("") || rtxt.getText().equals("") || 
//						(!ra.isSelected()&&!ki.isSelected()&& !mo.isSelected()&&!ch.isSelected()&&!po.isSelected())) {
//					JOptionPane.showMessageDialog(null, "모든 항목을 입력해 주세요.","미기재 항목",JOptionPane.PLAIN_MESSAGE);
//				} else {
//					stmt.executeQuery("INSERT INTO ask(a_no, a_title, a_publish, a_author, a_reason, a_id) "
//							+ "VALUES('"+cnt+"','"+btxt.getText()+"','"+ptxt.getText()+"','"+atxt.getText()+"','"+rtxt.getText()+"','"+id+"');");
//					JOptionPane.showMessageDialog(null, "도서 신청을 완료했습니다.","신청완료",JOptionPane.PLAIN_MESSAGE);
//				}
//				
//			}
//		});
		
		
		// 하단 광고 패널
		JPanel adv = new JPanel();
		adv.setLocation(0,465);
		adv.setSize(400,100);
		c.add(adv);
		
		ImgThread it = new ImgThread(adv);
		it.start();
		
		setVisible(true);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Ask(stmt, id);
	}
}

class ImgThread extends Thread{
	JPanel adv;
	int r;
	String url;
	ImgThread(JPanel adv){
		this.adv = adv;
		
		// 광고패널 이벤트
		adv.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				System.out.println(r);
				switch(r) {
				case 0: url = "https://www.naver.com/"; break;
				case 1: url = "https://www.daum.net/"; break;
				case 2: url = "https://www.nate.com/"; break;
				case 3: url = "https://www.google.com/"; break;
				case 4: url = "https://www.bing.com/"; break;
				}
				try {
					Desktop.getDesktop().browse(new URI(url));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (URISyntaxException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	}
	
	public void run() {
		while(true) {
			if(r>4) {
				r=0;
			} 
			
			JLabel lbe = new JLabel();
			ImageIcon iconr = putIcon(r);
			Image img = iconr.getImage();
			Image imgfin = img.getScaledInstance(390, 100, java.awt.Image.SCALE_SMOOTH);
			ImageIcon icon = new ImageIcon(imgfin);
			
			lbe.setIcon(icon);
			lbe.setSize(400,100);
			adv.add(lbe, 0);	// 컴포넌트의 위치를 설정할 수 있음 : 0번째 인덱스에 새롭게 넣어준다는 의미. or boolean flg = true를 선언하고 if문으로 할 수도 있음(하단)
			adv.repaint();

			
			try {
				sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return;
			}
			
			r++;
		}
	}
	
	ImageIcon putIcon(int r) {
		ImageIcon icon = null;
		switch(r) {
		case 0: icon = new ImageIcon("image/ad1.jpg"); break;
		case 1: icon = new ImageIcon("image/ad2.jpg"); break;
		case 2: icon = new ImageIcon("image/ad3.jpg"); break;
		case 3: icon = new ImageIcon("image/ad4.png"); break;
		case 4: icon = new ImageIcon("image/ad5.jpg"); break;
		}
		return icon;
	}
}


//if(!flg)
//	adv.remove(0);	// 배너가 중첩되어서 만들어지지 않도록 하는 것.
//else 
//	flg = !flg;
