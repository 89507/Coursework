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
    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)

    public static String getallUsers(){
        System.out.println("thing/list");
        JSONArray list = new JSONArray();

        try {

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

    }
    // this method adds a new user
    private static void adduser(String username, String DOB){

        try {

            PreparedStatement ps = Main.db.prepareStatement("INSERT INTO Users (username, DOB) VALUES (?, ?)");

            ps.setString(1, username);
            ps.setString(2, DOB);

            ps.executeUpdate();

        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
        }


    }
}
