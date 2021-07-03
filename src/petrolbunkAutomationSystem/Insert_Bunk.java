package petrolbunkAutomationSystem;

import java.sql.SQLException;
import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

public class Insert_Bunk extends JFrame{
	
		private static final long serialVersionUID = 1L;
		JPanel jp1,jp2,jp3;
		Connection con;
		int i;
		java.sql.Statement stmt;
		JLabel city,area,name; 
		JTextField bcity,barea,bname;
		TextArea ta;
		JButton in;
		
		public   Insert_Bunk()
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
			
			city=new JLabel("City Name");
			bcity=new JTextField(30);
			area=new JLabel("Area Name");
			barea=new JTextField(30);
			name=new JLabel("Bunk Name");
			bname=new JTextField(30);
			
		
			ta=new TextArea(10,40);
			in=new JButton("submit");
			getContentPane().setBackground(Color.blue);
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
			add(jp1);
			add(jp2);
			add(jp3);
			setVisible(true);
			setSize(500,600);
			setTitle("Enter following details:");
			setLayout(new GridLayout(3,2));
			pack();
			
			in.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
				 	// TODO Auto-generated method stub
					try {
						
						i=stmt.executeUpdate("insert into bunk values('"+bcity.getText()+"','"+barea.getText()+"','"+bname.getText()+"')");
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