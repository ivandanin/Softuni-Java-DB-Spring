import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class Minions_06 {
    private static final Properties properties = new Properties();
    private static Connection connection;


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        try {
            properties.setProperty("user", "root");
            properties.setProperty("password", "root");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/minions_db", properties);

            int villainId = Integer.parseInt(scanner.nextLine());

            int affectedEntities = deleteMinionsById(villainId);
            String villainName = findVillainById(villainId);

            deleteVillainById(villainId);

            System.out.println(villainName + " was deleted");
            System.out.println(affectedEntities + " minions released");



        } catch (SQLException e) {
            System.out.println("No such villain was found");
        }
    }

    public static int deleteMinionsById(int villainId) throws SQLException {
        PreparedStatement preparedStatement = connection.
                prepareStatement("DELETE FROM minions_villains WHERE villain_id = ?");
        preparedStatement.setInt(1, villainId);
        return preparedStatement.executeUpdate();
    }
    public static String findVillainById(int villainId) throws SQLException {
        PreparedStatement preparedStatement = connection.
                prepareStatement("SELECT name FROM villains WHERE id = ?;");
        preparedStatement.setInt(1, villainId);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
            return resultSet.getString("name");
    }
    public static void deleteVillainById(int villainId) throws SQLException {
        PreparedStatement preparedStatement = connection.
                prepareStatement("DELETE FROM villains WHERE id = ?");
        preparedStatement.setInt(1, villainId);
        preparedStatement.executeUpdate();
    }
}
