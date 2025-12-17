import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {
    private static Connection connection = null;
    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try (FileInputStream fis = new FileInputStream("src/main/resources/database.properties")) {
                Properties properties = new Properties();
                properties.load(fis);
                connection = DriverManager.getConnection(
                        properties.getProperty("db.url"),
                        properties.getProperty("db.user"),
                        properties.getProperty("db.password")
                );
            } catch (IOException e) {
                System.err.println("Error while reading database.properties" + e.getMessage());
            }
        }
        return connection;
    }
}
