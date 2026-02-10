
import java.util.ArrayList;

public class MainBuilding {
    public static void main(String[] args) {
        // Create a building
        Building building = new Building("Sunrise Apartments", "123 Main St");

        // Add rooms to the building
        building.addRoom(new Room(101, "Single", true, 500.0, 1));
        building.addRoom(new Room(102, "Double", true, 800.0, 1));
        building.addRoom(new Room(201, "Single", true, 550.0, 2));
        building.addRoom(new Room(202, "Double", true, 850.0, 2));

        // Show available rooms
        building.showRooms();

        // Create tenants
        Tenant tenant1 = new Tenant("Alice Smith", "+85512345678", "alice@example.com", null, "101", 30);
        Tenant tenant2 = new Tenant("Bob Johnson", "+855987654321", "bob@example.com", null, "102", 28);


        // Create contracts for tenants
        Contract contract1 = new Contract(building.getRooms().get(0), tenant1, "2024-01-01", 2.0, 0.15);
        Contract contract2 = new Contract(building.getRooms().get(1), tenant2, "2024-02-01", 2.0, 0.15);
        System.out.println(contract1);
        System.out.println(contract2);

        // Show updated room availability
        building.showRooms();

    }

}
