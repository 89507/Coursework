package Controllers;

import Sever.Main;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
@Path("Tracker/")
public class TrackerController {

    // This method prints out the contents of users from my SQL table

    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public static String getallTracker() {
        System.out.println("Tracker/list");
        JSONArray list = new JSONArray();

        try {
//      This is a Crud statement to Read For stage 2- API
            PreparedStatement ps = Main.db.prepareStatement("SELECT TrackerID, UserID, Date, Time, MealID, Catogery, Amount, InsulinSite FROM Tracker");

            ResultSet results = ps.executeQuery();
            while (results.next()) {

                JSONObject item = new JSONObject();
                item.put("TrackerID", results.getInt(1));
                item.put("UserID", results.getInt(2));
                item.put("Date", results.getString(3));
                item.put("Time", results.getString(4));
                item.put("MealID", results.getInt(5));
                item.put("Catogery", results.getString(6));
                item.put("Amount", results.getInt(7));
                item.put("InsulinSite", results.getString(8));
                list.add(item);

            }
            return list.toString();

        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
            return "{\"error\": \"Unable to list items, please see server console for more info.\"}";


        }
    }

    /*public static void getallTracker() {
        System.out.println("tracker/list");

        try {
//      This is a Crud statement to Read
            PreparedStatement ps = Main.db.prepareStatement("SELECT TrackerID, USerID, Date, Time, MealID, Catogery, Amount, InsulinSite FROM Tracker");

            ResultSet results = ps.executeQuery();
            while (results.next()) {
                int TrackerID = results.getInt(1);
                int UserID = results.getInt(2);
                String Date = results.getString(3);
                String Time = results.getString(4);
                int MealID = results.getInt(5);
                String Catogery = results.getString(6);
                int Amount = results.getInt(7);
                String InsulinSite = results.getString(8);

                System.out.println("TrackerID: " + TrackerID + " ");
                System.out.println("UserID: " + UserID + " ");
                System.out.println("Date: " + Date + " ");
                System.out.println("Time: " + Time + " ");
                System.out.println("MealID: " + MealID + " ");
                System.out.println("Catogery: " + Catogery + " ");
                System.out.println("Amount: " + Amount + " ");
                System.out.println("Insulin Site: " + InsulinSite + " ");

            }
        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());

        }
    }*/


    //this is stage 2 API Tracker create statement

    @POST
    @Path("Add")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public String AddTracker(
            @FormDataParam("UserID") Integer UserID, @FormDataParam("Date") String Date, @FormDataParam("Time") String Time, @FormDataParam("MealID") Integer MealID, @FormDataParam("Catogery") String Catogery, @FormDataParam("Amount") Integer Amount, @FormDataParam("InsulinSite") String InsulinSite) {

        try {
            if (Date == null || Time == null || Amount == null) {
                throw new Exception("One or more form data parameters are missing in the HTTP request.");
            }
            System.out.println("add/new UserID=" + UserID);

            PreparedStatement ps = Main.db.prepareStatement("INSERT INTO Tracker ( UserID, Date, Time, MealID, Catogery, Amount, InsulinSite) VALUES (?,?,?,?,?,?,?)");
            ps.setInt(1, UserID);
            ps.setString(2, Date);
            ps.setString(3, Time);
            ps.setInt(4, MealID);
            ps.setString(5, Catogery);
            ps.setInt(6, Amount);
            ps.setString(7, InsulinSite);
            ps.execute();
            return "{\"status\": \"OK\"}";
        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
            return "{\"error\": \"Unable to create new item, please see server console for more info.\"}";
        }
    }








    /*    // This is a CRUD create statement
        public static void addTracker(Integer TrackerID, Integer UserID, String Date, String Time, Integer MealID, String Catogery, Integer Amount, String InsulinSite) {

            try {

                PreparedStatement ps = Main.db.prepareStatement("INSERT INTO Tracker (TrackerID, UserID, Date, Time, MealID, Catogery, Amount, InsulinSite) VALUES (?, ?, ?, ?,?,?,?,?)");

                ps.setInt(1, TrackerID);
                ps.setInt(2, UserID);
                ps.setString(3, Date);
                ps.setString(4, Time);
                ps.setInt(5, MealID);
                ps.setString(6, Catogery);
                ps.setInt(7, Amount);
                ps.setString(8, InsulinSite);

                ps.executeUpdate();

            } catch (Exception exception) {
                System.out.println("Database error: " + exception.getMessage());
            }
        }*/


    @POST
    @Path("update")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public String updateTracker(
            @FormDataParam("TrackerID") Integer TrackerID, @FormDataParam("UserId") Integer UserID, @FormDataParam("Date") String Date, @FormDataParam("Time") String Time, @FormDataParam("MealID") Integer MealID, @FormDataParam("Catogery") String Catogery, @FormDataParam("Amount") Integer Amount, @FormDataParam("InsulinSite") String InsulinSite) {
        try {
            if (UserID == null || Date == null || Time == null || Catogery == null || Amount == null) {
                throw new Exception("One or more form data parameters are missing in the HTTP request.");
            }
            System.out.println("Tracker/update UserID=" + UserID);
            PreparedStatement ps = Main.db.prepareStatement("UPDATE Tracker SET TrackerID = ?, UserID = ?, Date = ?, Time = ?, MealID = ?, Catogery = ?, Amount = ?, InsulinSite = ?, WHERE UserID = ?");
            ps.setInt(1, TrackerID);
            ps.setInt(2, UserID);
            ps.setString(3, Date);
            ps.setString(4, Time);
            ps.setInt(5, MealID);
            ps.setString(6, Catogery);
            ps.setInt(7, Amount);
            ps.setString(8, InsulinSite);
            ps.execute();
            return "{\"status\": \"OK\"}";

        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
            return "{\"error\": \"Unable to update item, please see server console for more info.\"}";
        }

    }

/*    // This a update crud statement
    public static void updateuser(Integer TrackerID, Integer UserID, String Date, String Time, Integer MealID, String Catogery, Integer Amount, String InsulinSite) {


        try {
            PreparedStatement ps = Main.db.prepareStatement("UPDATE Tracker SET TrackerID = ?,UserID = ?, Date = ?, Time = ?, MealID = ?, Catogery = ?, Amount = ?, InsulinSite = ?");

            ps.setInt(1, TrackerID);
            ps.setInt(2, UserID);
            ps.setString(3, Date);
            ps.setString(4, Time);
            ps.setInt(5, MealID);
            ps.setString(6, Catogery);
            ps.setInt(7, Amount);
            ps.setString(8, InsulinSite);

            ps.executeUpdate();


        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
        }


    }*/


    @POST
    @Path("Delete")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public String DeleteTracker(@FormDataParam("TrackerID") Integer TrackerID) {


        try {
            if (TrackerID == null) {
                throw new Exception("One or more form data parameters are missing in the HTTP request.");
            }
            System.out.println("Tracker/delete UserID=" + TrackerID);

            PreparedStatement ps = Main.db.prepareStatement("DELETE FROM Tracker WHERE TrackerID = ?");

            ps.setInt(1, TrackerID);

            ps.execute();

            return "{\"status\": \"OK\"}";

        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
            return "{\"error\": \"Unable to delete item, please see server console for more info.\"}";
        }
    }

}





    /*public static void deleteTracker(int TrackerID){
        try{
            PreparedStatement ps = Main.db.prepareStatement("DELETE FROM Tracker WHERE TrackerID = ?");

            ps.setInt(1, TrackerID);

            ps.execute();
        } catch(Exception exception){
            System.out.println("Database error: " + exception.getMessage());
        }
    }*/







