import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

public class Minions_02 {
    public static void main(String[] args) {

        try {

            Properties properties = new Properties();
            properties.setProperty("user", "root");
            properties.setProperty("password", "root"); /// TODO: 6/23/2021 Enter pass

            Connection connection = DriverManager.getConnection
                    ("jdbc:mysql://localhost:3306/minions_db", properties);

            PreparedStatement statement = connection.prepareStatement("""
                    SELECT v.`name`, COUNT(DISTINCT mv.`minion_id`) AS `m_count`
                                        FROM `villains` AS v
                                        JOIN `minions_villains` AS mv
                                        ON v.`id` = mv.`villain_id`
                                        GROUP BY v.`name`
                                        HAVING `m_count` > ?;""");

            statement.setInt(1, 15);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                System.out.printf("%s %d%n", resultSet.getString(1), resultSet.getInt(2));
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
