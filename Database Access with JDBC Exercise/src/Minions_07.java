import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Minions_07 {
    public static void main(String[] args) {


        try {

            Properties properties = new Properties();
            properties.setProperty("user", "root");
            properties.setProperty("password", "root");

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/minions_db", properties);
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT name FROM minions");
            ResultSet resultSet = preparedStatement.executeQuery();

            List<String> allMinionsNames = new ArrayList<>();

            while (resultSet.next()) {
                allMinionsNames.add(resultSet.getString(1));
            }

            int start  = 0;
            int end = allMinionsNames.size() - 1;

            for (int i = 0; i < allMinionsNames.size(); i++) {
                System.out.println(i % 2 == 0
                ? allMinionsNames.get(start++)
                        : allMinionsNames.get(end--));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
