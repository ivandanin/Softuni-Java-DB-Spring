import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class Minions_08 {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        try {
            Properties properties = new Properties();
            properties.setProperty("user", "root");
            properties.setProperty("password", "root");

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/minions_db", properties);

            int id = Integer.parseInt(scanner.nextLine());

            CallableStatement callableStatement = connection.prepareCall("CALL usp_get_older1(?)");
            callableStatement.setInt(1, id);

            int affected = callableStatement.executeUpdate();



        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
