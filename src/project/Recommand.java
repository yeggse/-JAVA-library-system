package project;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.*;
import javax.swing.*;

// 가운데 정렬
public class Recommand extends JFrame{
	Statement stmt = null;
	Recommand(Statement stmt){
		this.stmt = stmt;
		setSize(600,500);
		this.setResizable(false);
		setTitle("(두면 도서관)이달의 도서 추천");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(null);
		c.setBackground(Color.white);
		
		// 타이틀 만들기
		ImageIcon logoIcon = new ImageIcon("image/h.png");
		Image img = logoIcon.getImage();	// 아이콘 크기 수정을 위해 필요한 과정
		Image imgfin = img.getScaledInstance(60, 60, java.awt.Image.SCALE_SMOOTH);
		ImageIcon logoIconfin = new ImageIcon(imgfin);
		JLabel title = new JLabel("이 달의 추천 도서", logoIconfin, SwingConstants.CENTER);
		title.setLocation(110,20);
		title.setSize(380,80);
		title.setFont(new Font("휴먼둥근헤드라인", Font.BOLD, 30));
		c.add(title);
				
		// 중간 설명 작성하기
			// 가운데 정렬.............
		JLabel explain = new JLabel("<html>두면 도서관의 사서 및 회원이 선정한<br> 이달의 추천 도서를 소개합니다.</html>");
		explain.setFont(new Font("본고딕 KR", Font.BOLD, 16));
		explain.setLocation(180,60);
		explain.setSize(400,120);
		c.add(explain);	
		
		// 추천도서 라벨
		ImageIcon recoIcon = new ImageIcon("image/reco.png");
		Image recoimg = recoIcon.getImage();	// 아이콘 크기 수정을 위해 필요한 과정
		Image recoimgfin = recoimg.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
		ImageIcon recoIconfin = new ImageIcon(recoimgfin);
		JLabel recoLabel = new JLabel("추천 도서", recoIconfin, SwingConstants.CENTER);
		recoLabel.setFont(new Font("휴먼모음T", Font.BOLD, 18));
		recoLabel.setLocation(30,120);
		recoLabel.setSize(200,120);
		c.add(recoLabel);	
		
		// 추천 도서 이미지
		myPanel imgPanel = new myPanel();
		imgPanel.setSize(180,220);
		imgPanel.setLocation(120,210);
		c.add(imgPanel);
		
		// 추천 도서 설명
		JLabel bookTitle = new JLabel("<html>만일 내가 인생을 다시 산다면 (10만 부 기념 스페셜 에디션)</html>");
		JLabel bookSubTitle = new JLabel("<html>벌써 마흔이 된 당신에게 해 주고 싶은 말들 42</html>");
		JLabel bookPublish = new JLabel("메이븐 (2022.11.11)");
		JLabel bookAuthor = new JLabel("김혜남 저자(글)");
		JLabel bookDetail = new JLabel("<html>“하나의 문이 닫히면 또 하나의 문이 열린다. "
				+ "그러니 더 이상 고민하지 말고 그냥 재미있게 살아라!”</html>");
		
		bookTitle.setFont(new Font("휴먼편지체", Font.BOLD, 16));
		bookTitle.setLocation(340,185);
		bookTitle.setSize(205,100);
		c.add(bookTitle);
		
		bookSubTitle.setFont(new Font("함초롱돋움", Font.ITALIC, 13));
		bookSubTitle.setLocation(335,226);
		bookSubTitle.setSize(200,100);
		c.add(bookSubTitle);
		
		bookPublish.setFont(new Font("함초롱돋움", Font.PLAIN, 13));
		bookPublish.setLocation(370,260);
		bookPublish.setSize(200,100);
		c.add(bookPublish);
		
		bookAuthor.setFont(new Font("함초롱돋움", Font.PLAIN, 13));
		bookAuthor.setLocation(380,280);
		bookAuthor.setSize(200,100);
		c.add(bookAuthor);
		
		bookDetail.setFont(new Font("함초롱돋움", Font.BOLD, 13));
		bookDetail.setLocation(320,280);
		bookDetail.setSize(245,200);
		c.add(bookDetail);
		
		
		// 뒤로 가기
		BackBTN back = new BackBTN(stmt, this);
		c.add(back);
		
		setVisible(true);
	}
	
	
	class myPanel extends JPanel{
		ImageIcon recoIMGIcon = new ImageIcon("image/book.jpg");
		Image recoIMGimg = recoIMGIcon.getImage();	// 아이콘 크기 수정을 위해 필요한 과정
		Image recoIMGimgfin = recoIMGimg.getScaledInstance(174, 214, java.awt.Image.SCALE_SMOOTH);
		public void paint(Graphics g) {//그리는 함수
			super.paintComponent(g);
			g.drawImage(recoIMGimgfin, 3, 3, this);//background를 그려줌		
		}
		myPanel(){
			this.setOpaque(true);
			this.setBackground(new Color(241,196,15));
		}
	}
}
