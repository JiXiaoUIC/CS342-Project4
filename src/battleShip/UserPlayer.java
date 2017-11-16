package battleShip;

public class UserPlayer {
    // 2D array for hold the battleship info
    public String[][] board;
    // format: board[row][col]
    // the first index hold row, the second index hold col
    //
    // "A" for Aircraft Carrier
    // "B" for Battleship
    // "D" for Destroyer
    // "S" for Submarine
    // "P" for Patrol Boat
    //
    // "0" means there is nothing at this position
    // "1" means this position was hitted, but nothing here
    //
    // use lowercase for hit part of ship, such as: "a", "b", "d", "s", "p"

    public int victory;
    // 1 for won, 0 for no one win yet, -1 for lose


    // Used for the begine of the game, just fill the whole board by "0"
    public UserPlayer() {
        // fill the board by 0 for no ship at the position
        board = new String[10][10];
    	
    	for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                board[i][j] = "0";
            }
        }

        victory = 0;
    }

    // if set the ship vertical, then input the top position of the setting position
    // if set the ship horizontal, then input the left position of the setting position
    public boolean setShip(String ship, int row, int col, int verOrHor) {
        // row: 0-9; col: 0-9; 1 for vertical, 2 for horizontal;
        // the position of the row and col is the top or left the ship position

    	for(int i = 0 ; i <10; i++)
    		for(int j=0 ; j <10; j++)
    			if(board[i][j].contains(ship))
    				return false;
    	
        if (ship.contains("A")) {
            if (verOrHor == 1 && row <= 5) {
                for (int i = row; i < row + 5; i++) {
                    if (board[i][col] == "0") {
                       if(i == row+4)
                    	   board[i][col] = "A"+"&"+"E";
                       else if( i == row)
                   		board[i][col] = "A"+"&"+"T";
                       else
                    	   board[i][col] = "A"+"&"+i;
                    } else {
                        return false;
                    }
                }
            } else if (verOrHor == 2 && col <= 5) {
                for (int j = col; j < col + 5; j++) {
                    if (board[row][j] == "0") {
                    	if(j == col+4)
                     	   board[row][j] = "A"+"*"+"E";
                    	else if( j == col)
                       		board[row][j] = "A"+"*"+"T";
                     	else
                     		board[row][j] = "A"+"*"+j;
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
                    if (board[i][col] == "0") {
                    	if(i == row+3)
                     	   board[i][col] = "B"+"&"+"E";
                    	else if( i == row)
                    		board[i][col] = "B"+"&"+"T";
                    	else
                     		board[i][col] = "B"+"&"+i;
                    } else {
                        return false;
                    }
                }
            } else if (verOrHor == 2 && col <= 6) {
                for (int j = col; j < col + 4; j++) {
                    if (board[row][j] == "0") {
                    	if(j == col+3)
                      	   board[row][j] = "B"+"*"+"E";
                    	else if( j == col)
                       		board[row][j] = "B"+"*"+"T";
                    	else
                    		board[row][j] = "B"+"*"+j;
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
                    if (board[i][col] == "0") {
                    	if(i == row+2)
                     	   board[i][col] = "D"+"&"+"E";
                    	else if( i == row)
                       		board[i][col] = "D"+"&"+"T";
                     	else
                     		board[i][col] = "D"+"&"+i;
                    } else {
                        return false;
                    }
                }
            } else if (verOrHor == 2 && col <= 7) {
                for (int j = col; j < col + 3; j++) {
                    if (board[row][j] == "0") {
                    	if(j == col+2)
                       	   board[row][j] = "D"+"*"+"E";
                    	else if( j == col)
                       		board[row][j] = "D"+"*"+"T";
                     	else
                     		board[row][j] = "D"+"*"+j;
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
                    if (board[i][col] == "0") {
                    	if(i == row+2)
                     	   board[i][col] = "S"+"&"+"E";
                    	else if( i == row)
                       		board[i][col] = "S"+"&"+"T";
                     	else
                     		board[i][col] = "S"+"&"+i;
                    } else {
                        return false;
                    }
                }
            } else if (verOrHor == 2 && col <= 7) {
                for (int j = col; j < col + 3; j++) {
                    if (board[row][j] == "0") {
                    	if(j == col+2)
                       	   board[row][j] = "S"+"*"+"E";
                    	else if( j == col)
                       		board[row][j] = "S"+"*"+"T";
                     	else
                     		board[row][j] = "S"+"*"+j;
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
                    if (board[i][col] == "0") {
                    	if(i == row+1)
                     	   board[i][col] = "P"+"&"+"E";
                    	else
                       		board[i][col] = "P"+"&"+"T";
                    } else {
                        return false;
                    }
                }
            } else if (verOrHor == 2 && col <= 8) {
                for (int j = col; j < col + 2; j++) {
                    if (board[row][j] == "0") {
                    	if(j == col+1)
                       	   board[row][j] = "P"+"*"+"E";
                     	else
                     		board[row][j] = "P"+"*"+"T";
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

    // input row and col to set the position was hit
    // for example: if opponent shot (2,4), then call beHit(2,4), then the board[2][4] was hit
    public String beHit(int row, int col) {

        String hit = board[row][col];

        if (hit.contains("A") || hit.contains("B") || hit.contains("D") || hit.contains("S") || hit.contains("P")) {
            board[row][col] += 32;
            return hit;
        } else if (hit.contains("a") || hit.contains("b") || hit.contains("d") || hit.contains("s") || hit.contains("p")) {
            hit = "F";     // F means this position already hit
            return hit;
        } else if (hit == "0") {
            board[row][col] = "1";
            return hit;
        } else if (hit == "1") {
            hit = "F";     // F means this position already hit
            return hit;
        } else {
            return "~";     // in case error
        }
    }

    // input row and col to return the char in the position in the board
    public String getCharByPos(int row, int col) {

        if (row >= 0 && row <= 10 && col >= 0 && col <= 10)
            return board[row][col];
        else
            return "~";
    }

    // this method used for check if this user lose the game
    // return true for lose
    // return false for didn"t lose
    public boolean checkLose() {
        int a = 0, b = 0, d = 0, s = 0, p = 0;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (board[i][j].contains("a"))
                    a++;
                else if (board[i][j].contains("b"))
                    b++;
                else if (board[i][j].contains("d"))
                    d++;
                else if (board[i][j].contains("s"))
                    s++;
                else if (board[i][j].contains("p"))
                    p++;
            }
        }

        // if all ship was hit, this player lose, return true
        if (a == 5 && b == 4 && d == 3 && s == 3 && p == 2)
            return true;

        // otherwise, this player not lose yet, return false
        return false;
    }
}
