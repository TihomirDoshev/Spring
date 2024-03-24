import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class _02_GetVillainsNames {
    public static void main(String[] args) throws SQLException {
        Properties properties = new Properties();
        properties.setProperty("user","root");
        properties.setProperty("password","");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/minions_db",properties);
        ResultSet resultSet = connection.createStatement().executeQuery("SELECT v.name,COUNT(mv.minion_id) AS count FROM minions_db.villains AS v\n" +
                "JOIN minions_db.minions_villains mv on v.id = mv.villain_id\n" +
                "GROUP BY v.name\n" +
                "HAVING  count > 15\n" +
                "ORDER BY count DESC ;");
        while (resultSet.next()){
            System.out.printf("%s %d%n",resultSet.getString("name"),resultSet.getInt("count"));
        }
    }
}
