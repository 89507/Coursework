package Controllers;

import Sever.Main;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MealsController {

    public static void getallMeals() {
        System.out.println("Meals/list");

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













}
