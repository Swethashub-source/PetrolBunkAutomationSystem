package petrolbunkAutomationSystem;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
	public Update_BookPetrol() {
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
		in=new JButton("Submit");
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
		setTitle("Update Booking Petrol:");
		setLayout(new GridLayout(3,2));
		pack();
		
		DefaultTableModel model=new DefaultTableModel();
		String colHeads[]= {"phno","amount"};
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
		setSize(600,500);
		String panephno="",paneamt="";
		try {
			rs=stmt.executeQuery("select phno,amt from booking_petrol");
			while(rs.next()) {
				panephno=rs.getString("phno");
				paneamt=rs.getString("amt");
				model.addRow(new Object[]{panephno,paneamt});
			}
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		table.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				int selectedRow=table.getSelectedRow();
				uphno.setText(model.getValueAt(selectedRow,0).toString());
			}
		});
		in.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					String sql="UPDATE booking_petrol SET amt=? where phno=?";
					PreparedStatement statement=con.prepareStatement(sql);
					int p=Integer.parseInt(uamt.getText());
					statement.setInt(1,p);
					statement.setString(2,uphno.getText());
					i=statement.executeUpdate();
				}
				catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				ta.append("\nUpdated "+i+" rows successfully.");
			}
		});
	}
}