import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.Scanner;

public class Minions_03 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        try {

        Properties properties = new Properties();
        properties.setProperty("user", "root");
        properties.setProperty("password", "root");

        Connection connection = DriverManager.getConnection
                ("jdbc:mysql://localhost:3306/minions_db", properties);

            System.out.println("Please, enter villains id");

        int villainId = Integer.parseInt(scanner.nextLine());

            PreparedStatement statement = connection.prepareStatement("""
                    SELECT m.name, m.age FROM minions m
                    JOIN minions_villains mv on m.id = mv.minion_id
                    WHERE mv.villain_id = ?;""");

            statement.setInt(1, villainId);

        ResultSet resultSet = statement.executeQuery();
        int counter = 0;

        while (resultSet.next()) {
            System.out.printf("%d. %s %d%n", ++counter, resultSet.getString("name"),
                resultSet.getInt("age"));



        }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
