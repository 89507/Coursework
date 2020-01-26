package Controllers;

import Sever.Main;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.swing.*;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
@Path("BloodSugarTracker/")
public class BloodSugarTracker {


    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public static String getallBloodSugarTracker() {
        System.out.println("BloodSugarTracker/list");
        JSONArray list = new JSONArray();

        try {
//      This is a Crud statement to Read
            PreparedStatement ps = Main.db.prepareStatement("SELECT SugarID, UserID, Date, Time, SugarLevel FROM BloodSugarTracker");

            ResultSet results = ps.executeQuery();
            while (results.next()) {

                JSONObject item = new JSONObject();
                item.put("SugarID", results.getInt(1));
                item.put("UserID", results.getInt(2));
                item.put("Date", results.getString(3));
                item.put("Time", results.getString(4));
                item.put("SugarLevel", results.getDouble(5));
                list.add(item);

            }
            return list.toString();

        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
            return "{\"error\": \"Unable to list items, please see server console for more info.\"}";

        }

    }

/*
    public static void getallBloodSugarTracker() {
        System.out.println("BloodSugarTracker/list");

        try {
//      This is a Crud statement to Read
            PreparedStatement ps = Main.db.prepareStatement("SELECT SugarID, USerID, Date, Time, SugarLevel FROM BloodSugarTracker");

            ResultSet results = ps.executeQuery();
            while (results.next()) {
                int SugarID = results.getInt(1);
                int UserID = results.getInt(2);
                String Date = results.getString(3);
                String Time = results.getString(4);
                int SugarLevel = results.getInt(5);

                System.out.println("SugarID: " + SugarID + " ");
                System.out.println("UserID: " + UserID + " ");
                System.out.println("Date: " + Date + " ");
                System.out.println("Time: " + Time + " ");
                System.out.println("SugarLevel: " + SugarLevel + " ");


            }
        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());

        }
    }
*/

    @POST
    @Path("Add")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public String AddBloodSugarTracker(
            @FormDataParam("UserID") Integer UserID, @FormDataParam("Date") Date Date, @FormDataParam("Time") Time Time, @FormDataParam("SugarLevel") Double SugarLevel) {

        try {
            if (UserID == null || Date == null || Time == null || SugarLevel == null) {
                throw new Exception("One or more form data parameters are missing in the HTTP request.");
            }
            System.out.println("add/new UserID=" + UserID);
            PreparedStatement ps = Main.db.prepareStatement("Select CURRENT_USER ()");
           // PreparedStatement ps = Main.db.prepareStatement("select Users (UserID) for current_user insert into BloodSugarTracker UserID, Date, Time, SugarLevel) VALUES (?,?,?,?,)");
            //PreparedStatement ps = Main.db.prepareStatement("INSERT INTO Users (UserID, Date, Time, SugarLevel) VALUES ( ?, ?, ?, ?,)");
            //ps.setInt(1, UserID);
          //ps.setDate(2, Date);
            //ps.setTime(3, Time);
            //ps.setDouble(4, SugarLevel);
            ps.execute();

            return "{\"status\": \"OK\"}";
        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
            return "{\"error\": \"Unable to create new item, please see server console for more info.\"}";
        }
    }


 /*   // This is a CRUD create statement
    public static void addBloodSugarTracker(Integer SugarID, Integer UserID, String Date, String Time, Integer SugarLevel) {

        try {

            PreparedStatement ps = Main.db.prepareStatement("INSERT INTO BloodSugarTracker (SugarID, UserID, Date, Time, SugarLevel) VALUES (?, ?, ?, ?,?)");

            ps.setInt(1, SugarID);
            ps.setInt(2, UserID);
            ps.setString(3, Date);
            ps.setString(4, Time);
            ps.setInt(5, SugarLevel);
            ps.executeUpdate();

        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
        }
    }*/


    // this updates the bloodsugarData data
    @POST
    @Path("update")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public String updateBloodSugarTracker(
            @FormDataParam("UserID") Integer UserID, @FormDataParam("Date") String Date, @FormDataParam("Time") String Time, @FormDataParam("SugarLevel") double SugarLevel) {
        try {
            if (UserID == null || Date == null || Time == null ) {
                throw new Exception("One or more form data parameters are missing in the HTTP request.");
            }
            System.out.println("Users/update UserID=" + UserID);

            PreparedStatement ps = Main.db.prepareStatement("UPDATE Users SET UserID = ?, Date = ?, Time = ?, SugarLevel = ? WHERE UserID = ?");
            ps.setInt(1, UserID);
            ps.setString(2, Date);
            ps.setString(3, Time);
            ps.setDouble(4, SugarLevel);
            ps.execute();
            return "{\"status\": \"OK\"}";

        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
            return "{\"error\": \"Unable to update item, please see server console for more info.\"}";
        }
    }






   /* // This a update crud statement
    public static void updateBloodSugarTracker(Integer SugarID, Integer UserID, String Date, String Time, Integer SugarLevel) {


        try {
            PreparedStatement ps = Main.db.prepareStatement("UPDATE BloodSugarTracker SET SugarID = ?,UserID = ?, Date = ?, Time = ?, SugarLevel = ?");

            ps.setInt(1, SugarID);
            ps.setInt(2, UserID);
            ps.setString(3, Date);
            ps.setString(4, Time);
            ps.setInt(5, SugarLevel);

            ps.executeUpdate();


        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
        }


    }*/


    @POST
    @Path("Delete")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public String DeleteBloodSugarTracker(@FormDataParam("UserID") Integer UserID) {

        try {
            if (UserID == null) {
                throw new Exception("One or more form data parameters are missing in the HTTP request.");
            }
            System.out.println("thing/delete UserID=" + UserID);

            PreparedStatement ps = Main.db.prepareStatement("DELETE FROM BloodSugarTracker WHERE UserID = ?");

            ps.setInt(1, UserID);

            ps.execute();

            return "{\"status\": \"OK\"}";

        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
            return "{\"error\": \"Unable to delete item, please see server console for more info.\"}";

        }
    }
}



























   /* public static void deleteBloodSugarTracker(int SugarID){
        try{
            PreparedStatement ps = Main.db.prepareStatement("DELETE FROM BloodSugarTracker WHERE SugarID = ?");

            ps.setInt(1, SugarID);

            ps.execute();
        } catch(Exception exception){
            System.out.println("Database error: " + exception.getMessage());
        }
    }


}*/
