package petrolbunkAutomationSystem;

import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
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
	JLabel sno,phno,exp,stars,comp; 
	JTextField usno,uphno,uexp,ustars,ucomp;
	TextArea ta;
	JButton expr,star,com;
	public  Update_Feedback() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","swetha","manager");
			stmt=con.createStatement();
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		phno=new JLabel("Registered phone number");
		uphno=new JTextField(10);
		sno=new JLabel("Serial No:");
		usno=new JTextField(10);
		exp=new JLabel("Experience");
		uexp=new JTextField(100);
		stars=new JLabel("Number Of Stars");
		ustars=new JTextField(5);
		comp=new JLabel("Any Complaints");
		ucomp=new JTextField(1000);
		
		ta=new TextArea(10,50);
		expr=new JButton("UpadteMyExperience");
		star=new JButton("UpdateMyStars");
		com=new JButton("UpdateMyComplaint");
		jp1=new JPanel(new GridLayout(5,1));
		jp2=new JPanel(new FlowLayout());
		jp3=new JPanel(new FlowLayout());
		jp1.add(sno);
		jp1.add(usno);
		jp1.add(phno);
		jp1.add(uphno);
		jp1.add(exp);
		jp1.add(uexp);
		jp1.add(stars);
		jp1.add(ustars);
		jp1.add(comp);
		jp1.add(ucomp);
		jp2.add(expr);
		jp2.add(star);
		jp2.add(com);
		jp3.add(ta);
		add(jp1);
		add(jp2);
		add(jp3);
		setVisible(true);
		setSize(500,600);
		setTitle("Update your Feedback:");
		setLayout(new GridLayout(3,2));
		pack();
			
		DefaultTableModel model=new DefaultTableModel();
		String colHeads[]= {"sno","phno","Experience","Stars","Complaints"};
		model.setColumnIdentifiers(colHeads);
		JTable table=new JTable();
		table.setModel(model);
		JScrollPane scroll=new JScrollPane(table);
		scroll.setHorizontalScrollBarPolicy(
			JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setVerticalScrollBarPolicy(
			JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		add(scroll);
		setVisible(true);
		setSize(500,600);
		String paneexp="",panestars="",panecomp="", panesno="";
		String panephno="";
		try {
			rs=stmt.executeQuery("select sno,phno,experience,noofstars,complaint from feedback");
			while(rs.next()) {
				panesno=rs.getString("sno");
				panephno=rs.getString("phno");
				paneexp=rs.getString("experience");
				panestars=rs.getString("noofstars");
				panecomp=rs.getString("complaint");
				model.addRow(new Object[]{panesno,panephno,paneexp,panestars,panecomp});
			}
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		table.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				int selectedRow=table.getSelectedRow();
				usno.setText(model.getValueAt(selectedRow,0).toString());
				uphno.setText(model.getValueAt(selectedRow,1).toString());
				uexp.setText(model.getValueAt(selectedRow,2).toString());
				ustars.setText(model.getValueAt(selectedRow,3).toString());
				ucomp.setText(model.getValueAt(selectedRow,4).toString());
			}
		});
		expr.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				try {
					String sql="UPDATE feedback SET experience=? where noofstars=? and complaint=? and phno=? and sno=?";
					PreparedStatement statement=con.prepareStatement(sql);
					statement.setString(1,uexp.getText());
					statement.setString(2,ustars.getText());
					statement.setString(3,ucomp.getText());
					statement.setString(4,uphno.getText());
					statement.setString(5,usno.getText());
					i=statement.executeUpdate(); 
				} 
				catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(i==1) {
					ta.append("\n updated your given experience successfully");
				}
				else {
					ta.append("\n Cannot update your complaint ");
				}
				uexp.setText("");
				usno.setText("");
				ustars.setText("");
				uphno.setText("");
				ucomp.setText("");
			}
		});

		star.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				try {
					String sql="UPDATE feedback SET noofstars=? where experience=? and complaint=? and phno=? and sno=?";
					PreparedStatement statement=con.prepareStatement(sql);
					statement.setString(2,uexp.getText());
					statement.setString(1,ustars.getText());
					statement.setString(3,ucomp.getText());
					statement.setString(4,uphno.getText());
					statement.setString(5,usno.getText());
					i=statement.executeUpdate(); 
				}
				catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(i==1) {
					ta.append("\n updated given no of stars successfully");
				}
				else {
					ta.append("\n Cannot update your complaint ");
				}
				uexp.setText("");
				usno.setText("");
				ustars.setText("");
				uphno.setText("");
				ucomp.setText("");
			}
		});
		com.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				try {
					
					String sql="UPDATE feedback SET complaint=?  where experience=? and noofstars=? and phno=? and sno=?";
					PreparedStatement statement=con.prepareStatement(sql);
					statement.setString(2,uexp.getText());
					statement.setString(3,ustars.getText());
					statement.setString(1,ucomp.getText());
					statement.setString(4,uphno.getText());
					statement.setString(5,usno.getText());
					i=statement.executeUpdate(); 
				} 
				catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(i==1) {
					ta.append("\n updated your complaint successfully");
				}
				else {
					ta.append("\n Cannot update your complaint ");
				}
				uexp.setText("");
				usno.setText("");
				ustars.setText("");
				uphno.setText("");
				ucomp.setText("");
			}
		});
	}
}