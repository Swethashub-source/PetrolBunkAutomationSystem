package petrolbunkAutomationSystem;

/*public class Insert_PetrolPrice {

}
*/

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


 public class Insert_PetrolPrice extends JFrame{
		private static final long serialVersionUID = 1L;
		JPanel jp1,jp2,jp3;
		Connection con;
		int i;
		java.sql.Statement stmt;
		JLabel city,area,date,price; 
		JTextField bcity,barea,bdate,bprice;
		TextArea ta;
		JButton in;
		
		public   Insert_PetrolPrice()
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
			barea=new JTextField(20);
			date=new JLabel("Date");
			bdate=new JTextField(10);
			price=new JLabel("Price");
			bprice=new JTextField(10);
			
		
			ta=new TextArea(10,100);
			in=new JButton("submit");
			getContentPane().setBackground(Color.blue);
			jp1=new JPanel(new GridLayout(5,1));
			jp2=new JPanel(new FlowLayout());
			jp3=new JPanel(new FlowLayout());
			jp1.add(city);
			jp1.add(bcity);
			jp1.add(area);
			jp1.add(barea);
			jp1.add(date);
			jp1.add(bdate);
			jp1.add(price);
			jp1.add(bprice);
			/*jp1.add(email);
			jp1.add(e);
			jp1.add(year);
			jp1.add(y);
			jp1.add(branch);
			jp1.add(b);
		//	jp1.add(email);
		//	jp1.add(e);
		 * *
		 */
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
						
						i=stmt.executeUpdate("insert into petrol_price values('"+bcity.getText()+"','"+barea.getText()+"','"+bdate.getText()+"','"+bprice.getText()+"')");
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