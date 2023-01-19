package administrator;

import java.sql.Statement;

import javax.swing.JDialog;

public class b_del extends JDialog{
	Statement stmt;
	String id;
	b_del(Statement stmt, String id){
		this.stmt = stmt;
		this.id = id;
		
		
		
		setVisible(false);
	}
}
