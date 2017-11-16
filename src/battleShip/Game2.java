package battleShip;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;



public class Game2 extends JFrame implements ActionListener{
	private CommunicationThread main;
	private JPanel container;
	private JPanel panel2;

	private JPanel panel[][];
	private String click;

	private JButton box[][];
	private JButton box2[][];

	private boolean isClicked = false;
	private JTextField shipType = new JTextField();
	private JTextField shipRow = new JTextField();
	private JTextField shipCol = new JTextField();
	private JTextField vertOrHor = new JTextField();
	private BoardBack backBoard;
	


	public Game2(CommunicationThread communicationThread)
	{
		
		this.main = communicationThread;

		BoardBack backBoard = new BoardBack();
		//backBoard.startGame();
		setBoard();
		
		JMenuBar menubar = new JMenuBar();
		setJMenuBar(menubar);
		JMenu Ship = new JMenu("Ship");
		JMenu Game = new JMenu("Game");		
		JMenu Help = new JMenu("Help");

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

				boolean a = backBoard.placeShip(backBoard.playerClient,type,Integer.parseInt(row)-1,Integer.parseInt(col)-1,Integer.parseInt(vertHor));
			
				if( a == false)
				{
					Object[] message4 = {
						    "You have failed to place the ship"
						};
					JOptionPane.showConfirmDialog(null, message4, "Message", JOptionPane.OK_CANCEL_OPTION);
				}
				else
				{
					update(backBoard.playerClient);
				
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

				boolean b = backBoard.deleteShip(backBoard.playerClient,type,Integer.parseInt(row)-1,Integer.parseInt(col)-1);
			
				update(backBoard.playerClient);
	
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

				boolean a = backBoard.replaceShip(backBoard.playerClient,type,Integer.parseInt(row)-1,Integer.parseInt(col)-1,Integer.parseInt(vertHor));
			
				update(backBoard.playerClient);

			 }
			//}

			
		} // end anonymous inner class
		);
		

		JMenuItem Attack = new JMenuItem("Attack");
		Attack.addActionListener(new ActionListener() {
			// display message dialog when user selects About...
			public void actionPerformed(ActionEvent event) {
				
				
	
//				if(backBoard.checkAllShipsWasSet(backBoard.playerClient) == true &&backBoard.checkAllShipsWasSet(backBoard.playerServer) == true )	
//				{
					Object[] message = {
							"Please Select row/col to attack",
						    "Row", shipRow,
						    "Col", shipCol,
						};
					int option = JOptionPane.showConfirmDialog(null, message, "Attack", JOptionPane.OK_CANCEL_OPTION);
					String row = shipRow.getText();
					String col = shipCol.getText();
	
					
					
					backBoard.clientAttack(Integer.parseInt(row)-1,Integer.parseInt(col)-1);
					
					box2[Integer.parseInt(row)-1][Integer.parseInt(col)-1].setIcon(new ImageIcon(getClass().getResource("batt103.gif")));
					update(backBoard.playerClient);

			 
//				}
//				else
//				{
//					Object[] message = {
//							"A player or both players didn't set up every ship yet",
//						};
//					int option = JOptionPane.showConfirmDialog(null, message, "Message", JOptionPane.OK_CANCEL_OPTION);
//				}

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
	            	JOptionPane.showMessageDialog(Game2.this,
	  	                  "Battle Ship, 4th project of CS342.\n"
	  	                  +"Ship Menu:\n"
	  	                  +"Place : Allowing the user to place ships.\n"
	  	                  +"Delete : Allowing the user to place ship.\n"
	  	                  +"Replace : Allowing the user to replace ship.\n"
	  	                  +"Whenever you delete a ship, please use replace to place the ship agian\n"
	  	                  +"\n"


	  	                  +"Game Menu:\n"
	  	                  + "Attack : Allow the user to Attack the other player.\n"
	  	                  +"\n"

	  	                  +"Windows:\n"
	  	                  + "Upper window is your board. You can place your ship and check a status of your ship.\n"
	  	                  + "Lower window is a board to let you know where you attacked.\n"
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
			JMenu About = new JMenu("About");
			JMenuItem About2 = new JMenuItem("About");
			About2.addActionListener(new ActionListener() {
				// display message dialog when user selects About...
				public void actionPerformed(ActionEvent event) {
					JOptionPane.showMessageDialog(Game2.this,
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

			JMenu Exit = new JMenu("Exit");
			JMenuItem Exit2 = new JMenuItem("Exit");
			Exit2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent event) {
						  System.exit(0);
			            }
					}
				// end anonymous inner class
			);	      

		About.add(About2);	
		Exit.add(Exit2);
		Ship.add(placeShip);
		Ship.add(deleteShip);
		Ship.add(replaceShip);
		Game.add(Attack);
		Help.add(interfaceMenuItem);
		menubar.add(Ship);
		menubar.add(Game);
		menubar.add(Help);
		menubar.add(About);
		menubar.add(Exit);


	}
	



	String finishSetUp()
	{
		return "done";
	}
		
	void beHit( int i, int j )
	{
		//ImageIcon image = new ImageIcon(getClass().getResource("batt100.gif"));
	    	box[i-1][j-1].setIcon(new ImageIcon(getClass().getResource("batt103.gif")));       	
	   
	}

//	
	void update(UserPlayer player)
	{
		for(int r = 0; r < 10; r++){
	        for(int c = 0; c < 10; c++){
	 	      // box[r][c].setIcon(new ImageIcon(getClass().getResource("batt100.gif")));
	 	      // box[r][c].setText();
	 	       if((player.board[r][c]).contains("*T"))
	 	       {
	 	    	  box[r][c].setIcon(new ImageIcon(getClass().getResource("batt1.gif")));
	 	       }
	 	       else if(player.board[r][c].contains("*E"))
	 	       {
	 	    	  box[r][c].setIcon(new ImageIcon(getClass().getResource("batt5.gif")));
	 	       }
	 	       else if(player.board[r][c].contains("*"))
	 	       {
	 	    	  box[r][c].setIcon(new ImageIcon(getClass().getResource("batt3.gif")));
	 	       }
	 	       
	 	      if(player.board[r][c].contains("&T"))
	 	       {
	 	    	  box[r][c].setIcon(new ImageIcon(getClass().getResource("batt6.gif")));
	 	       }
	 	       else if(player.board[r][c].contains("&E"))
	 	       {
	 	    	  box[r][c].setIcon(new ImageIcon(getClass().getResource("batt10.gif")));
	 	       }
	 	       else if(player.board[r][c].contains("&"))
	 	       {
	 	    	  box[r][c].setIcon(new ImageIcon(getClass().getResource("batt8.gif")));
	 	       }
	 	       
	 	      if(player.board[r][c].contains("0"))
	 	      {
	 	    	  box[r][c].setIcon(new ImageIcon(getClass().getResource("batt100.gif")));

	 	      }
	        }
	    }	
	
	}
		
	
	void close()
	{
		setVisible(false);
	}
	
	void setBoard()
	{
		JPanel main = new JPanel();
	    main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
	    JLabel label = new JLabel(" ", SwingConstants.CENTER);

        label.setFont(new Font("Serif", Font.PLAIN, 30));
	    


		
	    JPanel p = new JPanel();
	    p.setLayout(new GridLayout(10, 10));
	    
	    JPanel p2 = new JPanel();
	    p2.setLayout(new GridLayout(10, 10));
	    
	    
	    box = new JButton[10][10];
	    for(int r = 0; r < 10; r++){
	        for(int c = 0; c < 10; c++){
	           box[r][c]= new JButton();
	 	       box[r][c].setIcon(new ImageIcon(getClass().getResource("batt100.gif")));
	 	      
	 			p.add(box[r][c]);
	        }
	    }
	    
	    box2 = new JButton[10][10];
	    for(int r = 0; r < 10; r++){
	        for(int c = 0; c < 10; c++){
	           box2[r][c]= new JButton();
	 	       box2[r][c].setIcon(new ImageIcon(getClass().getResource("batt100.gif")));
	 	      
	 			p2.add(box2[r][c]);
	        }
	    }
	    setLocationRelativeTo(null);
	    
	    main.add(p);

        main.add(label,BorderLayout.CENTER);

	 //   main.add(Box.createVerticalStrut(20));

	    main.add(p2);
	    getContentPane().add(main);
	    


	
		setSize(2500,2500);
	    
	    pack();
	    setVisible(true);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}