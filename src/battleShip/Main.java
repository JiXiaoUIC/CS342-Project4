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
		JMenu test = new JMenu("Test");

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

		
		Connect.add(Server);
		Connect.add(Client);
		//test.add(start);
		menubar.add(Connect);
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
