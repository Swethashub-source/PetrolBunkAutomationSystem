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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;


 public class Update_BookPetrol extends JFrame{

		
	 private static final long serialVersionUID = 1L;
		JPanel jp1,jp2,jp3;
		Connection con;
		int i,amount;
		ResultSet rs;
		String sel;
		java.sql.Statement stmt;
		JLabel phno,amt; 
		JTextField uamt,uphno;
		TextArea ta;
		JButton in;
		
		public Update_BookPetrol()
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
			phno=new JLabel("Phone Number");
			uphno=new JTextField(10);
			amt=new JLabel("Enter amount");
			uamt=new JTextField(30);
		
			ta=new TextArea(100,50);
			in=new JButton("Submit");
			
			getContentPane().setBackground(Color.blue);
			jp1=new JPanel(new GridLayout(5,1));
			jp2=new JPanel(new FlowLayout());
			jp3=new JPanel(new FlowLayout());
			jp1.add(phno);
			jp1.add(uphno);
			jp1.add(amt);
			jp1.add(uamt);

			jp2.add(in);
			
			jp3.add(ta);
			//lis=new List();
			//getContentPane().setBackground(Color.blue);
			add(jp1);
		//	"insert into students values('"+ s.getText() +"','"+sn.getText()+"','"+d.getText()+"','"+e.getText()+"',"+y.getText()+",'"+b.getText()+"')"
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
			String colHeads[]= {"phno","amount"};
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
			String panephno="",paneamt="";
			//Date panedate;
			try {
				rs=stmt.executeQuery("select phno,amt from booking_petrol");
				while(rs.next()) {
					panephno=rs.getString("phno");
					paneamt=rs.getString("amt");
					model.addRow(new Object[]{panephno,paneamt});
					//model.addRow(new Object[]{panecity,panearea,panedate});
				}
				
		 		
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			table.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent evt) {
					int selectedRow=table.getSelectedRow();
					uphno.setText(model.getValueAt(selectedRow,0).toString());
					//amount=Integer.parseInt(model.getValueAt(selectedRow,1).toString());
				}
			});
			
			in.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					/*// TODO Auto-generated method stub
					try {
						//int a=Integer.parseInt(uamt.getText());
						i=stmt.executeUpdate(" update update_balance set amt=amt+'"+uamt.getText()+"' where phno='"+uphno.getText()+"' ");
					}*/
					try {
			
							
						String sql="UPDATE booking_petrol SET amt=? where phno=?";
						PreparedStatement statement=con.prepareStatement(sql);
						int p=Integer.parseInt(uamt.getText());
						statement.setInt(1,p);
						statement.setString(2,uphno.getText());
						i=statement.executeUpdate();
							/*String sql="UPDATE booking_petrol SET amt=amt-? where phno=?";
							PreparedStatement statement=con.prepareStatement(sql);
							//int p=Integer.parseInt(uamt.getText());
							statement.setInt(1,p);
							statement.setString(2,uphno.getText());
							i=statement.executeUpdate();
					}
						else {
							String sql="UPDATE booking_petrol SET amt=? where phno=?";
							PreparedStatement statement=con.prepareStatement(sql);
							//int p=Integer.parseInt(uamt.getText());
							statement.setInt(1,(amount+p));
							statement.setString(2,uphno.getText());
							i=statement.executeUpdate();
						}*/
					}
					catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					ta.append("\nUpdated "+i+" rows successfully.");
					/*uamt.setText("");
					uphno.setText("");
				 	i=0;
					*/
				}
			});
		}
	}