import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class StudentDAO {

    private static final String URL =
            "jdbc:mariadb://localhost:3306/community_event_portal";

    private static final String USER = "javauser";
    private static final String PASSWORD = "java123";

    public void insertStudent(int id, String name, int age)
            throws Exception {

        Connection con =
                DriverManager.getConnection(
                        URL,
                        USER,
                        PASSWORD
                );

        String sql =
                "INSERT INTO students(id,name,age) VALUES(?,?,?)";

        PreparedStatement ps =
                con.prepareStatement(sql);

        ps.setInt(1, id);
        ps.setString(2, name);
        ps.setInt(3, age);

        int rows = ps.executeUpdate();

        System.out.println(
                rows + " row inserted."
        );

        ps.close();
        con.close();
    }

    public void updateStudentAge(int id, int age)
            throws Exception {

        Connection con =
                DriverManager.getConnection(
                        URL,
                        USER,
                        PASSWORD
                );

        String sql =
                "UPDATE students SET age=? WHERE id=?";

        PreparedStatement ps =
                con.prepareStatement(sql);

        ps.setInt(1, age);
        ps.setInt(2, id);

        int rows = ps.executeUpdate();

        System.out.println(
                rows + " row updated."
        );

        ps.close();
        con.close();
    }

    public static void main(String[] args)
            throws Exception {

        StudentDAO dao = new StudentDAO();

        dao.insertStudent(
                4,
                "Anshuk",
                19
        );

        dao.updateStudentAge(
                2,
                25
        );

        System.out.println(
                "Insert and Update completed."
        );
    }
}