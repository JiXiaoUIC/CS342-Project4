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
	


	public Game2(CommunicationThread communicationThread)
	{
		
		this.main = communicationThread;

	
		//backbox.startGame();
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
				
				
				//while(backbox.checkAllShipsWasSet(backbox.playerClient) == false){
					
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

				boolean a =setShip(type,Integer.parseInt(row)-1,Integer.parseInt(col)-1,Integer.parseInt(vertHor));
			
				if( a == false)
				{
					Object[] message4 = {
						    "You have failed to place the ship"
						};
					JOptionPane.showConfirmDialog(null, message4, "Message", JOptionPane.OK_CANCEL_OPTION);
				}
				else
				{
					update();
				
					if(checkAllShipsWasSet() == false)
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
				
				
				//while(backbox.checkAllShipsWasSet(backbox.playerClient) == false){
					
				Object[] message2 = {
					    "Type of Ship:\n A: Aircraft Carrier \n B: Battle Ship \n D: Destroyer \n S: Submarine \n P: Patrol Boat ", shipType,
					    "Row", shipRow,
					    "Col", shipCol
					};
				int option2 = JOptionPane.showConfirmDialog(null, message2, "Delete Ship", JOptionPane.OK_CANCEL_OPTION);
				String type = shipType.getText();
				String row = shipRow.getText();
				String col = shipCol.getText();

				boolean b = deleteShip(type,Integer.parseInt(row)-1,Integer.parseInt(col)-1);
			
				update();
	
			 }
			//}

			
		} // end anonymous inner class
		);
		
		
		

		

		JMenuItem Attack = new JMenuItem("Attack");
		Attack.addActionListener(new ActionListener() {
			// display message dialog when user selects About...
			public void actionPerformed(ActionEvent event) {
				
				
	
//				if(backbox.checkAllShipsWasSet(backbox.playerClient) == true &&backbox.checkAllShipsWasSet(backbox.playerServer) == true )	
//				{
					Object[] message = {
							"Please Select row/col to attack",
						    "Row", shipRow,
						    "Col", shipCol,
						};
					int option = JOptionPane.showConfirmDialog(null, message, "Attack", JOptionPane.OK_CANCEL_OPTION);
					String row = shipRow.getText();
					String col = shipCol.getText();
					//communicationThread.doSendMessage(Integer.parseInt(row)-1,Integer.parseInt(col)-1);	
					box2[Integer.parseInt(row)-1][Integer.parseInt(col)-1].setIcon(new ImageIcon(getClass().getResource("batt103.gif")));
					update();

			 
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
	  	                  + "Upper window is your box. You can place your ship and check a status of your ship.\n"
	  	                  + "Lower window is a box to let you know where you attacked.\n"
	  	                  +"\n"
	  	                  
	  	                  +"How to play :\n"
	  	                  +"1. Make a Server :\n"
	  	                  +"2. If you are a server user, wait for a client to join \n"
	  	                  +"3. If you are a client user, join to a server \n"
	  	                  +"4. place ships to the box, wait for both player to complete placing ships \n"
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
		
	

//	
	void update()
	{
		for(int r = 0; r < 10; r++){
	        for(int c = 0; c < 10; c++){
	 	      // box[r][c].setIcon(new ImageIcon(getClass().getResource("batt100.gif")));
	 	      // box[r][c].setText();
	        	if(box[r][c].getText().contains("a") || box[r][c].getText().contains("b")||box[r][c].getText().contains("d")||box[r][c].getText().contains("s")||box[r][c].getText().contains("p"))
		 	    {
		 	    	   if((box[r][c]).getText().contains("*T"))
			 	       {
			 	    	  box[r][c].setIcon(new ImageIcon(getClass().getResource("batt201.gif")));
			 	       }
			 	       else if(box[r][c].getText().contains("*E"))
			 	       {
			 	    	  box[r][c].setIcon(new ImageIcon(getClass().getResource("batt203.gif")));
			 	       }
			 	       else if(box[r][c].getText().contains("*"))
			 	       {
			 	    	  box[r][c].setIcon(new ImageIcon(getClass().getResource("batt202.gif")));
			 	       }
		 	    	  if(box[r][c].getText().contains("&T"))
			 	       {
			 	    	  box[r][c].setIcon(new ImageIcon(getClass().getResource("batt204.gif")));
			 	       }
			 	       else if(box[r][c].getText().contains("&E"))
			 	       {
			 	    	  box[r][c].setIcon(new ImageIcon(getClass().getResource("batt206.gif")));
			 	       }
			 	       else if(box[r][c].getText().contains("&"))
			 	       {
			 	    	  box[r][c].setIcon(new ImageIcon(getClass().getResource("batt205.gif")));
			 	       }
		 	      
		 	      
		 	    }
		        else{
		           if((box[r][c]).getText().contains("F"))
			 	   {
		        	   box[r][c].setIcon(new ImageIcon(getClass().getResource("batt102.gif")));
			 	   } 		        	
		        	
		           else if((box[r][c]).getText().contains("*T"))
		 	       {
		 	    	  box[r][c].setIcon(new ImageIcon(getClass().getResource("batt1.gif")));
		 	       }
		 	       else if(box[r][c].getText().contains("*E"))
		 	       {
		 	    	  box[r][c].setIcon(new ImageIcon(getClass().getResource("batt5.gif")));
		 	       }
		 	       else if(box[r][c].getText().contains("*"))
		 	       {
		 	    	  box[r][c].setIcon(new ImageIcon(getClass().getResource("batt3.gif")));
		 	       }
		 	       
		 	      if(box[r][c].getText().contains("&T"))
		 	       {
		 	    	  box[r][c].setIcon(new ImageIcon(getClass().getResource("batt6.gif")));
		 	       }
		 	       else if(box[r][c].getText().contains("&E"))
		 	       {
		 	    	  box[r][c].setIcon(new ImageIcon(getClass().getResource("batt10.gif")));
		 	       }
		 	       else if(box[r][c].getText().contains("&"))
		 	       {
		 	    	  box[r][c].setIcon(new ImageIcon(getClass().getResource("batt8.gif")));
		 	       }
		 	       
		 	      if(box[r][c].getText().contains("0"))
		 	      {
		 	    	  box[r][c].setIcon(new ImageIcon(getClass().getResource("batt100.gif")));
	
		 	      }
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
	           box[r][c].setText("0");
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

	
    public boolean setShip(String ship, int row, int col, int verOrHor) {
        // row: 0-9; col: 0-9; 1 for vertical, 2 for horizontal;
        // the position of the row and col is the top or left the ship position

    	for(int i = 0 ; i <10; i++)
    		for(int j=0 ; j <10; j++)
    			if(box[i][j].getText().contains(ship))
    				return false;
    	
        if (ship.contains("A")) {
            if (verOrHor == 1 && row <= 5) {
                for (int i = row; i < row + 5; i++) {
                    if (box[i][col].getText() == "0") {
                       if(i == row+4)
                    	   box[i][col].setText("A"+"&"+"E");
                       else if( i == row)
                   		box[i][col].setText("A"+"&"+"T");
                       else
                    	   box[i][col].setText("A"+"&"+i);
                    } else {
                        return false;
                    }
                }
            } else if (verOrHor == 2 && col <= 5) {
                for (int j = col; j < col + 5; j++) {
                    if (box[row][j].getText() == "0") {
                    	if(j == col+4)
                     	   box[row][j].setText("A"+"*"+"E");
                    	else if( j == col)
                       		box[row][j].setText("A"+"*"+"T");
                     	else
                     		box[row][j].setText("A"+"*"+j);
                    } else {
                        return false;
                    }
                }
            } else {
                return false;
            }
        } else if (ship.contains("B")) {
            if (verOrHor == 1 && row <= 6) {
                for (int i = row; i < row + 4; i++) {
                    if (box[i][col].getText() == "0") {
                    	if(i == row+3)
                     	   box[i][col].setText("B"+"&"+"E");
                    	else if( i == row)
                    		box[i][col].setText("B"+"&"+"T");
                    	else
                     		box[i][col].setText("B"+"&"+i);
                    } else {
                        return false;
                    }
                }
            } else if (verOrHor == 2 && col <= 6) {
                for (int j = col; j < col + 4; j++) {
                    if (box[row][j].getText() == "0") {
                    	if(j == col+3)
                      	   box[row][j].setText("B"+"*"+"E");
                    	else if( j == col)
                       		box[row][j].setText("B"+"*"+"T");
                    	else
                    		box[row][j].setText("B"+"*"+j);
                    } else {
                        return false;
                    }
                }
            } else {
                return false;
            }
        } else if (ship.contains("D")) {
            if (verOrHor == 1 && row <= 7) {
                for (int i = row; i < row + 3; i++) {
                    if (box[i][col].getText() == "0") {
                    	if(i == row+2)
                     	   box[i][col].setText("D"+"&"+"E");
                    	else if( i == row)
                       		box[i][col].setText("D"+"&"+"T");
                     	else
                     		box[i][col].setText("D"+"&"+i);
                    } else {
                        return false;
                    }
                }
            } else if (verOrHor == 2 && col <= 7) {
                for (int j = col; j < col + 3; j++) {
                    if (box[row][j].getText() == "0") {
                    	if(j == col+2)
                       	   box[row][j].setText("D"+"*"+"E");
                    	else if( j == col)
                       		box[row][j].setText("D"+"*"+"T");
                     	else
                     		box[row][j].setText("D"+"*"+j);
                    } else {
                        return false;
                    }
                }
            } else {
                return false;
            }
        } else if (ship.contains("S")) {
            if (verOrHor == 1 && row <= 7) {
                for (int i = row; i < row + 3; i++) {
                    if (box[i][col].getText() == "0") {
                    	if(i == row+2)
                     	   box[i][col].setText("S"+"&"+"E");
                    	else if( i == row)
                       		box[i][col].setText( "S"+"&"+"T");
                     	else
                     		box[i][col].setText("S"+"&"+i);
                    } else {
                        return false;
                    }
                }
            } else if (verOrHor == 2 && col <= 7) {
                for (int j = col; j < col + 3; j++) {
                    if (box[row][j].getText()=="0") {
                    	if(j == col+2)
                       	   box[row][j].setText("S"+"*"+"E");
                    	else if( j == col)
                       		box[row][j].setText("S"+"*"+"T");
                     	else
                     		box[row][j].setText("S"+"*"+j);
                    } else {
                        return false;
                    }
                }
            } else {
                return false;
            }
        } else if (ship.contains("P")) {
            if (verOrHor == 1 && row <= 8) {
                for (int i = row; i < row + 2; i++) {
                    if (box[i][col].getText() == "0") {
                    	if(i == row+1)
                     	   box[i][col].setText("P"+"&"+"E");
                    	else
                       		box[i][col].setText("P"+"&"+"T");
                    } else {
                        return false;
                    }
                }
            } else if (verOrHor == 2 && col <= 8) {
                for (int j = col; j < col + 2; j++) {
                    if (box[row][j].getText() == "0") {
                    	if(j == col+1)
                       	   box[row][j].setText("P"+"*"+"E");
                     	else
                     		box[row][j].setText("P"+"*"+"T");
                    } else {
                        return false;
                    }
                }
            } else {
                return false;
            }
        } else {
            return false;
        }

        // success to set a ship
        return true;
    }
    
    public void beHit(int row, int col) {

        String hit = box[row][col].getText();

        if (hit.contains("A"))
        {
        	box[row][col].setText( "a" + box[row][col].getText().substring(1, 3)); 
        }
        else if (hit.contains("B"))
        {
        	box[row][col].setText( "b" + box[row][col].getText().substring(1, 3)); 
        } 
        else if (hit.contains("D"))
        {
        	box[row][col].setText( "d" + box[row][col].getText().substring(1, 3));    
        } 
        else if (hit.contains("S"))
        {
        	box[row][col].setText( "s" + box[row][col].getText().substring(1, 3));       
        } 
        else if (hit.contains("P"))
        {
        	box[row][col].setText( "p" + box[row][col].getText().substring(1, 3));        
        } 
        else if (hit == "0") {
            box[row][col].setText("F");
        }
        
        update();
    }


    public boolean checkAllShipsWasSet() {
        int checkA = 0;
        int checkB = 0;
        int checkD = 0;
        int checkS = 0;
        int checkP = 0;

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (box[i][j].getText().contains("A"))
                    checkA++;
                else if (box[i][j].getText().contains("B"))
                    checkB++;
                else if (box[i][j].getText().contains("D"))
                    checkD++;
                else if (box[i][j].getText().contains("S"))
                    checkS++;
                else if (box[i][j].getText().contains("P"))
                    checkP++;
            }
        }

        // if all ship place finish, return true
        if (checkA == 5 && checkB == 4 && checkD == 3 && checkS == 3 && checkP == 2)
            return true;

        // else return false
        return false;
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public boolean deleteShip( String ship, int row, int col) {
        // in case wrong String input, return false, fail to place ship
        if (!ship.contains("A") && !ship.contains("B") && !ship.contains("D") && !ship.contains("S") && !ship.contains("P"))
            return false;

        boolean shipReplace = false;

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (box[i][j].getText().contains(ship))
                {
                    shipReplace = true;
                    box[i][j].setText("0");
                }
            }
        }

        if (shipReplace == false)
            return false;

        return true;
    }
}