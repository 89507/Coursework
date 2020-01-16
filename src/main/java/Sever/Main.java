package Sever;// These are imports, they add a connection class, so the code can use the SQL library
// Test for github
import Controllers.TrackerController;
import Controllers.UserContoller;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;
import org.sqlite.SQLiteConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class Main {

    public static Connection db = null;
    //phase 2
    public static void main(String[] args) {

        openDatabase("Database.db");
       //UserContoller.adduser("Bobby","Bob", "smith","19/11/2011", "bob@gmail.com","Male", "password");

        ResourceConfig config = new ResourceConfig();
        config.packages("Controllers");
        config.register(MultiPartFeature.class);
        ServletHolder servlet = new ServletHolder(new ServletContainer(config));

        Server server = new Server(8081);
        ServletContextHandler context = new ServletContextHandler(server, "/");
        context.addServlet(servlet, "/*");

        try {
            server.start();
            System.out.println("Server successfully started.");
            server.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//phase 1
    /*public static void main(String[] args) {
        openDatabase("Database.db");


        // code to get data from, write to the database etc goes here!



        UserContoller.adduser("zach1234", "a", "b", "16/06/2002", "zach12345@yahoo.co.uk", "male", "Testing1234" );
        UserContoller.getallUsers();

        closeDatabase();
    }*/

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



