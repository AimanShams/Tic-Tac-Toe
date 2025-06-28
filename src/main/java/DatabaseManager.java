import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class DatabaseManager {
    private static final String URL = "jdbc:mysql://localhost:3306/tic_tac_toe_db";
    private static final String USER = "root";
    private static final String PASSWORD = "V6y0N8";

    public void saveResult(String player1, String player2, String winner) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sql = "INSERT INTO game_results (player1, player2, winner) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, player1);
            stmt.setString(2, player2);
            stmt.setString(3, winner);
            stmt.executeUpdate();
            System.out.println("Game result saved to database.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
    

