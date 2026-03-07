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
    public ArrayList<Iuser> getUsers() {
            return users;
        }
    private Iuser loggedInUser; 
    public RentalSystem() {
        users.add(new LandLord(new UserBase("A01", "admin", "1234"), "012345678", "admin@email.com"));
        users.add(new TenantAcc("001", "tenant","1234" ));
        users.add(new Manager("M01", "manager", "1234", "012345679"));
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
}
