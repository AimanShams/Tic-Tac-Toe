import java.util.Scanner;

public class Game {
    private Player player1;
    private Player player2;
    private Board board;
    private Scanner scanner;
    private DatabaseManager dbManager;

    public Game() {
        scanner = new Scanner(System.in);
        board = new Board();
        dbManager = new DatabaseManager();
    }

    public void start() {
        System.out.print("Enter Player 1 name: ");
        String name1 = scanner.nextLine();
        player1 = new Player(name1, 'X');

        System.out.print("Enter Player 2 name: ");
        String name2 = scanner.nextLine();
        player2 = new Player(name2, 'O');

        Player currentPlayer = player1;
        board.display();

        while (true) {
            System.out.println(currentPlayer.getName() + "'s turn (" + currentPlayer.getSymbol() + ")");
            System.out.print("Enter row (0-2): ");
            int row = scanner.nextInt();
            System.out.print("Enter column (0-2): ");
            int col = scanner.nextInt();

            if (board.makeMove(row, col, currentPlayer.getSymbol())) {
                board.display();
                if (board.checkWin(currentPlayer.getSymbol())) {
                    System.out.println(currentPlayer.getName() + " wins!");
                    dbManager.saveResult(player1.getName(), player2.getName(), currentPlayer.getName());
                    break;
                } else if (board.isFull()) {
                    System.out.println("It's a draw!");
                    dbManager.saveResult(player1.getName(), player2.getName(), "Draw");
                    break;
                }
                currentPlayer = (currentPlayer == player1) ? player2 : player1;
            } else {
                System.out.println("Invalid move. Try again.");
            }
        }
    }
}

    

