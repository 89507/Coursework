package Controllers;

import Sever.Main;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class TrackerController {


    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)





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


        // This is a CRUD create statement
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
        }

    // This a update crud statement
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


    }

    public static void deleteTracker(int TrackerID){
        try{
            PreparedStatement ps = Main.db.prepareStatement("DELETE FROM Tracker WHERE TrackerID = ?");

            ps.setInt(1, TrackerID);

            ps.execute();
        } catch(Exception exception){
            System.out.println("Database error: " + exception.getMessage());
        }
    }





}

