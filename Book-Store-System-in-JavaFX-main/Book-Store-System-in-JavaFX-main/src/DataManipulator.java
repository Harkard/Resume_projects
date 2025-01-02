import java.sql.*;

public class DataManipulator {

    //Updates the table
    public static void update(String updateStatement) {
        try {
            Connection connection = MySQLHandler.getConnection();
            if (connection != null) {
                Statement stmt = connection.createStatement();
                stmt.executeUpdate(updateStatement);
            }
        } catch (SQLException e) {}
    }

    //Gets the result
    public static ResultSet query(String query) {
        ResultSet rs = null;
        try {
            Connection connection = MySQLHandler.getConnection();
            if (connection != null) {
                Statement stmt = connection.createStatement();
                rs = stmt.executeQuery(query);
            }
        } catch (SQLException e) {}
        return rs;
    }
}
