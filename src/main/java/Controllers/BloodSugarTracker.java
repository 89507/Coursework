package Controllers;

import Sever.Main;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class BloodSugarTracker {


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



    // This is a CRUD create statement
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
    }



    // This a update crud statement
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


    }


    public static void deleteBloodSugarTracker(int SugarID){
        try{
            PreparedStatement ps = Main.db.prepareStatement("DELETE FROM BloodSugarTracker WHERE SugarID = ?");

            ps.setInt(1, SugarID);

            ps.execute();
        } catch(Exception exception){
            System.out.println("Database error: " + exception.getMessage());
        }
    }


}
