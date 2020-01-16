package Controllers;

import Sever.Main;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Path("Users/")
public class UserContoller {

    // This method prints out the contents of users from my SQL table
    //$ curl -s localhost:8081/Users/list
    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public static String getallUsers(){
        System.out.println("Users/list");
        JSONArray list = new JSONArray();

        try {
//      This is a Crud statement to Read
            PreparedStatement ps = Main.db.prepareStatement("SELECT UserID, Username, FirstName, LastName, DOB, Email, Gender, Password FROM Users");

            ResultSet results = ps.executeQuery();
            while (results.next()) {

                JSONObject item = new JSONObject();
                item.put("UserID", results.getInt(1));
                item.put("Username", results.getString(2));
                item.put("FirstName", results.getString(3));
                item.put("LastName", results.getString(4));
                item.put("DOB", results.getString(5));
                item.put("Email", results.getString(6));
                item.put("Gender", results.getString(7));
                item.put("Password", results.getString(8));
                list.add(item);

            }
            return list.toString();

        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
            return "{\"error\": \"Unable to list items, please see server console for more info.\"}";

        }

    }

/*    public static void getallUsers() {
        System.out.println("Users/list");

        try {
//      This is a Crud statement to Read
            PreparedStatement ps = Main.db.prepareStatement("SELECT UserID, Username, FirstName, LastName, DOB, Email, Gender, Password FROM Users");

            ResultSet results = ps.executeQuery();
            while (results.next()) {
                int UserId = results.getInt(1);
                String Username = results.getString(2);
                String FirstName = results.getString(3);
                String LastName = results.getString(4);
                String DOB = results.getString(5);
                String Email = results.getString(6);
                String Gender = results.getString(7);
                String Password = results.getString(8);


                System.out.println("ID: " + UserId + " ");
                System.out.println("Username: " + Username + " ");
                System.out.println("FirstName: " + FirstName + " ");
                System.out.println("Lastname: " + LastName + " ");
                System.out.println("DOB: " + DOB + " ");
                System.out.println("Email: " + Email + " ");
                System.out.println("Gender: " + Gender + " ");
                System.out.println("Password: " + Password + " ");

            }

        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());

        }

    }*/

    // This is a CRUD create statement



    @POST
    @Path("Add")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public String AddUsers(
    @FormDataParam("UserID") Integer UserID, @FormDataParam("UserName") String UserName, @FormDataParam("FirstName") String FirstName, @FormDataParam("LastName") String LastName, @FormDataParam("DOB") String DOB, @FormDataParam("Email")String Email, @FormDataParam("Gender") String Gender, @FormDataParam("Password") String Password){

    try{
        if (UserID == null || UserName == null || FirstName == null || LastName == null || DOB == null || Email
    }

    }







    /*public static void adduser(String username, String FirstName, String LastName, String DOB, String email, String gender, String password) {

        try {

            PreparedStatement ps = Main.db.prepareStatement("INSERT INTO Users ( username, FirstName, LastName, DOB, email, gender, password) VALUES (?, ?, ?, ?,?,?,?)");

            ps.setString(1, username);
            ps.setString(2, FirstName);
            ps.setString(3, LastName);
            ps.setString(4, DOB);
            ps.setString(5, email);
            ps.setString(6, gender);
            ps.setString(7, password);
            ps.executeUpdate();

        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
        }
    }*/
// This a update crud statement
        public static void updateuser(Integer userID, String username, String FirstName, String Lastname,  String DOB, String email, String gender, String password){


            try {
                PreparedStatement ps = Main.db.prepareStatement("UPDATE Users SET UserID = ?,Username = ?, FirstName = ?, LastName = ?, DOB = ?, Email = ?, Gender = ?, Password = ?,");

                ps.setInt(1, userID);
                ps.setString(2, username);
                ps.setString(3, FirstName);
                ps.setString(4, Lastname);
                ps.setString(5, DOB);
                ps.setString(6, email);
                ps.setString(7, gender);
                ps.setString(8, password);
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




