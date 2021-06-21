package petrolbunkAutomationSystem;

import java.text.SimpleDateFormat;

/*public class Update_Feedback {

}
*/
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.Color;
import java.awt.event.*;


import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


 public class  Update_Feedback extends JFrame{
		private static final long serialVersionUID = 1L;
		JPanel jp1,jp2,jp3;
		Connection con;
		int i;
		ResultSet rs;
		java.sql.Statement stmt;
		JLabel exp,stars,comp; 
		JTextField uexp,ustars,ucomp;
		TextArea ta;
		JButton in;
		
		public  Update_Feedback()
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
			
			
			DefaultTableModel model=new DefaultTableModel();
			String colHeads[]= {"Experience","Stars","Complaints"};
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
			String paneexp="",panestars="",panecomp="";
			//Date panedate;
			try {
				rs=stmt.executeQuery("select experience,noofstars,complaint from feedback");
				while(rs.next()) {
					paneexp=rs.getString("experience");
					panestars=rs.getString("noofstars");
					panecomp=rs.getString("complaint");
					model.addRow(new Object[]{paneexp,panestars,panecomp});
				}
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			table.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent evt) {
					int selectedRow=table.getSelectedRow();
					uexp.setText(model.getValueAt(selectedRow,0).toString());
					ustars.setText(model.getValueAt(selectedRow,1).toString());
					//String s=model.getValueAt(selectedRow,2).toString();
					ucomp.setText(model.getValueAt(selectedRow,2).toString());
					//bprice.setText(model.getValueAt(selectedRow,3).toString());
				}
			});
			
			
			
			
			
			
			
			
			
			
			
			
		in.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					try {
						
						String sql="UPDATE feedback SET experience=? and noofstars=? and complaint=?";
						PreparedStatement statement=con.prepareStatement(sql);
						statement.setString(1,uexp.getText());
						statement.setString(2,ustars.getText());
						statement.setString(3,ucomp.getText());
						i=statement.executeUpdate(); 
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					//ta.append("given "+bcity+" "+barea+" "+bname +" "+"successfully");
					ta.append("\n updated "+i+" rows successfully");
		
				}
			});

}
		}