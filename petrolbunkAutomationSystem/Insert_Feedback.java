package petrolbunkAutomationSystem;

import java.sql.SQLException;
import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.awt.event.*;


import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;


 public class  Insert_Feedback extends JFrame{
		private static final long serialVersionUID = 1L;
		JPanel jp1,jp2,jp3;
		Connection con;
		int i;
		java.sql.Statement stmt;
		JLabel exp,stars,comp; 
		JTextField uexp,ustars,ucomp;
		TextArea ta;
		JButton in;
		
		public  Insert_Feedback()
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
			exp=new JLabel("Experience");
			uexp=new JTextField(100);
			stars=new JLabel("Number Of Stars");
			ustars=new JTextField(5);
			comp=new JLabel("Any Complaints");
			ucomp=new JTextField(1000);
			
		
			ta=new TextArea(10,100);
			in=new JButton("submit");
			getContentPane().setBackground(Color.blue);
			jp1=new JPanel(new GridLayout(5,1));
			jp2=new JPanel(new FlowLayout());
			jp3=new JPanel(new FlowLayout());
			jp1.add(exp);
			jp1.add(uexp);
			jp1.add(stars);
			jp1.add(ustars);
			jp1.add(comp);
			jp1.add(ucomp);
			
			jp2.add(in);
			jp3.add(ta);
			add(jp1);
		//	"insert into students values('"+ s.getText() +"','"+sn.getText()+"','"+d.getText()+"','"+e.getText()+"',"+y.getText()+",'"+b.getText()+"')"
			add(jp2);
			add(jp3);
			setVisible(true);
			//getContentPane().setBackground(Color.blue);
			setSize(1000,500);
			setTitle("Enter following details:");
			setLayout(new GridLayout(3,2));
			pack();
			
		in.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					try {
						
						i=stmt.executeUpdate("insert into feedback values('"+uexp.getText()+"','"+ustars.getText()+"','"+ucomp.getText()+"')");
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					//ta.append("given "+bcity+" "+barea+" "+bname +" "+"successfully");
					ta.append("\n Inserted "+i+" rows successfully");
					dispose();
					
				}
			});

}
		}