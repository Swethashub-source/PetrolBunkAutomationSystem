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

public class Delete_Bunk extends JFrame{
	private static final long serialVersionUID = 1L;
	JPanel jp1,jp2,jp3;
	Connection con;
	int i;
	ResultSet rs;
	String sel;
	java.sql.Statement stmt;
	JLabel city,area,name; 
	JTextField bcity,barea,bname;
	TextArea ta;
	JButton in;
	List lis;
	 
	public Delete_Bunk()
	{
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","swetha","manager");
			stmt=con.createStatement();
		} 
		catch (Exception e) {
				// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		city=new JLabel("City Name");
		bcity=new JTextField(30);
		area=new JLabel("Area Name");
		barea=new JTextField(30);
		name=new JLabel("Bunk Name");
		bname=new JTextField(30);
		
		ta=new TextArea(10,40);
		in=new JButton("submit");
		jp1=new JPanel(new GridLayout(5,1));
		jp2=new JPanel(new FlowLayout());
		jp3=new JPanel(new FlowLayout());
			
		jp1.add(city);
		jp1.add(bcity);
		jp1.add(area);
		jp1.add(barea);
		jp1.add(name);
		jp1.add(bname);
		jp2.add(in);
		jp3.add(ta);
		lis=new List();
		add(jp1);
		add(jp2);
		add(jp3);
		add(lis);
		setVisible(true);
		setSize(500,600);
		setTitle("Enter following details:");
		setLayout(new GridLayout(3,2));
		pack();
			
		try {
			rs=stmt.executeQuery("select area from bunk");
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
					rs=stmt.executeQuery("select city,area,name from bunk where area='"+lis.getSelectedItem()+"'");
					if(rs.next()) {
						bcity.setText(rs.getString(1));
						barea.setText(rs.getString(2));
						bname.setText(rs.getString(3));
					}
				}
				catch (SQLException e) {
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
				
					i=stmt.executeUpdate("delete from bunk where city='"+bcity.getText()+"'and area='"+barea.getText()+"' ");
				} 
				catch (SQLException e) {
						// TODO Auto-generated catch block
					e.printStackTrace();
				}
					ta.append("\n Deleted "+i+" rows successfully");
			}
		});
		
	}
}
