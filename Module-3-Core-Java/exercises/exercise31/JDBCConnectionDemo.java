import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JDBCConnectionDemo {

    public static void main(String[] args) {

        String url =
                "jdbc:mariadb://localhost:3306/community_event_portal";

        String username = "javauser";
        String password = "java123";

        try {
            Class.forName("org.mariadb.jdbc.Driver");

            Connection connection =
                    DriverManager.getConnection(
                            url,
                            username,
                            password
                    );

            System.out.println("Database Connected!\n");

            Statement stmt = connection.createStatement();

            ResultSet rs =
                    stmt.executeQuery(
                            "SELECT * FROM students"
                    );

            System.out.println("Students Table:");

            while (rs.next()) {

                System.out.println(
                        rs.getInt("id") + " | " +
                        rs.getString("name") + " | " +
                        rs.getInt("age")
                );
            }

            rs.close();
            stmt.close();
            connection.close();

        } catch (Exception e) {

            System.out.println(
                    "Error: " + e.getMessage()
            );
        }
    }
}