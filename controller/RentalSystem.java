package controller;
import java.util.ArrayList;
import Other.Building;
import Other.Room;
import user.LandLord;
import user.Manager;
import user.TenantAcc;
import user.UserBase;
import user.Iuser;

public class RentalSystem {

    public static final String Add_Manager = "Add Manager";
    public static final String Remove_Manager = "Remove Manager";
    public static final String Create_Room = "Create Room";
    public static final String View_Room = "View Room";
    public static final String Update_Room = "Update Room";
    public static final String Create_Tenant = "Create Tenant";
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


    private ArrayList<Iuser> users = new ArrayList<>();// List of all users in the system
    private Iuser loggedInUser;// Currently logged in user

    public RentalSystem() {
        users.add(new LandLord("L01", "landlord", "1234", "0123456789", "landlord@email.com"));
        users.add(new TenantAcc("001", "tenant", "1234"));
        users.add(new Manager("M01", "manager", "1234", "012345679"));
    }

    private String resolveRole(Iuser user) {
        if (user instanceof LandLord) return "LandLord";
        if (user instanceof Manager) return "Manager";
        if (user instanceof TenantAcc) return "Tenant";
        return "Unknown";
    }// Authentication method

    public boolean login(String username, String password) {
        if (username == null || password == null) {
            System.out.println("Invalid username or password!");
            return false;
        }// Loop through users to find a match

        for (Iuser user : users) {
            if (!user.getUserName().equals(username)) continue;
            boolean authenticated;
            if (user instanceof UserBase) {
                authenticated = ((UserBase) user).checkPassword(password);
            } else {
                authenticated = user.getPassword().equals(password);
            }
            
            if (authenticated) { 
                loggedInUser = user;
                System.out.println("Login successful as " + resolveRole(user));
                return true;
            } // If username matches but password doesn't, break early to avoid unnecessary checks
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
// User management methods
    public ArrayList<Iuser> getUsers() {
        return users;
    } 
// Method to add a new user to the system (for demonstration purposes)
    public void addUser(Iuser user) {
        users.add(user);
    }

    public boolean createRoom(Building building, Room room, double rentPrice, int floor) {
        if (!requirePermission(Create_Room)) return false;

        if (rentPrice <= 0) {
            System.out.println("Rent price must be positive.");
            return false;
        }

        if (floor <= 0) {
            System.out.println("Floor must be above ground floor.");
            return false;
        }

        if (building.isRoomExist(room.getRoomId())) {
            System.out.println("Room ID " + room.getRoomId() + " already exists!");
            return false;
        }

        room.setRentPrice(rentPrice);
        room.setFloor(floor);
        building.addRoom(room);

        System.out.println("Room added successfully.");
        return true;
    }

    public void viewRooms(Building building) {
        if (!requirePermission(View_Room)) return;
        building.showRooms();
    }

    public void polymorphismDemo() {
        String[] actions = {
            Add_Manager,
            Create_Room,
            View_Bill
        };

        for (Iuser u : users) {
            System.out.println("=== " + resolveRole(u) + " ===");
            for (String action : actions) {
                System.out.println(action + ": " + u.can(action));
            }
        }
    }
}
