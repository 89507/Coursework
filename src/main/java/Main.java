// These are imports, they add a connection class, so the code can use the SQL library

import org.sqlite.SQLiteConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class Main {

    public static Connection db = null;

    public static void main(String[] args) {
        openDatabase("Database.db");


        // code to get data from, write to the database etc goes here!
        getallUsers();


        closeDatabase();
    }

    // Opening and setting up
    private static void openDatabase(String dbFile) {
        try {
            Class.forName("org.sqlite.JDBC");
            SQLiteConfig config = new SQLiteConfig();
            // This makes sure SQL and java have the same updated database
            config.enforceForeignKeys(true);
            db = DriverManager.getConnection("jdbc:sqlite:resources/" + dbFile, config.toProperties());
            System.out.println("Database connection successfully established.");
            // This code prints an error message if the data base connection fails
        } catch (Exception exception) {
            System.out.println("Database connection error: " + exception.getMessage());
        }

    }

    // This is to close the data base after the code has accessed it
    private static void closeDatabase() {
        try {
            db.close();
            System.out.println("Disconnected from database.");
            // This prints out an error message if it fails
        } catch (Exception exception) {
            System.out.println("Database disconnection error: " + exception.getMessage());
        }
    }


}



