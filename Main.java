
import java.util.ArrayList;//to store data 

public class Main {

    public static void main(String[] args) {

    ArrayList<Room> rooms = new ArrayList<>();
    ArrayList<Tenant> tenants = new ArrayList<>();
    ArrayList<Contract> contracts = new ArrayList<>();

        // Sample Data
        Room room1 = new Room(101, "Single", true, 500.0, 1);
        Room room2 = new Room(102, "Double", false, 800.0, 1);
        rooms.add(room1);   
        rooms.add(room2);
        Tenant tenant1 = new Tenant("Alice", "123-456-7890", "alice@example.com", room1, true, "T001", 25); 
        Tenant tenant2 = new Tenant("Meow", "987-654-3210", "bob@example.com", room2, true, "T002", 30); 
        tenants.add(tenant1);
        tenants.add(tenant2);
        Contract contract1 = new Contract(room1, tenant1, "2023-01-01", tenant1, "2023-12-31", 30.0, 50.0);
        Contract contract2 = new Contract(room2, tenant2, "2023-02-01", tenant2, "2024-01-31", 40.0, 60.0);
        contracts.add(contract1);   
        contracts.add(contract2);

        Choice.displayMenu(rooms, tenants, contracts);

}
}
