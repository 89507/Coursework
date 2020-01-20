package Controllers;

import Sever.Main;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MealsController {


    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public static String getallMeals(){
        System.out.println("Meals/list");
        JSONArray list = new JSONArray();

        try {
//      This is a Crud statement to Read
            PreparedStatement ps = Main.db.prepareStatement("SELECT MealID, Name, PortionSize, Carbs, Custom FROM Meals");

            ResultSet results = ps.executeQuery();
            while (results.next()) {

                JSONObject item = new JSONObject();
                item.put("MealID", results.getInt(1));
                item.put("Name", results.getString(2));
                item.put("PortionSize", results.getInt(3));
                item.put("Carbs", results.getInt(4));
                item.put("Custom", results.getBoolean(5));
                list.add(item);

            }
            return list.toString();

        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
            return "{\"error\": \"Unable to list items, please see server console for more info.\"}";

        }

    }




















   /* public static void getallMeals() {
        System.out.println("Meals/list");

        try {
//      This is a Crud statement to Read
            PreparedStatement ps = Main.db.prepareStatement("SELECT MealID, Name, Portionsize, Carbs, Custom FROM Meals");

            ResultSet results = ps.executeQuery();
            while (results.next()) {
                int MealID = results.getInt(1);
                String Name = results.getString(2);
                int Portionsize = results.getInt(3);
                int Carbs = results.getInt(4);
                boolean Custom = results.getBoolean(5);

                System.out.println("SugarID: " + MealID + " ");
                System.out.println("UserID: " + Name + " ");
                System.out.println("Date: " + Portionsize + " ");
                System.out.println("Time: " + Carbs + " ");
                System.out.println("SugarLevel: " + Custom + " ");


            }
        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());

        }
    }*/

    @POST
    @Path("Add")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public String AddMeals(
            @FormDataParam("Name") String Name, @FormDataParam("PortionSize") Integer PortionSize, @FormDataParam("Carbs") Integer Carbs, @FormDataParam("Custom")Boolean Custom){

        try{
            if ( Name == null || PortionSize == null || Carbs == null || Custom == null ) {
                throw new Exception("One or more form data parameters are missing in the HTTP request.");
            }
            System.out.println("add/new Name =" + Name);

            PreparedStatement ps = Main.db.prepareStatement("INSERT INTO Meals(Name, Portionsize, Carbs, Custom) VALUES (?, ?, ?, ?)");
            ps.setString(1, Name);
            ps.setInt(2, PortionSize);
            ps.setInt(3, Carbs);
            ps.setBoolean(4, Custom);
            ps.execute();
            return "{\"status\": \"OK\"}";
        } catch (Exception exception){
            System.out.println("Database error: " + exception.getMessage());
            return "{\"error\": \"Unable to create new item, please see server console for more info.\"}";
        }
    }

















   /* // This is a CRUD create statement
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
*/

    @POST
    @Path("update")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public String updateMeals(
            @FormDataParam("MealId") Integer MealID, @FormDataParam("Name")String Name, @FormDataParam("Portionsize") Integer PortionSize, @FormDataParam("Carbs") Integer Carbs, @FormDataParam("Custom") Boolean Custom) {
        try {
            if (MealID == null || Name == null || PortionSize == null || Carbs == null || Custom == null) {
                throw new Exception("One or more form data parameters are missing in the HTTP request.");
            }
            System.out.println("Meals/update Name=" + Name);
            PreparedStatement ps = Main.db.prepareStatement("UPDATE Meals SET MealID = ?, Name = ?, Portionsize = ?, Carbs = ?, Custom = ? WHERE MealID = ?");
            ps.setInt(1, MealID);
            ps.setString(2, Name);
            ps.setInt(3, PortionSize);
            ps.setInt(4, Carbs);
            ps.setBoolean(5, Custom);
            ps.execute();
            return "{\"status\": \"OK\"}";

        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
            return "{\"error\": \"Unable to update item, please see server console for more info.\"}";
        }

    }



    @POST
    @Path("Delete")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public String DeleteMeals(@FormDataParam("MealID") Integer MealID) {


        try {
            if(MealID == null) {
                throw new Exception("One or more form data parameters are missing in the HTTP request.");
            }
            System.out.println("Meal/delete UserID=" + MealID);

            PreparedStatement ps = Main.db.prepareStatement("DELETE FROM Meals WHERE MealID = ?");

            ps.setInt(1, MealID);

            ps.execute();

            return "{\"status\": \"OK\"}";

        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
            return "{\"error\": \"Unable to delete item, please see server console for more info.\"}";
        }
    }





}
