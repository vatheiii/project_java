import java.util.ArrayList;
public class RentalSystem {

public static final String Add_Manager = "Add Manager";
public static final String Remove_Manager = "Remove Manager";
public static final String Create_Room = "Create Room";
public static final String View_Room = "View Room";
public static final String Update_Room = "Update Room";
public static final String Create_Tenant= "Create Tenant";
public static final String View_Tenant = "View Tenant";
public static final String Update_Tenant = "Update Tenant";
public static final String Delete_Tenant = "Delete Tenant";
public static final String Create_Contract = "Create Contract";
public static final String View_Contract = "View Contract";
public static final String Update_Contract = "Update Contract";
public static final String Delete_Contract = "Delete Contract";
public static final String Generate_Bill = "Generate Bill";
public static final String View_Bill = "View Bill";
public static final String Update_Bill = "Update Bill";
    

    private ArrayList<Iuser> users = new ArrayList<>();
    private Iuser loggedInUser;
    public RentalSystem() {
        users.add(new LandLord("A01", "admin", "1234"));
        users.add(new TenantAcc("001", "tenant","1234" ));
    }
    public boolean login(String username, String password) {
        for (Iuser user : users) {
            if (user.getUserName().equals(username) && user.getPassword().equals(password)) {
                loggedInUser = user;
                System.out.println("Login successful as " + user.getRole());
                return true;
            }
        }
        System.out.println("Invalid username or password!");
        return false;
    }
    public void logout() {
        loggedInUser = null;
        System.out.println("Logged out");
    }
    public boolean isUserLoggedIn() {
        return loggedInUser != null;
    }

    public Iuser getLoggedInUser() {
        return loggedInUser;
    }
    public void addUser(Iuser user) {
        users.add(user);
    }
    public boolean requirePermission(String action) {
        if (!isUserLoggedIn()) {
            System.out.println("No user logged in!");
            return false;
        }
        if (!loggedInUser.can(action)) {
            System.out.println("You do not have permission to perform this action: " + action);
            return false;
        }
        return true;
    }
}
