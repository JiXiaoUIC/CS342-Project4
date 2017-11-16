package battleShip;

public class BoardBack {

    public int winner;
    // 1 for server win
    // 2 for client win
    // 0 for no winner yet

    public UserPlayer playerServer;
    public UserPlayer playerClient;

    public BoardBack() {
        // set no one won the game yet
        winner = 0;

        // initialize the two player objects
        playerServer = new UserPlayer();
        playerClient = new UserPlayer();
    }

    // this function used for restart the game, it clean all the ship on board for both player
    public void startGame() {
        playerServer = new UserPlayer();
        playerClient = new UserPlayer();
    }

    // placeShip(player, ship type, row num, col num, 1 for vertical / 2 for horizontal)
    public boolean placeShip(UserPlayer player, char ship, int row, int col, int verOrHor) {
        // in case wrong char input, return false, fail to place ship
        if (ship != 'A' && ship != 'B' && ship != 'D' && ship != 'S' && ship != 'P')
            return false;

        // place ship
        boolean placeFinish = player.setShip(ship, row, col, verOrHor);
        return placeFinish;
    }

    // this methos can only use after ship placed and before the game start
    public boolean deleteShip(UserPlayer player, char ship, int row, int col) {
        // in case wrong char input, return false, fail to place ship
        if (ship != 'A' && ship != 'B' && ship != 'D' && ship != 'S' && ship != 'P')
            return false;

        boolean shipReplace = false;

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (player.board[i][j] == ship)
                {
                    shipReplace = true;
                    player.board[i][j] = '0';
                }
            }
        }

        if (shipReplace == false)
            return false;

        return true;
    }

    public boolean replaceShip(UserPlayer player, char ship, int row, int col, int verOrHor) {
        // delete ship
        boolean shipDeleted;
        shipDeleted = deleteShip(player, ship, row, col);
        if (shipDeleted == false)
            return false;   // in case it did not successful delete ship

        // place ship
        boolean shipPlaced;
        shipPlaced = placeShip(player, ship, row, col, verOrHor);
        if (shipPlaced == false)
            return false;   // in case it did not successful place ship

        return true;
    }

    // this methos can only use before battle start
    public boolean checkAllShipsWasSet(UserPlayer player) {
        int checkA = 0;
        int checkB = 0;
        int checkD = 0;
        int checkS = 0;
        int checkP = 0;

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (player.board[i][j] == 'A')
                    checkA++;
                else if (player.board[i][j] == 'B')
                    checkB++;
                else if (player.board[i][j] == 'D')
                    checkD++;
                else if (player.board[i][j] == 'S')
                    checkS++;
                else if (player.board[i][j] == 'P')
                    checkP++;
            }
        }

        // if all ship place finish, return true
        if (checkA == 5 && checkB == 4 && checkD == 3 && checkS == 3 && checkP == 2)
            return true;

        // else return false
        return false;
    }

    // this methos used for server attack client,
    // that means client is hit by server
    public char serverAttack(int row, int col) {
        return playerClient.beHit(row, col);
    }

    // this methos used for client attack server,
    // that means server is hit by client
    public char clientAttack(int row, int col) {
        return playerServer.beHit(row, col);
    }

    public void nuclearBomb(UserPlayer attacker, UserPlayer playerBeHit) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (playerBeHit.board[i][j] == '0' || playerBeHit.board[i][j] == '1')
                    playerBeHit.board[i][j] = '1';
                else if (playerBeHit.board[i][j] == 'A' || playerBeHit.board[i][j] == 'B'
                        || playerBeHit.board[i][j] == 'D' || playerBeHit.board[i][j] == 'S'
                        || playerBeHit.board[i][j] == 'P')
                    playerBeHit.board[i][j] += 32;
            }
        }

        // the attacker won
        attacker.victory = 1;
        playerBeHit.victory = -1;
    }

    // this method should be use after every time that a user do something
    public boolean checkGameFinish() {
        if (playerServer.victory == 1 || playerClient.victory == -1) {
            winner = 1;
            return true;
        } else if (playerClient.victory == 1 || playerServer.victory == -1) {
            winner = 2;
            return true;
        }

        // else no one won or lose, return false;
        return false;
    }

    public char getBoardByPos(UserPlayer player, int row, int col) {
        if (row >= 0 && row <= 9 && col >= 0 && col <= 9)
            return player.board[row][col];

        // in case the unavailable position
        return '~';
    }

}