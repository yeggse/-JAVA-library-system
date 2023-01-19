package administrator;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;

import project.Main;

public class A_Drawing {

}

class BackBTN extends JButton{
	static String id;
	Statement stmt=null;
	BackBTN(String id, Statement stmt, JFrame jframe){
		this.stmt = stmt;
		this.id = id;
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
				Admin_Main main = new Admin_Main(stmt, id);
				jframe.setVisible(false);
			}
		});
	}
}