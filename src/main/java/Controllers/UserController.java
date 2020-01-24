package Controllers;

import Sever.Main;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.UUID;
@Path("Users/")
public class UserController {

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

    /*public static void getallUsers() {
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
    @FormDataParam("UserName") String UserName, @FormDataParam("FirstName") String FirstName, @FormDataParam("LastName") String LastName, @FormDataParam("DOB") String DOB, @FormDataParam("Email")String Email, @FormDataParam("Gender") String Gender, @FormDataParam("Password") String Password){

    try{
        if (UserName == null || FirstName == null || LastName == null || DOB == null || Email == null || Gender == null || Password == null) {
            throw new Exception("One or more form data parameters are missing in the HTTP request.");
        }
        System.out.println("add/new UserName=" + UserName);

        PreparedStatement ps = Main.db.prepareStatement("INSERT INTO Users (Username, FirstName, LastName, DOB, Email, Gender, Password) VALUES (?, ?, ?, ?, ?, ?, ?)");
        ps.setString(1, UserName);
        ps.setString(2, FirstName);
        ps.setString(3, LastName);
        ps.setString(4, DOB);
        ps.setString(5, Email);
        ps.setString(6, Gender);
        ps.setString(7,Password);
        ps.execute();
        return "{\"status\": \"OK\"}";
    } catch (Exception exception){
        System.out.println("Database error: " + exception.getMessage());
        return "{\"error\": \"Unable to create new item, please see server console for more info.\"}";
        }
    }







   /* public static void adduser(String username, String FirstName, String LastName, String DOB, String email, String gender, String password) {

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



   // this updates the Users data
   @POST
   @Path("update")
   @Consumes(MediaType.MULTIPART_FORM_DATA)
   @Produces(MediaType.APPLICATION_JSON)
   public String updateUsers(
           @FormDataParam("UserId") Integer UserID, @FormDataParam("UserName") String UserName, @FormDataParam("FirstName") String FirstName, @FormDataParam("LastName") String LastName, @FormDataParam("DOB")String DOB, @FormDataParam("Email")String Email, @FormDataParam("Gender")String Gender, @FormDataParam("Password")String Password) {
       try {
           if (UserID == null ||UserName == null || FirstName == null || LastName == null || DOB == null || Email == null || Gender == null || Password == null) {
               throw new Exception("One or more form data parameters are missing in the HTTP request.");
           }
           System.out.println("Users/update UserName=" + UserName);

           PreparedStatement ps = Main.db.prepareStatement("UPDATE Users SET UserID = ?, UserName = ?, FirstName = ?, LastName = ?, DOB = ?, Email = ?, Gender = ?, Password = ? WHERE Username = ?");
           ps.setInt(1, UserID);
           ps.setString(2, UserName);
           ps.setString(3, FirstName);
           ps.setString(4, LastName);
           ps.setString(5, DOB);
           ps.setString(6,Email);
           ps.setString(7,Gender);
           ps.setString(8, Password);
           ps.execute();
           return "{\"status\": \"OK\"}";

       }catch (Exception exception) {
           System.out.println("Database error: " + exception.getMessage());
           return "{\"error\": \"Unable to update item, please see server console for more info.\"}";
       }
   }







// This a update crud statement
        /*public static void updateuser(Integer userID, String username, String FirstName, String Lastname,  String DOB, String email, String gender, String password){


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
        }*/


        @POST
    @Path("Delete")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public String DeleteUsers(@FormDataParam("UserID")Integer UserID){

            try {
                if (UserID == null) {
                    throw new Exception("One or more form data parameters are missing in the HTTP request.");
                }
                System.out.println("thing/delete UserID=" + UserID);

                PreparedStatement ps = Main.db.prepareStatement("DELETE FROM Users WHERE UserID = ?");

                ps.setInt(1, UserID);

                ps.execute();

                return "{\"status\": \"OK\"}";

            } catch (Exception exception) {
                System.out.println("Database error: " + exception.getMessage());
                return "{\"error\": \"Unable to delete item, please see server console for more info.\"}";

            }
        }


        /*public static void deletleUsers(int UserID){

        try{
            PreparedStatement ps = Main.db.prepareStatement("DELETE FROM Users WHERE UserID = ?");

            ps.setInt(1,UserID);

            ps.execute();

        } catch(Exception exception) {
            System.out.println("Databse error: " + exception.getMessage());
        }
    }*/






//this is the code for my login sysytem - the path is Users/login
    @POST
    @Path("login")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    //this is asking for the JSON to get username and password ready for the login
    public String loginUser(@FormDataParam("Username") String Username, @FormDataParam("Password") String Password, @FormDataParam("Token")String Token) {
System.out.print("Username "+ Username);
System.out.print("Password "+ Password);
        //if(!UserController.validToken(Token)) {
        //    return "{\"error\": \"You don't appear to be logged in.\"}";
        //}

        try {

            System.out.println("Users/login");

            PreparedStatement ps1 = Main.db.prepareStatement("SELECT Password FROM Users WHERE Username = ?");
            ps1.setString(1, Username);
            ResultSet loginResults = ps1.executeQuery();
            if (loginResults.next()) {

                //this determines weather the user has input a correct password with their username
                String correctPassword = loginResults.getString(1);
                if (Password.equals(correctPassword)) {

                    //this prepares a random token - a string of random letters and numbers - this is for security perposes
                    String token = UUID.randomUUID().toString();

                    //this sets the username and token up in order to make sure they have the token
                    PreparedStatement ps2 = Main.db.prepareStatement("UPDATE Users SET Token = ? WHERE Username = ?");
                    ps2.setString(1, token);
                    ps2.setString(2, Username);
                    ps2.executeUpdate();

                    JSONObject userDetails = new JSONObject();
                    userDetails.put("Username", Username);
                    userDetails.put("Token", token);
                    return userDetails.toString();

                    //these are the results returned if the user does not get the correct password
                } else {
                    return "{\"error\": \"Incorrect password!\"}";
                }

            } else {
                return "{\"error\": \"Unknown user!\"}";
            }
//this is an error catch to make my debugging process much easier
        } catch (Exception exception) {
            System.out.println("Database error during /user/login: " + exception.getMessage());
            return "{\"error\": \"Server side error!\"}";
        }
    }


    @POST
    @Path("logout")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public String logoutUser(@CookieParam("token") String token) {

        try {

            System.out.println("Users/logout");

            PreparedStatement ps1 = Main.db.prepareStatement("SELECT UserID FROM Users WHERE Token = ?");
            ps1.setString(1, token);
            ResultSet logoutResults = ps1.executeQuery();
            if (logoutResults.next()) {

                int id = logoutResults.getInt(1);

                PreparedStatement ps2 = Main.db.prepareStatement("UPDATE Users SET Token = NULL WHERE UserID = ?");
                ps2.setInt(1, id);
                ps2.executeUpdate();

                return "{\"status\": \"OK\"}";
            } else {

                return "{\"error\": \"Invalid token!\"}";

            }

        } catch (Exception exception) {
            System.out.println("Database error during /user/logout: " + exception.getMessage());
            return "{\"error\": \"Server side error!\"}";
        }

    }

    public static boolean validToken(String token) {
        try {
            PreparedStatement ps = Main.db.prepareStatement("SELECT UserID FROM Users WHERE Token = ?");
            ps.setString(1, token);
            ResultSet logoutResults = ps.executeQuery();
            return logoutResults.next();
        } catch (Exception exception) {
            System.out.println("Database error during /user/logout: " + exception.getMessage());
            return false;
        }
    }
}



