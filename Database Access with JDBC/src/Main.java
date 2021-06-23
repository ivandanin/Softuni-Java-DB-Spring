import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);

        try {
            Properties properties = new Properties();
            properties.setProperty("user", "root");
            properties.setProperty("password",  "root");

            Connection connection = DriverManager.getConnection
                    ("jdbc:mysql://localhost:3306/soft_uni", properties);

            PreparedStatement statement = connection.prepareStatement("SELECT `first_name`, `last_name` " +
                    " FROM `employees` " +
                    "WHERE `salary` > ?");

            System.out.println("Insert salary");
            String salary = scanner.nextLine();
            statement.setDouble(1, Double.parseDouble(salary));

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                System.out.println(resultSet.getString("first_name") + " "
                        + resultSet.getString("last_name"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
