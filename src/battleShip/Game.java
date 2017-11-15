package battleShip;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;



public class Game extends JFrame implements ActionListener{
	private Main main;
	private JPanel container;
	private JPanel panel2;

	private JPanel panel[][];
	private String click;

	private MyButton cell[][];
	private JButton box[][];
	private boolean isClicked = false;
	private JTextField shipType = new JTextField();
	private JTextField shipRow = new JTextField();
	private JTextField shipCol = new JTextField();
	private JTextField vertOrHor = new JTextField();
	


	public Game(Main main)
	{
		
		this.main = main;
		SetBoard();
		//f.add(menubar);
		//setShip('A',2,1,1);
	}

	
	void SetBoard()
	{

		//JFrame f = new JFrame("Window containing a matrix");
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
		JMenuBar menubar = new JMenuBar();
		setJMenuBar(menubar);
		JMenu Game = new JMenu("Game");
		JMenuItem placeShip = new JMenuItem("Place Ships");

		placeShip.addActionListener(new ActionListener() {
			// display message dialog when user selects About...
			public void actionPerformed(ActionEvent event) {
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

				setShip(type.charAt(0),Integer.parseInt(row),Integer.parseInt(col),Integer.parseInt(vertHor));

				

			}
		} // end anonymous inner class
		);
		Game.add(placeShip);
		menubar.add(Game);

	
		setSize(400,400);
	    
	    pack();
	    setVisible(true);
	}

	
	
	public boolean setShip(char ship, int row, int col, int verOrHor) {
        // row: 1-10; col: 1-10; 1 for vertical, 2 for horizontal;
        // the position of the row and col is the top or left the ship position

        if (ship == 'A') {
            if (verOrHor == 1 && row <= 6) {
                for (int i = row; i < row + 5; i++) {
                    if (box[i][col].getText()=="0") {
                    	box[i][col].setText("A");
                    } else {
                        return false;
                    }
                }
            } else if (verOrHor == 2 && col <= 6) {
                for (int j = col; j < col + 5; j++) {
                    if (box[row][j].getText()=="0") {
                    	box[row][j].setText("A");
                    } else {
                        return false;
                    }
                }
            } else {
                return false;
            }
        } else if (ship == 'B') {
            if (verOrHor == 1 && row <= 7) {
                for (int i = row; i < row + 4; i++) {
                    if (box[i][col].getText()=="0") {
                    	box[i][col].setText("B");
                    } else {
                        return false;
                    }
                }
            } else if (verOrHor == 2 && col <= 7) {
                for (int j = col; j < col + 4; j++) {
                    if (box[row][j].getText()=="0") {
                    	box[row][j].setText("B");
                    } else {
                        return false;
                    }
                }
            } else {
                return false;
            }
        } else if (ship == 'D') {
            if (verOrHor == 1 && row <= 8) {
                for (int i = row; i < row + 3; i++) {
                    if (box[i][col].getText()=="0") {
                    	box[i][col].setText("D");
                    } else {
                        return false;
                    }
                }
            } else if (verOrHor == 2 && col <= 8) {
                for (int j = col; j < col + 3; j++) {
                    if (box[row][j].getText()=="0") {
                    	box[row][j].setText("D");
                    } else {
                        return false;
                    }
                }
            } else {
                return false;
            }
        } else if (ship == 'S') {
            if (verOrHor == 1 && row <= 8) {
                for (int i = row; i < row + 3; i++) {
                    if (box[i][col].getText()=="0") {
                    	box[i][col].setText("S");
                    } else {
                        return false;
                    }
                }
            } else if (verOrHor == 2 && col <= 8) {
                for (int j = col; j < col + 3; j++) {
                    if (box[row][j].getText()=="0") {
                    	box[row][j].setText("S");
                    } else {
                        return false;
                    }
                }
            } else {
                return false;
            }
        } else if (ship == 'P') {
            if (verOrHor == 1 && row <= 9) {
                for (int i = row; i < row + 2; i++) {
                    if (box[i][col].getText()=="0") {
                    	box[i][col].setText("P");
                    } else {
                        return false;
                    }
                }
            } else if (verOrHor == 2 && col <= 9) {
                for (int j = col; j < col + 2; j++) {
                    if (box[row][j].getText()=="0") {
                    	box[row][j].setText("P");
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

//	  public char beHit(int row, int col) {
//
//	        char hit = box[row][col].getText().charAt(0);
//
//	        if (hit == 'A' || hit == 'B' || hit == 'D' || hit == 'S' || hit == 'P') {
//	            hit += 32;
//	            
//	            return hit;
//	        } else if (hit == 'a' || hit == 'b' || hit == 'd' || hit == 's' || hit == 'p') {
//	            hit = 'F';     // F means this position already hit
//	            return hit;
//	        } else if (hit == '0') {
//	            box[row][col] = '1';
//	            return hit;
//	        } else if (hit == '1') {
//	            hit = 'F';     // F means this position already hit
//	            return hit;
//	        } else {
//	            return '~';     // in case error
//	        }
//	    }
	
	


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	class MyButton extends JButton
	{
		private int X;
		private int Y;
		
		public MyButton(int x, int y)
		{
			X = x;
			Y = y;
		}
		
		public int getX()
		{
			return X;
		}
		public int getY()
		{
			return Y;
		}
	}
	


}