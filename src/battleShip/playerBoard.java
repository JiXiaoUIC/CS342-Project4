package battleShip;

public class playerBoard {
    // 2D array for hold the battleship info
    private char[][] board; // board[row][col]
    /*
    A for Aircraft Carrier
    B for Battleship
    D for Destroyer
    S for Submarine
    P for Patrol Boat

    use lowercase for hitted ship
     */



    public playerBoard() {
        // fill the board by 0 for no ship at the position
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                board[i][j] = '0';
            }
        }
    }

    public boolean setShip(char ship, int row, int col, int verOrHor) {
        // row: 1-10; col: 1-10; 1 for vertical, 2 for horizontal;
        // the position of the row and col is the top or left the ship position

        if (ship == 'A') {
            if (verOrHor == 1 && row <= 6) {
                for (int i = row; i < row + 5; i++) {
                    if (board[i][col] == '0') {
                        board[i][col] = 'A';
                    } else {
                        return false;
                    }
                }
            } else if (verOrHor == 2 && col <= 6) {
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
            if (verOrHor == 1 && row <= 7) {
                for (int i = row; i < row + 4; i++) {
                    if (board[i][col] == '0') {
                        board[i][col] = 'B';
                    } else {
                        return false;
                    }
                }
            } else if (verOrHor == 2 && col <= 7) {
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
            if (verOrHor == 1 && row <= 8) {
                for (int i = row; i < row + 3; i++) {
                    if (board[i][col] == '0') {
                        board[i][col] = 'D';
                    } else {
                        return false;
                    }
                }
            } else if (verOrHor == 2 && col <= 8) {
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
            if (verOrHor == 1 && row <= 8) {
                for (int i = row; i < row + 3; i++) {
                    if (board[i][col] == '0') {
                        board[i][col] = 'S';
                    } else {
                        return false;
                    }
                }
            } else if (verOrHor == 2 && col <= 8) {
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
            if (verOrHor == 1 && row <= 9) {
                for (int i = row; i < row + 2; i++) {
                    if (board[i][col] == '0') {
                        board[i][col] = 'P';
                    } else {
                        return false;
                    }
                }
            } else if (verOrHor == 2 && col <= 9) {
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

    public char beShot(int row, int col) {

        char shot = board[row][col];

        if (shot == 'A' || shot == 'B' || shot == 'D' || shot == 'S' || shot == 'P') {
            board[row][col] += 32;
            return shot;
        } else if (shot == 'a' || shot == 'b' || shot == 'd' || shot == 's' || shot == 'p') {
            shot = 'F';     // F means this position already shot
            return shot;
        } else if (shot == '0') {
            board[row][col] = '1';
            return shot;
        } else if (shot == '1') {
            shot = 'F';     // F means this position already shot
            return shot;
        } else {
            return 'z';     // in case error
        }
    }

    public char getBoardByPos(int row, int col) {
        return board[row][col];
    }
}




