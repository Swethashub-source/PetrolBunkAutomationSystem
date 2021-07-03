package petrolbunkAutomationSystem;

import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

public class Insert_BookPetrol extends JFrame{
	private static final long serialVersionUID = 1L;
	JPanel jp1,jp2,jp3;
	Connection con;
	int i;
	ResultSet rs;
	java.sql.Statement stmt;
	JLabel phno,amt; 
	JTextField uamt,uphno;
	TextArea ta;
	JButton in;
	public Insert_BookPetrol() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","swetha","manager");
			stmt=con.createStatement();
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		phno=new JLabel("Phone Number");
		uphno=new JTextField(10);
		amt=new JLabel("Enter amount");
		uamt=new JTextField(30);
		ta=new TextArea(10,40);
		in=new JButton("submit");
		jp1=new JPanel(new GridLayout(5,1));
		jp2=new JPanel(new FlowLayout());
		jp3=new JPanel(new FlowLayout());
		jp1.add(phno);
		jp1.add(uphno);
		jp1.add(amt);
		jp1.add(uamt);
		jp2.add(in);
		jp3.add(ta);
		add(jp1);
		add(jp2);
		add(jp3);
		setVisible(true);
		setSize(500,600);
		setTitle("Insert into Petrol Booking:");
		setLayout(new GridLayout(3,2));
		pack();
			
		in.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				try {
					i=stmt.executeUpdate("insert into booking_petrol values('"+uphno.getText()+"','"+uamt.getText()+"')");
				}
				catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				ta.append("\n Inserted "+i+" rows successfully");
					
			}
		});
	}
}