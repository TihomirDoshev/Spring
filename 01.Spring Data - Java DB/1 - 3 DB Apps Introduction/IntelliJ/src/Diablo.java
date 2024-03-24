
import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class Diablo {
    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter username default (root): ");
        String user = sc.nextLine();
        user = user.equals("") ? "root" : user;
        System.out.println();

        System.out.print("Enter password default (empty):");
        String password = sc.nextLine().trim();
        System.out.println();

        Properties props = new Properties();
        props.setProperty("user", user);
        props.setProperty("password", password);

        Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/diablo", props);

        PreparedStatement stmt =
                connection.prepareStatement("SELECT u.first_name, u.last_name, COUNT(ug.game_id) " +
                        "FROM users as u " +
                        "JOIN users_games AS ug ON u.id = ug.user_id " +
                        "WHERE u.user_name = ?" +
                        "GROUP BY u.id;");

        String userName = sc.nextLine();
        stmt.setString(1, userName);
        ResultSet result = stmt.executeQuery();

        if (result.next()){
            System.out.printf("User: %s\n" +
                    "%s %s has played %d games\n",userName,result.getString("u.first_name")
            ,result.getString("u.last_name"),result.getInt("COUNT(ug.game_id)"));
        }else {
            System.out.println("No such user exists");
        }
        connection.close();
    }
}
