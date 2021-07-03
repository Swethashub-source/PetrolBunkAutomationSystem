package petrolbunkAutomationSystem;

import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class  Insert_Feedback extends JFrame{
	
	private static final long serialVersionUID = 1L;
	JPanel jp1,jp2,jp3;
	Connection con;
	int i;
	java.sql.Statement stmt;
	JLabel sno,phno,exp,stars,comp; 
	JTextField usno,uphno,uexp,ustars,ucomp;
	TextArea ta;
	JButton in;
	ResultSet rs;
	
	public  Insert_Feedback() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","swetha","manager");
			stmt=con.createStatement();
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sno=new JLabel("Serial No:");
		usno=new JTextField(10);
		phno=new JLabel("Registered phone number");
		uphno=new JTextField(10);
		exp=new JLabel("Experience");
		uexp=new JTextField(100);
		stars=new JLabel("Number Of Stars");
		ustars=new JTextField(5);
		comp=new JLabel("Any Complaints");
		ucomp=new JTextField(1000);
			
		ta=new TextArea(10,40);
		in=new JButton("submit");
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
		jp2.add(in);
		jp3.add(ta);
		add(jp1);
		add(jp2);
		add(jp3);
		setVisible(true);
		setSize(500,600);
		setTitle("Insert into Feedback:");
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
		in.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				try {
					i=stmt.executeUpdate("insert into feedback values("+usno.getText()+","+uphno.getText()+",'"+uexp.getText()+"','"+ustars.getText()+"','"+ucomp.getText()+"')");
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