import java.sql.*;
import java.util.Properties;
import java.util.Scanner;


public class _03_GetMinionNames {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        Properties properties = new Properties();
        properties.setProperty("user", "root");
        properties.setProperty("password", "");

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/minions_db", properties);

        int villainId = Integer.parseInt(scanner.nextLine());

        PreparedStatement villainStatement = connection.prepareStatement(
                "select name from villains\n" +
                        "where id = ?;");

        villainStatement.setInt(1, villainId);

        ResultSet villainSet = villainStatement.executeQuery();

        if (!villainSet.next()) {
            System.out.printf("No villain with ID %d exists in the database.", villainId);

        } else {
            PreparedStatement minionsStatement = connection.prepareStatement("select m.name , m.age from minions m\n" +
                    "join minions_villains mv on m.id = mv.minion_id\n" +
                    "where mv.villain_id =  ?;");

            minionsStatement.setInt(1, villainId);
            ResultSet minionsSet = minionsStatement.executeQuery();

            String villainName = villainSet.getString("name");
            System.out.printf("Villain: %s%n", villainName);
            int count = 0;
            while (minionsSet.next()) {
                System.out.printf("%d. %s %d%n", ++count,
                        minionsSet.getString("m.name"),
                        minionsSet.getInt("m.age"));
            }
        }
    }
}
