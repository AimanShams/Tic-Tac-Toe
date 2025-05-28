import java.util.Scanner;

public class TicTacToe {
    private char[][] board;
    private final int SIZE = 3;
    private char currentPlayer;

    public TicTacToe() {
        board = new char[SIZE][SIZE];
        initializeBoard();
        currentPlayer = 'X';
    }

    private void initializeBoard() {
        for (int i = 0; i < SIZE; i++)
            for (int j = 0; j < SIZE; j++)
                board[i][j] = '-';
    }

    public void printBoard() {
        System.out.println("Current Board:");
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++)
                System.out.print(board[i][j] + " ");
            System.out.println();
        }
    }

    public boolean placeMark(int row, int col) {
        if (row >= 0 && row < SIZE && col >= 0 && col < SIZE && board[row][col] == '-') {
            board[row][col] = currentPlayer;
            return true;
        }
        return false;
    }

    public boolean checkWin() {
        for (int i = 0; i < SIZE; i++) {
            if ((board[i][0] == currentPlayer &&
                 board[i][1] == currentPlayer &&
                 board[i][2] == currentPlayer) ||
                (board[0][i] == currentPlayer &&
                 board[1][i] == currentPlayer &&
                 board[2][i] == currentPlayer)) {
                return true;
            }
        }

        if ((board[0][0] == currentPlayer &&
             board[1][1] == currentPlayer &&
             board[2][2] == currentPlayer) ||
            (board[0][2] == currentPlayer &&
             board[1][1] == currentPlayer &&
             board[2][0] == currentPlayer)) {
            return true;
        }

        return false;
    }

    public boolean isBoardFull() {
        for (int i = 0; i < SIZE; i++)
            for (int j = 0; j < SIZE; j++)
                if (board[i][j] == '-')
                    return false;
        return true;
    }

    public void changePlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    public char getCurrentPlayer() {
        return currentPlayer;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TicTacToe game = new TicTacToe();

        System.out.println("Welcome to Tic Tac Toe!");

        while (true) {
            game.printBoard();
            System.out.println("Player " + game.getCurrentPlayer() + ", enter your move (row and column): ");
            
            int row, col;
            try {
                row = scanner.nextInt();
                col = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Invalid input! Please enter numeric values.");
                scanner.nextLine(); // Clear the invalid input
                continue;
            }

            if (!game.placeMark(row, col)) {
                System.out.println("Invalid move! Try again.");
                continue;
            }

            if (game.checkWin()) {
                game.printBoard();
                System.out.println("Player " + game.getCurrentPlayer() + " wins!");
                break;
            }

            if (game.isBoardFull()) {
                game.printBoard();
                System.out.println("It's a draw!");
                break;
            }

            game.changePlayer();
        }

        scanner.close();
    }
}
