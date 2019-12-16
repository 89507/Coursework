package Controllers;

import Sever.Main;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Path("Users/")
public class UserContoller {

    // This method prints out the contents of users from my SQL table
   /* @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)

    public static String getallUsers(){
        System.out.println("thing/list");
        JSONArray list = new JSONArray();

        try {
//      This is a Crud statement to Read
            PreparedStatement ps = Main.db.prepareStatement("SELECT * FROM Users");

            ResultSet results = ps.executeQuery();
            while (results.next()) {

                JSONObject item = new JSONObject();
                item.put("id", results.getInt(1));
                item.put("name", results.getString(2));
                item.put("DOB", results.getInt(3));
                item.put("Email", results.getString(4));
                item.put("Gender", results.getString(5));
                item.put("Password", results.getString(6));
                list.add(item);

            }
            return list.toString();

        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
            return "{\"error\": \"Unable to list items, please see server console for more info.\"}";

        }

    }*/

    public static void getallUsers() {
        System.out.println("Users/list");

        try {
//      This is a Crud statement to Read
            PreparedStatement ps = Main.db.prepareStatement("SELECT UserID, Username, DOB, Email, Gender, Password FROM Users");

            ResultSet results = ps.executeQuery();
            while (results.next()) {
                int UserId = results.getInt(1);
                String Username = results.getString(2);
                String DOB = results.getString(3);
                String Email = results.getString(4);
                String Gender = results.getString(5);
                String Password = results.getString(6);
                System.out.println("ID: " + UserId + " ");
                System.out.println("Username: " + Username + " ");
                System.out.println("DOB: " + DOB + " ");
                System.out.println("Email: " + Email + " ");
                System.out.println("Gender: " + Gender + " ");
                System.out.println("Password: " + Password + " ");

            }

        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());

        }

    }

    // This is a CRUD create statement
    public static void adduser(Integer userID, String username, String DOB, String email, String gender, String password) {

        try {

            PreparedStatement ps = Main.db.prepareStatement("INSERT INTO Users (userId, username, DOB, email, gender, password) VALUES (?, ?, ?, ?,?,?)");

            ps.setInt(1, userID);
            ps.setString(2, username);
            ps.setString(3, DOB);
            ps.setString(4, email);
            ps.setString(5, gender);
            ps.setString(6, password);
            ps.executeUpdate();

        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
        }
    }
// This a update crud statement
        public static void updateuser(Integer userID, String username, String DOB, String email, String gender, String password){


            try {
                PreparedStatement ps = Main.db.prepareStatement("UPDATE Users SET UserID = ?,Username = ?, DOB = ?, Email = ?, Gender = ?, Password = ?,");

                ps.setInt(1, userID);
                ps.setString(2, username);
                ps.setString(3, DOB);
                ps.setString(4, email);
                ps.setString(5, gender);
                ps.setString(6, password);
                ps.executeUpdate();



        } catch (Exception exception) {
                System.out.println("Database error: " + exception.getMessage());
            }
        }

        public static void deletleUsers(int UserID){

        try{
            PreparedStatement ps = Main.db.prepareStatement("DELETE FROM Users WHERE UserID = ?");

            ps.setInt(1,UserID);

            ps.execute();

        } catch(Exception exception) {
            System.out.println("Databse error: " + exception.getMessage());
        }
    }



    }




