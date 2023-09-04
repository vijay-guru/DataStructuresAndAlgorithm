import java.util.Scanner;

public class TicTacToe {
    private char[][] board;
    private char currentPlayer;
    private int boardSize;

    public TicTacToe(int size) {
        boardSize = size;
        board = new char[boardSize][boardSize];
        currentPlayer = 'X';
        initializeBoard();
    }

    public void initializeBoard() {
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                board[i][j] = ' ';
            }
        }
    }

    public void printBoard() {
        System.out.println("Tic-Tac-Toe Board:");
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                System.out.print(board[i][j]);
                if (j < boardSize - 1) {
                    System.out.print(" | ");
                }
            }
            System.out.println();
            if (i < boardSize - 1) {
                for (int j = 0; j < boardSize * 4 - 1; j++) {
                    System.out.print("-");
                }
                System.out.println();
            }
        }
    }

    public boolean makeMove(int row, int col) {
        if (row < 0 || row >= boardSize || col < 0 || col >= boardSize || board[row][col] != ' ') {
            return false;
        }
        board[row][col] = currentPlayer;
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
        return true;
    }

    public boolean isGameOver() {
        return isWin() || isDraw();
    }

    public boolean isWin() {
        return checkRows() || checkColumns() || checkDiagonals();
    }

    public boolean checkRows() {
        for (int i = 0; i < boardSize; i++) {
            if (board[i][0] != ' ' && allEqual(board[i])) {
                return true;
            }
        }
        return false;
    }

    public boolean checkColumns() {
        for (int j = 0; j < boardSize; j++) {
            char[] column = new char[boardSize];
            for (int i = 0; i < boardSize; i++) {
                column[i] = board[i][j];
            }
            if (column[0] != ' ' && allEqual(column)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkDiagonals() {
        char[] diagonal1 = new char[boardSize];
        char[] diagonal2 = new char[boardSize];
        for (int i = 0; i < boardSize; i++) {
            diagonal1[i] = board[i][i];
            diagonal2[i] = board[i][boardSize - 1 - i];
        }
        return (diagonal1[0] != ' ' && allEqual(diagonal1)) || (diagonal2[0] != ' ' && allEqual(diagonal2));
    }

    public boolean isDraw() {
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                if (board[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean allEqual(char[] arr) {
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] != arr[0]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the size of the Tic-Tac-Toe board (e.g., 3 for 3x3): ");
        int boardSize = scanner.nextInt();

        TicTacToe game = new TicTacToe(boardSize);

        while (!game.isGameOver()) {
            game.printBoard();
            System.out.println("Player " + game.getCurrentPlayer() + ", make your move (row and column):");
            int row = scanner.nextInt();
            int col = scanner.nextInt();

            if (game.makeMove(row, col)) {
                System.out.println("Valid move!");
            } else {
                System.out.println("Invalid move! Try again.");
            }
        }

        game.printBoard();

        if (game.isWin()) {
            System.out.println("Player " + game.getCurrentPlayer() + " wins!");
        } else {
            System.out.println("It's a draw!");
        }

        scanner.close();
    }

    public char getCurrentPlayer() {
        return currentPlayer;
    }
}
