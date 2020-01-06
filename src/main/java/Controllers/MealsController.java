package Controllers;

import Sever.Main;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MealsController {

    public static void getallMeals() {
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












}
