package petrolbunkAutomationSystem;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Frame extends JFrame {
	
	private static final long serialVersionUID = 1L;
	JMenuBar menubar;
	JMenu menu1,menu2,menu3,menu4,menu5,menu6,menu7,menu8;
	JMenuItem item1,item2,item3,item4,item5,item6,item7,item8;
	JMenuItem up1,up2,up3,up4,up5,up6,up7,up8,dl1,dl2,dl3,dl4,dl5,dl6,dl7,dl8;
	
	public  void firstframe() {
		
		menubar=new JMenuBar();
		menu1=new JMenu("Bunk details");
		menu2=new JMenu("Petrol Price");
		menu3=new JMenu("Diesel Price");
		menu4=new JMenu("SignUp");
		menu5=new JMenu("Update My Balance");
		menu6=new JMenu("Book Petrol");
		menu7=new JMenu("Book Diesel");
		menu8=new JMenu("Feedback");
		
		item1=new JMenuItem("Insert");
		up1=new JMenuItem("Update");
		dl1=new JMenuItem("Delete");
		item2=new JMenuItem("Insert");
		up2=new JMenuItem("Update");
		dl2=new JMenuItem("Delete");
		item3=new JMenuItem("Insert");
		up3=new JMenuItem("Update");
		dl3=new JMenuItem("Delete");
		item4=new JMenuItem("Insert");
		up4=new JMenuItem("Update");
		dl4=new JMenuItem("Delete");
		item5=new JMenuItem("Insert");
		up5=new JMenuItem("Update");
		dl5=new JMenuItem("Delete");
		item6=new JMenuItem("Insert");
		up6=new JMenuItem("Update");
		dl6=new JMenuItem("Delete");
		item7=new JMenuItem("Insert");
		up7=new JMenuItem("Update");
		dl7=new JMenuItem("Delete");
		item8=new JMenuItem("Insert");
		up8=new JMenuItem("Update");
		dl8=new JMenuItem("Delete");
		
		getContentPane().setBackground(Color.blue);
		setVisible(true);
		setSize(500,600);
		setTitle("PetrolBunk Automation System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new FlowLayout());
		setJMenuBar(menubar);
		menubar.add(menu1);
		menubar.add(menu2);
		menubar.add(menu3);
		menubar.add(menu4);
		menubar.add(menu5);
		menubar.add(menu6);
		menubar.add(menu7);
		menubar.add(menu8);
		menu1.add(item1);
		menu1.add(up1);
		menu1.add(dl1);
		menu2.add(item2);
		menu2.add(up2);
		menu2.add(dl2);
		menu3.add(item3);
		menu3.add(up3);
		menu3.add(dl3);
		menu4.add(item4);
		menu4.add(up4);
		menu4.add(dl4);
		menu5.add(item5);
		menu5.add(up5);
		menu5.add(dl5);
		menu6.add(item6);
		menu6.add(up6);
		menu6.add(dl6);
		menu7.add(item7);
		menu7.add(up7);
		menu7.add(dl7);
		menu8.add(item8);
		menu8.add(up8);
		menu8.add(dl8);
		
		
		item1.addActionListener(new ActionListener() {
		
		@Override
			public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
				new Insert_Bunk();
			
			}
		});

		up1.addActionListener(new ActionListener() {
		
		@Override
			public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
				new Update_Bunk();
			
			}
		});
	
		dl1.addActionListener(new ActionListener() {
		
		@Override
			public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
				new Delete_Bunk();
			
			}
		});
	
		item2.addActionListener(new ActionListener() {
		
		@Override
			public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
				new Insert_PetrolPrice();
			
			}
		});

		up2.addActionListener(new ActionListener() {
		
		@Override
			public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
				new Update_PetrolPrice();
			
			}
		});
	
		dl2.addActionListener(new ActionListener() {
		
		@Override
			public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			new Delete_PetrolPrice();
			
		}
	});
	
		item3.addActionListener(new ActionListener() {
		
		@Override
			public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
				new Insert_DieselPrice();
			}
		});

		up3.addActionListener(new ActionListener() {
		
		@Override
			public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
				new Update_DieselPrice();
			
			}
		});
	
		dl3.addActionListener(new ActionListener() {
		
		@Override
			public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
				new Delete_DieselPrice();
			
			}
		});
	
		item4.addActionListener(new ActionListener() {
		
		@Override
			public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
				new Insert_Register();
			}
		});

		up4.addActionListener(new ActionListener() {
		
		@Override
			public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
				new Update_Register();
			
			}
		});
	
		dl4.addActionListener(new ActionListener() {
		
		@Override
			public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
				new Delete_Register();
			
			}
		});
	
		item5.addActionListener(new ActionListener() {
		
		@Override
			public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
				new Insert_UpdateBalance();
			}
		});

		up5.addActionListener(new ActionListener() {
		
		@Override
			public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
				new Update_UpdateBalance();
			
			}
		});
	
		dl5.addActionListener(new ActionListener() {
		
		@Override
			public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
				new Delete_UpdateBalance();
			
			} 
		});
		
		item6.addActionListener(new ActionListener() {
		
		@Override
			public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
				new Insert_BookPetrol();
			}
		});
		
		up6.addActionListener(new ActionListener() {
	
		@Override
			public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
				new Update_BookPetrol();
		
			}
		});

		dl6.addActionListener(new ActionListener() {
	
			@Override
			public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
				new Delete_BookPetrol();
		
			} 
		}); 
		
		item7.addActionListener(new ActionListener() {
	
			@Override
			public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
				new Insert_BookDiesel();
			}
		}); 

		up7.addActionListener(new ActionListener() {
	
			@Override
			public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
				new Update_BookDiesel();
		
			}
		});
	
		dl7.addActionListener(new ActionListener() {
	
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new Delete_BookDiesel();
		
			} 
		});
		
		item8.addActionListener(new ActionListener() {
	
			@Override
			public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
				new Insert_Feedback();
			}
		}); 

		up8.addActionListener(new ActionListener() {
	
			@Override
			public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
				new Update_Feedback();
		
			}
		});

		dl8.addActionListener(new ActionListener() {
	
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				new Delete_Feedback();
		
			}
		});
	}
	
	public static void main(String[] args) {
		
		Frame f = new Frame();
		f.firstframe();

	}

}
