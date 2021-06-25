import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.Scanner;

public class Minions_05 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {

            Properties properties = new Properties();
            properties.setProperty("user", "root");
            properties.setProperty("password", "root");

            Connection connection = DriverManager.getConnection
                    ("jdbc:mysql://localhost:3306/minions_db", properties);

            System.out.println("Please, enter country name.");
            String country = scanner.nextLine();

            PreparedStatement statement = connection.prepareStatement
                    ("update towns set name = upper(name) where country = ?");
            statement.setString(1, country);

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                System.out.println("No town names were affected.");
                return;
            }
            System.out.printf("%d town names were affected.%n", affectedRows);

            PreparedStatement statementTowns = connection.prepareStatement
                    ("select name from towns where country = ?");

            statementTowns.setString(1, country);
            ResultSet resultSet = statementTowns.executeQuery();

            while (resultSet.next()) {
                System.out.println(resultSet.getString("name"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
