package battleShip;

public class UserPlayer {
    // 2D array for hold the battleship info
    public char[][] board;
    // format: board[row][col]
    // the first index hold row, the second index hold col
    //
    // 'A' for Aircraft Carrier
    // 'B' for Battleship
    // 'D' for Destroyer
    // 'S' for Submarine
    // 'P' for Patrol Boat
    //
    // '0' means there is nothing at this position
    // '1' means this position was hitted, but nothing here
    //
    // use lowercase for hit part of ship, such as: 'a', 'b', 'd', 's', 'p'

    public int victory;
    // 1 for won, 0 for no one win yet, -1 for lose


    // Used for the begine of the game, just fill the whole board by '0'
    public UserPlayer() {
        // fill the board by 0 for no ship at the position
        board = new char[10][10];
    	
    	for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                board[i][j] = '0';
            }
        }

        victory = 0;
    }

    // if set the ship vertical, then input the top position of the setting position
    // if set the ship horizontal, then input the left position of the setting position
    public boolean setShip(char ship, int row, int col, int verOrHor) {
        // row: 0-9; col: 0-9; 1 for vertical, 2 for horizontal;
        // the position of the row and col is the top or left the ship position

    	for(int i = 0 ; i <10; i++)
    		for(int j=0 ; j <10; j++)
    			if(board[i][j]==ship)
    				return false;
    	
        if (ship == 'A') {
            if (verOrHor == 1 && row <= 5) {
                for (int i = row; i < row + 5; i++) {
                    if (board[i][col] == '0') {
                        board[i][col] = 'A';
                    } else {
                        return false;
                    }
                }
            } else if (verOrHor == 2 && col <= 5) {
                for (int j = col; j < col + 5; j++) {
                    if (board[row][j] == '0') {
                        board[row][j] = 'A';
                    } else {
                        return false;
                    }
                }
            } else {
                return false;
            }
        } else if (ship == 'B') {
            if (verOrHor == 1 && row <= 6) {
                for (int i = row; i < row + 4; i++) {
                    if (board[i][col] == '0') {
                        board[i][col] = 'B';
                    } else {
                        return false;
                    }
                }
            } else if (verOrHor == 2 && col <= 6) {
                for (int j = col; j < col + 4; j++) {
                    if (board[row][j] == '0') {
                        board[row][j] = 'B';
                    } else {
                        return false;
                    }
                }
            } else {
                return false;
            }
        } else if (ship == 'D') {
            if (verOrHor == 1 && row <= 7) {
                for (int i = row; i < row + 3; i++) {
                    if (board[i][col] == '0') {
                        board[i][col] = 'D';
                    } else {
                        return false;
                    }
                }
            } else if (verOrHor == 2 && col <= 7) {
                for (int j = col; j < col + 3; j++) {
                    if (board[row][j] == '0') {
                        board[row][j] = 'D';
                    } else {
                        return false;
                    }
                }
            } else {
                return false;
            }
        } else if (ship == 'S') {
            if (verOrHor == 1 && row <= 7) {
                for (int i = row; i < row + 3; i++) {
                    if (board[i][col] == '0') {
                        board[i][col] = 'S';
                    } else {
                        return false;
                    }
                }
            } else if (verOrHor == 2 && col <= 7) {
                for (int j = col; j < col + 3; j++) {
                    if (board[row][j] == '0') {
                        board[row][j] = 'S';
                    } else {
                        return false;
                    }
                }
            } else {
                return false;
            }
        } else if (ship == 'P') {
            if (verOrHor == 1 && row <= 8) {
                for (int i = row; i < row + 2; i++) {
                    if (board[i][col] == '0') {
                        board[i][col] = 'P';
                    } else {
                        return false;
                    }
                }
            } else if (verOrHor == 2 && col <= 8) {
                for (int j = col; j < col + 2; j++) {
                    if (board[row][j] == '0') {
                        board[row][j] = 'P';
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
    public char beHit(int row, int col) {

        char hit = board[row][col];

        if (hit == 'A' || hit == 'B' || hit == 'D' || hit == 'S' || hit == 'P') {
            board[row][col] += 32;
            return hit;
        } else if (hit == 'a' || hit == 'b' || hit == 'd' || hit == 's' || hit == 'p') {
            hit = 'F';     // F means this position already hit
            return hit;
        } else if (hit == '0') {
            board[row][col] = '1';
            return hit;
        } else if (hit == '1') {
            hit = 'F';     // F means this position already hit
            return hit;
        } else {
            return '~';     // in case error
        }
    }

    // input row and col to return the char in the position in the board
    public char getCharByPos(int row, int col) {

        if (row >= 0 && row <= 10 && col >= 0 && col <= 10)
            return board[row][col];
        else
            return '~';
    }

    // this method used for check if this user lose the game
    // return true for lose
    // return false for didn't lose
    public boolean checkLose() {
        int a = 0, b = 0, d = 0, s = 0, p = 0;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (board[i][j] == 'a')
                    a++;
                else if (board[i][j] == 'b')
                    b++;
                else if (board[i][j] == 'd')
                    d++;
                else if (board[i][j] == 's')
                    s++;
                else if (board[i][j] == 'p')
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
