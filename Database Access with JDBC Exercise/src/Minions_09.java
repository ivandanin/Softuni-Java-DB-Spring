import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class Minions_09 {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        try {
            Properties properties = new Properties();
            properties.setProperty("user", "root");
            properties.setProperty("password", "root");

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/minions_db", properties);

            int id = Integer.parseInt(scanner.nextLine());

            CallableStatement callableStatement = connection.prepareCall("CALL usp_get_older(?)");
            callableStatement.setInt(1, id);
            callableStatement.executeUpdate();

            PreparedStatement preparedStatement = connection.
                    prepareStatement("SELECT name, age FROM minions WHERE id = ?;");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            System.out.println(resultSet.getString("name") + " " + resultSet.getString("age"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
/*
DELIMITER //
CREATE PROCEDURE usp_get_older (minion_id INT)
BEGIN
    UPDATE minions
        SET age = age + 1
        WHERE id = minion_id;
END //
DELIMITER ;
 */