package battleShip;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Main extends JFrame implements ActionListener {
	static Main main = null;
	private JLabel displayLabel;
	private static String portField;
	JTextField nameClient = new JTextField();
	JTextField portFieldClient = new JTextField();

	
	public Main() {
		this.Frame();
	}

	public void Frame() {

		JMenuBar menubar = new JMenuBar();
		setJMenuBar(menubar);
		JMenu Connect = new JMenu("Connect");
		JMenuItem Server = new JMenuItem("Server");
		StatusBar statusBar = new StatusBar();
		JMenu Help = new JMenu("Help");
		JMenu About = new JMenu("About");
		JMenuItem About2 = new JMenuItem("About");
		JMenuItem Exit2 = new JMenuItem("Exit");

		About2.addActionListener(new ActionListener() {
			// display message dialog when user selects About...
			public void actionPerformed(ActionEvent event) {
				JOptionPane.showMessageDialog(Main.this,
	  	                  "Battle Ship, 4th project of CS342.\n"
	  	                  +"Youlho Cha <ycha8@uic.edu>\n"
	  	                  +"Liam Edelman <ledelma2@uic.edu>\n"
	  	                  +"Ji Xiao <jxiao22@uic.edu>"
	  	                  +"2017-11-16\n"
	  	                  +"Thank You\n"




         ,"About", JOptionPane.PLAIN_MESSAGE);
	            }
			}
		// end anonymous inner class
		);

		Server.addActionListener(new ActionListener() {
			// display message dialog when user selects About...
			public void actionPerformed(ActionEvent event) {
				GameServer Server = new GameServer();
			}
		} // end anonymous inner class
		);

		JMenuItem Client = new JMenuItem("Client");

		Client.addActionListener(new ActionListener() {
			// display message dialog when user selects About...
			public void actionPerformed(ActionEvent event) {
//				
				GameClient game = new GameClient();
			
			}
		} // end anonymous inner class
		);

		JMenuItem start = new JMenuItem("Start");

		start.addActionListener(new ActionListener() {
			// display message dialog when user selects About...
			public void actionPerformed(ActionEvent event) {
         	    //setVisible(false);
			//	Game game = new Game(main);

			}
		} // end anonymous inner class
		);

		
		  JMenuItem interfaceMenuItem = new JMenuItem( "Help" );
	      interfaceMenuItem.setMnemonic( 'A' );
	      Help.add( interfaceMenuItem );
	      interfaceMenuItem.addActionListener(
	         new ActionListener() {  // anonymous inner class
	            // display message dialog when user selects About...
	            public void actionPerformed( ActionEvent event )
	            {
	            	JOptionPane.showMessageDialog(Main.this,
	  	                  "Battle Ship, 4th project of CS342.\n"
	  	                  +"Connect Menu:\n"
	  	                  +"Server : Allowing the user to make a server.\n"
	  	                  +"Client : Allowing the user to connect to a server as a client.\n"
	  	                  +"\n"
	  	                  +"How to play :\n"
	  	                  +"1. Make a Server :\n"
	  	                  +"2. If you are a server user, wait for a client to join \n"
	  	                  +"3. If you are a client user, join to a server \n"
	  	                  +"4. place ships to the board, wait for both player to complete placing ships \n"
	  	                  +"5. With Attack feature, please attack each other until one of player loses \n"
	  	                 




           ,"How to use our interface", JOptionPane.PLAIN_MESSAGE);
	            }
	         }  // end anonymous inner class
	      ); 
		
		JMenu Exit = new JMenu("Exit");
		Exit2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
					  System.exit(0);
		            }
				}
			// end anonymous inner class
		);	      
		
		Connect.add(Server);
		Connect.add(Client);
		About.add(About2);
		Exit.add(Exit2);
		//test.add(start);
		menubar.add(Connect);
		menubar.add(Help);
		menubar.add(About);
		menubar.add(Exit);

		//menubar.add(test);
		displayLabel = new JLabel("BattleShip", SwingConstants.CENTER);
		getContentPane().setBackground(Color.WHITE);
		displayLabel.setFont(new Font("Serif", Font.PLAIN, 72));
		getContentPane().add(displayLabel, BorderLayout.CENTER);
		getContentPane().add(statusBar, java.awt.BorderLayout.SOUTH);

		setResizable(false);
		setSize(400, 400);
		setLocationRelativeTo(null);
		setVisible(true);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

	public static void main(String[] args) {
		main = new Main();
	}

}

class StatusBar extends JLabel {
    
    /** Creates a new instance of StatusBar */
    public StatusBar() {
        super();
        super.setPreferredSize(new Dimension(100, 15));

       
        setMessage("Ready");
    }
     
    public void setMessage(String message) {
        setText(" "+message);        
    }        
}
