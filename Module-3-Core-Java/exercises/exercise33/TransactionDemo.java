import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class TransactionDemo {

    private static final String URL =
            "jdbc:mariadb://localhost:3306/community_event_portal";

    private static final String USER = "javauser";
    private static final String PASSWORD = "java123";

    public static void transfer(
            int fromId,
            int toId,
            double amount
    ) {

        Connection con = null;

        try {

            con = DriverManager.getConnection(
                    URL,
                    USER,
                    PASSWORD
            );

            con.setAutoCommit(false);

            PreparedStatement debit =
                    con.prepareStatement(
                            "UPDATE accounts SET balance = balance - ? WHERE id = ?"
                    );

            debit.setDouble(1, amount);
            debit.setInt(2, fromId);

            PreparedStatement credit =
                    con.prepareStatement(
                            "UPDATE accounts SET balance = balance + ? WHERE id = ?"
                    );

            credit.setDouble(1, amount);
            credit.setInt(2, toId);

            debit.executeUpdate();
            credit.executeUpdate();

            con.commit();

            System.out.println(
                    "Transaction committed successfully."
            );

            debit.close();
            credit.close();

        } catch (Exception e) {

            try {
                if (con != null) {
                    con.rollback();
                    System.out.println(
                            "Transaction rolled back."
                    );
                }
            } catch (Exception ex) {
                System.out.println(
                        "Rollback failed: " +
                        ex.getMessage()
                );
            }

            System.out.println(
                    "Transaction failed: " +
                    e.getMessage()
            );

        } finally {

            try {
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                System.out.println(
                        "Error closing connection: " +
                        e.getMessage()
                );
            }
        }
    }

    public static void main(String[] args) {

        transfer(
                1,
                2,
                200
        );
    }
}