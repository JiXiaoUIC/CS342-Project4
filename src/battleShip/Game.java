package battleShip;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;



public class Game extends JFrame implements ActionListener{
	private GameClient main;
	private JPanel container;
	private JPanel panel2;

	private JPanel panel[][];
	private String click;

	private JButton box[][];
	private boolean isClicked = false;
	private JTextField shipType = new JTextField();
	private JTextField shipRow = new JTextField();
	private JTextField shipCol = new JTextField();
	private JTextField vertOrHor = new JTextField();
	private BoardBack backBoard;
	


	public Game(GameClient client1)
	{
		
		this.main = client1;

		BoardBack backBoard = new BoardBack();
		//backBoard.startGame();
		setBoard();
		
		JMenuBar menubar = new JMenuBar();
		setJMenuBar(menubar);
		JMenu Ship = new JMenu("Ship");
		JMenu Game = new JMenu("Game");
		JMenuItem placeShip = new JMenuItem("Place Ships");
		placeShip.addActionListener(new ActionListener() {
			// display message dialog when user selects About...
			public void actionPerformed(ActionEvent event) {
				
				
				//while(backBoard.checkAllShipsWasSet(backBoard.playerClient) == false){
					
				Object[] message = {
					    "Type of Ship:\n A: Aircraft Carrier \n B: Battle Ship \n D: Destroyer \n S: Submarine \n P: Patrol Boat ", shipType,
					    "Row", shipRow,
					    "Col", shipCol,
					    "1 for Vertical / 2 for Horizontal", vertOrHor
					};
				int option = JOptionPane.showConfirmDialog(null, message, "Place Ship", JOptionPane.OK_CANCEL_OPTION);
				String type = shipType.getText();
				String row = shipRow.getText();
				String col = shipCol.getText();
				String vertHor = vertOrHor.getText();

				boolean a = backBoard.placeShip(backBoard.playerClient,type.charAt(0),Integer.parseInt(row),Integer.parseInt(col),Integer.parseInt(vertHor));
			
				if( a == false)
				{
					Object[] message4 = {
						    "You have failed to place the ship"
						};
					JOptionPane.showConfirmDialog(null, message4, "Message", JOptionPane.OK_CANCEL_OPTION);
				}
				else
				{
					for(int r = 0; r < 10; r++){
				        for(int c = 0; c < 10; c++){
				 	      // box[r][c].setIcon(new ImageIcon(getClass().getResource("batt100.gif")));
				 	       box[r][c].setText(Character.toString(backBoard.playerClient.board[r][c]));
				        }
				    }	
				
					if(backBoard.checkAllShipsWasSet(backBoard.playerClient) == false)
					{
						Object[] message2 = {
								"You have succeeded to place the ship",
							    "Please place every ship you need"
							};
						JOptionPane.showConfirmDialog(null, message2, "Message", JOptionPane.OK_CANCEL_OPTION);
					}
					else
					{
						Object[] message3 = {
								"You have succeeded to place the ship",
							    "You have placed every sheep you need"
							};
						JOptionPane.showConfirmDialog(null, message3, "Message", JOptionPane.OK_CANCEL_OPTION);
					
					}
				
				}				
			 }
			//}

			
		} // end anonymous inner class
		);
		
		
		
		JMenuItem deleteShip = new JMenuItem("Delete Ships");
		deleteShip.addActionListener(new ActionListener() {
			// display message dialog when user selects About...
			public void actionPerformed(ActionEvent event) {
				
				
				//while(backBoard.checkAllShipsWasSet(backBoard.playerClient) == false){
					
				Object[] message2 = {
					    "Type of Ship:\n A: Aircraft Carrier \n B: Battle Ship \n D: Destroyer \n S: Submarine \n P: Patrol Boat ", shipType,
					    "Row", shipRow,
					    "Col", shipCol
					};
				int option2 = JOptionPane.showConfirmDialog(null, message2, "Delete Ship", JOptionPane.OK_CANCEL_OPTION);
				String type = shipType.getText();
				String row = shipRow.getText();
				String col = shipCol.getText();

				boolean b = backBoard.deleteShip(backBoard.playerClient,type.charAt(0),Integer.parseInt(row),Integer.parseInt(col));
			
				for(int r = 0; r < 10; r++){
			        for(int c = 0; c < 10; c++){
			 	      // box[r][c].setIcon(new ImageIcon(getClass().getResource("batt100.gif")));
			 	       box[r][c].setText(Character.toString(backBoard.playerClient.board[r][c]));
			        }
			    }	
			 }
			//}

			
		} // end anonymous inner class
		);
		
		
		
		JMenuItem replaceShip = new JMenuItem("Replace Ships");
		replaceShip.addActionListener(new ActionListener() {
			// display message dialog when user selects About...
			public void actionPerformed(ActionEvent event) {
				
				
				//while(backBoard.checkAllShipsWasSet(backBoard.playerClient) == false){
					
				Object[] message = {
					    "Type of Ship:\n A: Aircraft Carrier \n B: Battle Ship \n D: Destroyer \n S: Submarine \n P: Patrol Boat ", shipType,
					    "Row", shipRow,
					    "Col", shipCol,
					    "1 for Vertical / 2 for Horizontal", vertOrHor
					};
				int option = JOptionPane.showConfirmDialog(null, message, "Place Ship", JOptionPane.OK_CANCEL_OPTION);
				String type = shipType.getText();
				String row = shipRow.getText();
				String col = shipCol.getText();
				String vertHor = vertOrHor.getText();

				boolean a = backBoard.replaceShip(backBoard.playerClient,type.charAt(0),Integer.parseInt(row),Integer.parseInt(col),Integer.parseInt(vertHor));
			
				for(int r = 0; r < 10; r++){
			        for(int c = 0; c < 10; c++){
			 	      // box[r][c].setIcon(new ImageIcon(getClass().getResource("batt100.gif")));
			 	       box[r][c].setText(Character.toString(backBoard.playerClient.board[r][c]));
			        }
			    }	
			 }
			//}

			
		} // end anonymous inner class
		);
		

		JMenuItem Attack = new JMenuItem("Attack");
		Attack.addActionListener(new ActionListener() {
			// display message dialog when user selects About...
			public void actionPerformed(ActionEvent event) {
				
				
				//while(backBoard.checkAllShipsWasSet(backBoard.playerClient) == false){
				if(backBoard.checkAllShipsWasSet(backBoard.playerClient) == true &&backBoard.checkAllShipsWasSet(backBoard.playerServer) == true )	
				{
					Object[] message = {
							"Please Select row/col to attack",
						    "Row", shipRow,
						    "Col", shipCol,
						};
					int option = JOptionPane.showConfirmDialog(null, message, "Attack", JOptionPane.OK_CANCEL_OPTION);
					String row = shipRow.getText();
					String col = shipCol.getText();
	
					backBoard.clientAttack(Integer.parseInt(row),Integer.parseInt(col));
				
					for(int r = 0; r < 10; r++){
				        for(int c = 0; c < 10; c++){
				 	      // box[r][c].setIcon(new ImageIcon(getClass().getResource("batt100.gif")));
				 	       box[r][c].setText(Character.toString(backBoard.playerClient.board[r][c]));
				        }
				    }	
			 
				}
				else
				{
					Object[] message = {
							"A player or both players didn't set up every ship yet",
						};
					int option = JOptionPane.showConfirmDialog(null, message, "Message", JOptionPane.OK_CANCEL_OPTION);
				}
			//}

			}	
		} // end anonymous inner class
		);
		
		
		Ship.add(placeShip);
		Ship.add(deleteShip);
		Ship.add(replaceShip);
		Game.add(Attack);
		menubar.add(Ship);
		menubar.add(Game);

	}
		


//	
	void update(UserPlayer player)
	{
		for(int r = 0; r < 10; r++){
	        for(int c = 0; c < 10; c++){
		 	       box[r][c].setText(Character.toString(player.board[r][c]));
	 	      // box[r][c].setIcon(new ImageIcon(getClass().getResource("batt100.gif")));
	        }
	    }
	}
	
	void close()
	{
		setVisible(false);
	}
	
	void setBoard()
	{

	    JPanel p = new JPanel();
	    p.setLayout(new GridLayout(10, 10));
	      box = new JButton[10][10];
	    for(int r = 0; r < 10; r++){
	        for(int c = 0; c < 10; c++){
	           box[r][c]= new JButton();
	 	      // box[r][c].setIcon(new ImageIcon(getClass().getResource("batt100.gif")));
	 	       box[r][c].setText("0");
	 			p.add(box[r][c]);
	        }
	    }
	    setLocationRelativeTo(null);
	    
	    getContentPane().add(p);

	
		setSize(400,400);
	    
	    pack();
	    setVisible(true);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}