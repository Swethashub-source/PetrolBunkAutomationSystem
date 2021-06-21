package petrolbunkAutomationSystem;


import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;

import javax.swing.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTable;
//import java.sql.Date;
import javax.swing.table.DefaultTableModel;
import java.util.Date;

 public class Update_PetrolPrice extends JFrame{

		
	 private static final long serialVersionUID = 1L;
		JPanel jp1,jp2,jp3;
		Connection con;
		int i;
		ResultSet rs;
		String sel;
		java.sql.Statement stmt;
		JLabel city,area,date,price; 
		JTextField bcity,barea,bdate,bprice;
		TextArea ta;
		JButton in;
		
		public Update_PetrolPrice()
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
			
		
			ta=new TextArea(100,50);
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
			
			jp2.add(in);
			jp3.add(ta);
			add(jp1);
			add(jp2);
			add(jp3);
			//add(lis);
			setVisible(true);
			//getContentPane().setBackground(Color.blue);
			setSize(1000,500);
			setTitle("Enter following details:");
			setLayout(new GridLayout(3,2));
			pack();
			
			
			DefaultTableModel model=new DefaultTableModel();
			String colHeads[]= {"city","area","date","price"};
			model.setColumnIdentifiers(colHeads);
			JTable table=new JTable();
			table.setModel(model);
			//table.setFillsViewportHeight(true); 
			JScrollPane scroll=new JScrollPane(table);
			scroll.setHorizontalScrollBarPolicy(
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			scroll.setVerticalScrollBarPolicy(
					JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
			add(scroll);
			setVisible(true);
			//getContentPane().setBackground(Color.blue);
			setSize(1000,500);
			String panecity="",panearea="",paneprice="";
			//Date panedate;
			try {
				rs=stmt.executeQuery("select price,city,area,day from petrol_price");
				while(rs.next()) {
					panecity=rs.getString("city");
					panearea=rs.getString("area");
					SimpleDateFormat mat=new SimpleDateFormat("dd-MMM-yy");
					java.util.Date date=rs.getDate("day");
					String panedate=mat.format(date);
					paneprice=rs.getString("price");
					model.addRow(new Object[]{panecity,panearea,panedate,paneprice});
				}
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			table.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent evt) {
					int selectedRow=table.getSelectedRow();
					bcity.setText(model.getValueAt(selectedRow,0).toString());
					barea.setText(model.getValueAt(selectedRow,1).toString());
					//String s=model.getValueAt(selectedRow,2).toString();
					bdate.setText(model.getValueAt(selectedRow,2).toString());
					bprice.setText(model.getValueAt(selectedRow,3).toString());
				}
			});
			
			in.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					SimpleDateFormat mat=new SimpleDateFormat("dd-MMM-yyyy");
					// TODO Auto-generated method stub
					try {
						String sql="UPDATE petrol_price SET price=? where city=? and area=? and day=?";
						PreparedStatement statement=con.prepareStatement(sql);
						int p=Integer.parseInt(bprice.getText());
						statement.setInt(1,p);
						statement.setString(2,bcity.getText());
						statement.setString(3,barea.getText());
						statement.setString(4,bdate.getText());
						i=statement.executeUpdate(); 
					}
					catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					ta.append("Updated "+i+" rows successfully\n");
				}
			});
		
		}
	}