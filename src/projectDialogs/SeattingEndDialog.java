package projectDialogs;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import project.Main;

public class SeattingEndDialog extends JDialog {
	SeattingEndDialog(){
		setSize(350,350);
		this.setResizable(false);
		setTitle("좌석 사용 완료하기");
		Container c = getContentPane();
		c.setLayout(null);
		c.setBackground(new Color(224,238,238));
			
		JLabel title = new JLabel("좌석 이용 완료");
		title.setSize(350,30);
		title.setLocation(100,30);
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
		
		// 완료 확정 버튼
		JButton okBtn = new JButton("이용 완료");
		okBtn.setLocation(110,250);
		okBtn.setSize(110,30);
		okBtn.setFont(new Font("함초롱돋움", Font.BOLD, 15));
		okBtn.setForeground(Color.white);
		okBtn.setBackground(new Color(131,139,139));
		c.add(okBtn);
		
		okBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
		// 이름 ID 연결하고, 이벤트로 DB에 좌석이용완료 되었다고 연결하기!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
				setVisible(false);
				outroDialog out = new outroDialog();
			}
		});
		
		setVisible(true);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new SeattingEndDialog();
	}

}

class outroDialog extends JDialog{
	outroDialog(){
		setSize(350,350);
		this.setResizable(false);
		setTitle("좌석 사용 완료");
		Container c = getContentPane();
		c.setLayout(null);
		c.setBackground(new Color(224,238,238));
			
		JLabel title = new JLabel("좌석 이용 완료 확인");
		title.setSize(400,30);
		title.setLocation(50,60);
		title.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 23));
		c.add(title);
		
		// 중간 설명 작성하기
		JLabel explain = new JLabel("<html>좌석 사용을 완료합니다. <br>"
				+ "이용해 주셔서 감사합니다. </html>");
		explain.setFont(new Font("본고딕 KR", Font.BOLD, 20));
		explain.setLocation(40,100);
		explain.setSize(400,120);
		c.add(explain);	
		
		// 확인 확정 버튼
		JButton okBtn = new JButton("이용 완료");
		okBtn.setLocation(90,220);
		okBtn.setSize(150,30);
		okBtn.setFont(new Font("함초롱돋움", Font.BOLD, 18));
		okBtn.setForeground(Color.white);
		okBtn.setBackground(new Color(131,139,139));
		c.add(okBtn);
		
		okBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setVisible(false);
				project.Main main = new Main();
			}
		});
		
		setVisible(true);
	}
}
