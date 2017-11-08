package battleShip;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;
import java.util.Arrays;
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

		Server.addActionListener(new ActionListener() {
			// display message dialog when user selects About...
			public void actionPerformed(ActionEvent event) {
				portField = JOptionPane.showInputDialog(Connect, "Enter a port number to make a server", null);
				ServerGame Server = new ServerGame(Integer.parseInt(portField));
				Server.start();
				

			}
		} // end anonymous inner class
		);

		JMenuItem Client = new JMenuItem("Client");

		Client.addActionListener(new ActionListener() {
			// display message dialog when user selects About...
			public void actionPerformed(ActionEvent event) {
				Object[] message = {
					    "IP Address:", nameClient,
					    "Portnumber:", portFieldClient,
					};
				int option = JOptionPane.showConfirmDialog(null, message, "connect", JOptionPane.OK_CANCEL_OPTION);
				String nameText = nameClient.getText();
				String portText = portFieldClient.getText();
				ClientGame game = new ClientGame(nameText, Integer.parseInt(portText));
				game.start();	
			}
		} // end anonymous inner class
		);

		Connect.add(Server);
		Connect.add(Client);
		menubar.add(Connect);

		displayLabel = new JLabel("BattleShip", SwingConstants.CENTER);
		getContentPane().setBackground(Color.WHITE);
		displayLabel.setFont(new Font("Serif", Font.PLAIN, 72));
		getContentPane().add(displayLabel, BorderLayout.CENTER);
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
