package battleShip;

public class BoardBack {

    public int winner;
    // 1 for server win
    // 2 for client win
    // 0 for no winner yet

    public PlayerBoard playerServer;
    public PlayerBoard playerClient;

    public BoardBack() {
        // set no one won the game yet
        winner = 0;

        // initialize the two player objects
        playerServer = new PlayerBoard();
        playerClient = new PlayerBoard();
    }

    public void startGame() {
        playerServer = new PlayerBoard();
        playerClient = new PlayerBoard();
    }

    public boolean placeShip(PlayerBoard player, char ship, int row, int col, int verOrHor) {
        boolean placeFinish = player.setShip(ship, row, col, verOrHor);
        return placeFinish;
    }

    // this methos can only use before battle start
    public boolean allShipsWasSet(PlayerBoard player) {
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

    public void nuclearBomb(PlayerBoard attacker, PlayerBoard playerBeHit) {
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
        if (playerServer.victory == 1) {
            winner = 1;
            return true;
        } else if (playerClient.victory == 1) {
            winner = 2;
            return true;
        }

        return false;
    }


}
