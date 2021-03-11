package database;

import javax.swing.*;
import java.sql.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @Description: Database connection
 * @Author: Li Liao
 * @Date: 03/04/2021
 */
public final class DatabaseHandler {


    private static DatabaseHandler handler = null;
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://your_database_url:3306/your_database_name";
    static final String USER = "your_user_name";
    static final String PASS = "your_password";
    private static Connection connection = null;
    private static Statement statement = null;

    static {
        createConnection();
    }

    private DatabaseHandler() {
    }

    public static DatabaseHandler getInstance() {
        if (handler == null) {
            handler = new DatabaseHandler();
        }
        return handler;
    }

    private static void createConnection() {
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            statement = connection.createStatement();
            //System.out.println("database connect success");
        } catch (Exception exception) {
            exception.printStackTrace();
            System.exit(0);
        }
    }

    public ResultSet execQuery(String query) {
        ResultSet result;
        // System.out.println("execQuery:dataHandler" + query);
        try {
            statement = connection.createStatement();
            result = statement.executeQuery(query);   // return single
        } catch (SQLException exception) {
            exception.printStackTrace();
            // System.out.println("Exception at execQuery:dataHandler" + exception.getLocalizedMessage());
            return null;
        }
        return result;
    }

    public boolean execAction(String query) {
        try {
            statement = connection.createStatement();
            statement.execute(query);
            return true;
        } catch (SQLException exception) {
            exception.printStackTrace();
            return false;
        }
    }

    public int execUpdate(String query) {
        // System.out.println("execQuery:dataHandler:" + query);
        try {
            statement = connection.createStatement();
            return statement.executeUpdate(query);
        } catch (SQLException exception) {
            exception.printStackTrace();
            return 0;
        }
    }


    /*
    private static Set<String> getDBTables() throws SQLException {
        Set<String> set = new HashSet<>();
        DatabaseMetaData metaData = connection.getMetaData();
        readDBTable(set, metaData, "TABLE", null);
        return set;
    }

    private static void readDBTable(Set<String> set, DatabaseMetaData metaData, String searchCriteria, String schema) throws SQLException {
        ResultSet rs = metaData.getTables(null, schema, null, new String[]{searchCriteria});
        while (rs.next()) {
            set.add(rs.getString("TABLE_NAME").toLowerCase());
        }
    }

    public boolean execAction(String qu) {
        try {
            statement = connection.createStatement();
            statement.execute(qu);
            return true;
        } catch (SQLException exception) {
            JOptionPane.showMessageDialog(null, "Error:" + exception.getMessage(), "Error Occured", JOptionPane.ERROR_MESSAGE);
            System.out.println("Exception at execQuery:dataHandler" + exception.getLocalizedMessage());
            return false;
        } finally {
        }
    }
     */

    public Connection getConnection() {
        return connection;
    }
}
