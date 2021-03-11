/**
 * @Author: Li Liao
 * @Date: 03/04/2021
 */

package database;

import java.sql.*;

/**
 * DatabaseHandler is used to handle request 
 */
public final class DatabaseHandler {

    private static DatabaseHandler handler = null;
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://your_database_url:3306/your_database_name";
    static final String USER = "your_user_name";
    static final String PASS = "your_password";
    private static Connection connection = null;
    private static Statement statement = null;
    
    /**
     * A static method when connect to the database when initializating
     */
    static {
        createConnection();
    }

    private DatabaseHandler() {
    }
    
    /**
     * This method used to connect with database 
     */
    private static void createConnection() {
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            statement = connection.createStatement();
        } catch (Exception exception) {
            exception.printStackTrace();
            System.exit(0);
        }
    }
    
    /**
     * This method used to get DatabaseHandler
     */
    public static DatabaseHandler getInstance() {
        if (handler == null) {
            handler = new DatabaseHandler();
        }
        return handler;
    }
    
    /**
     * This method used to close connetion when quit program
     */
    public Connection getConnection() {
        return connection;
    }
    
     /**
     * This method is called to handle SQL statement, and return ResultSet
     *
     * @param query A SQL statement trigger by action on GUI
     * @return A ResultSet object that contains the data
     */
    public ResultSet execQuery(String query) {
        ResultSet result;
        try {
            statement = connection.createStatement();
            result = statement.executeQuery(query); 
        } catch (SQLException exception) {
            exception.printStackTrace();
            return null;
        }
        return result;
    }
    
    /**
     * This method is called to handle SQL statement, and return a int to trigger Alert dialog
     *
     * @param query A SQL statement trigger by action on GUI
     * @return The row count for SQL DML statements
     */
    public int execUpdate(String query) {
        try {
            statement = connection.createStatement();
            return statement.executeUpdate(query);
        } catch (SQLException exception) {
            exception.printStackTrace();
            return 0;
        }
    }
    
    /**
     * This method is called to handle SQL statement
     *
     * @param query A SQL statement trigger by action on GUI
     * @return A boolean
     */
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
}
