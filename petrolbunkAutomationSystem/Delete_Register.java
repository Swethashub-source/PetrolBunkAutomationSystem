package petrolbunkAutomationSystem;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.List;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
//import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Color;
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
 public class Delete_Register extends JFrame{

	 private static final long serialVersionUID = 1L;
		JPanel jp1,jp2,jp3;
		Connection con;
		int i;
		ResultSet rs;
		//String sel;
		java.sql.Statement stmt;
		JLabel name,phno,pin; 
		JTextField uname,uphno,upin;
		TextArea ta;
		JButton in;
		List lis;
		
		public Delete_Register()
		{
			try 
			{
				
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
		//	email=new JLabel("student mailid");
			//e=new JTextField(10);
			ta=new TextArea(100,50);
			in=new JButton("submit");
			getContentPane().setBackground(Color.blue);
			jp1=new JPanel(new GridLayout(5,1));
			jp2=new JPanel(new FlowLayout());
			jp3=new JPanel(new FlowLayout());
			
			jp1.add(name);
			jp1.add(uname);
			jp1.add(phno);
			jp1.add(uphno);
			jp1.add(pin);
			jp1.add(upin);
			
			jp2.add(in);
			jp3.add(ta);
			lis=new List();
			add(jp1);
		//	"insert into students values('"+ s.getText() +"','"+sn.getText()+"','"+d.getText()+"','"+e.getText()+"',"+y.getText()+",'"+b.getText()+"')"
			add(jp2);
			add(jp3);
			add(lis);
			setVisible(true);
			setSize(1000,500);
			setTitle("Enter following details:");
			setLayout(new GridLayout(3,2));
			pack();
			
			try {
				rs=stmt.executeQuery("select phno from register");
				while(rs.next()) {
					lis.add(rs.getString(1));
					//lis.add(rs.getString(2));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			lis.addItemListener(new ItemListener() {
				
				@Override
				public void itemStateChanged(ItemEvent arg0) {
					// TODO Auto-generated method stub
					try {
						//sel=lis.getSelectedItem();
						rs=stmt.executeQuery("select name,pin,phno from register where phno='"+lis.getSelectedItem()+"'");
					if(rs.next()) {
						uname.setText(rs.getString(1));
						upin.setText(rs.getString(2));
						uphno.setText(rs.getString(3));
					
					}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}			

			});
			in.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					try {
						
						i=stmt.executeUpdate("delete from register where phno='"+uphno.getText()+"' ");
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					ta.append("\n Deleted "+i+" rows successfully");
				}
			});
		
		}
	}
