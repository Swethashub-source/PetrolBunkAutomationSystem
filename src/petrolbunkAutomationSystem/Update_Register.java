package petrolbunkAutomationSystem;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.List;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Update_Register extends JFrame{
	private static final long serialVersionUID = 1L;
	JPanel jp1,jp2,jp3;
	Connection con;
	int i,j;
	ResultSet rs;
	String sel;
	java.sql.Statement stmt;
	JLabel name,phno,pin; 
	JTextField uname,uphno,upin;
	TextArea ta;
	JButton inname,inpin;
	List lis;
	
	public Update_Register() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","swetha","manager");
			stmt=con.createStatement();
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		name=new JLabel("Name");
		uname=new JTextField(20);
		phno=new JLabel("Phone Number");
		uphno=new JTextField(10);
		pin=new JLabel("Enter pin");
		upin=new JTextField(4);
		
		ta=new TextArea(100,50);
		inname=new JButton("Update Name");
		inpin=new JButton("Update Pin");
		jp1=new JPanel(new GridLayout(5,1));
		jp2=new JPanel(new FlowLayout());
		jp3=new JPanel(new FlowLayout());
		jp1.add(name);
		jp1.add(uname);
		jp1.add(phno);
		jp1.add(uphno);
		jp1.add(pin);
		jp1.add(upin);
		jp2.add(inname);
		jp2.add(inpin);
		jp3.add(ta);
		lis=new List();
		add(jp1);
		add(jp2);
		add(jp3);
		add(lis);
		setVisible(true);
		setSize(500,600);
		setTitle("Update Registered Account:");
		setLayout(new GridLayout(3,2));
		pack();
		
		try {
			rs=stmt.executeQuery("select phno from register");
			while(rs.next()) {
				lis.add(rs.getString(1));
			}
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		lis.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				// TODO Auto-generated method stub
				try {
					sel=lis.getSelectedItem();
					rs=stmt.executeQuery("select name,pin,phno from register where phno='"+lis.getSelectedItem()+"'");
					if(rs.next()) {
						uname.setText(rs.getString(1));
						upin.setText(rs.getString(2));
						uphno.setText(rs.getString(3));
					
					}
				}
				catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}			
		});
		inname.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				try {
					i=stmt.executeUpdate(" update register set name='"+uname.getText()+"' where phno='"+uphno.getText()+"'and pin='"+upin.getText()+"' ");
				} 
				catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				ta.append("\nUpdated "+i+" rows successfully with new name");
				uname.setText("");
				uphno.setText("");
				upin.setText("");
				i=0;
			}
		});
		inpin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				try {
					j=stmt.executeUpdate(" update register set pin='"+upin.getText()+"' where phno='"+uphno.getText()+"'and name='"+uname.getText()+"' ");
				}
				catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				ta.append("\nUpdated "+j+" rows successfully with new pin");
				uname.setText("");
				uphno.setText("");
				upin.setText("");
			}
		});
	}
}
