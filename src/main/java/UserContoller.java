import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserContoller {

    // This method prints out the contents of users from my SQL table
    private static void getallUsers(){

        try {

            PreparedStatement ps = db.prepareStatement("SELECT * FROM Users");

            ResultSet results = ps.executeQuery();
            while (results.next()) {
                int userID = results.getInt(1);
                String username= results.getString(2);
                String score = results.getString(3);
                System.out.println(userID + " " + username + " " + score);
            }

        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
        }

    }
    // this method adds a new user
    private static void adduser(String username, String DOB){

        try {

            PreparedStatement ps = db.prepareStatement("INSERT INTO Users (username, DOB) VALUES (?, ?)");

            ps.setString(1, username);
            ps.setString(2, DOB);

            ps.executeUpdate();

        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
        }


    }
}
