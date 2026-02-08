import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Room> rooms = new ArrayList<>();  
        ArrayList<Tenant> tenants = new ArrayList<>();
        ArrayList<Contract> contracts = new ArrayList<>();
        ArrayList<Bill>bills=new ArrayList<>();
         // Sample Data
        Room room1 = new Room(101, "Single", true, 500.0, 1);
        Room room2 = new Room(102, "Double", false, 800.0, 1);
        rooms.add(room1);
        rooms.add(room2);
        Tenant tenant1 = new Tenant("Alice", "012345678", "alice@gmail.com", room2, true, "ID12345", 18);
        tenants.add(tenant1);
        Contract contract1 = new Contract( room2 ,tenant1, "2023-01-01", 0.5, 0.5, 1);
        contracts.add(contract1);
        Bill bill1 = new Bill(contract1, 10, 50);
        bills.add(bill1);
        System.out.println(bill1);

        System.out.println("F1: Primitive Copy Proof");
        double originalRent = 500.0;
        double copiedRent = originalRent;
        copiedRent += 100.0;
        System.out.println("Original Rent Price: " + originalRent); 
        System.out.println("Copied Rent Price after modification: " + copiedRent);


        System.out.println("\nF2: Reference Copy Proof");
        Room roomRef1 = room1;
        Room roomRef2 = roomRef1;
        roomRef2.setRentPrice(600.00);
        System.out.println("Room 1 Rent Price: " + roomRef1.getRentPrice());
        System.out.println("Room 2 Rent Price after modification: " + roomRef2.getRentPrice());


        System.out.println("\nF3: Array store Reference Proof");
        Room[] roomArray = new Room[2];
        roomArray[0] = room1;
        roomArray[1] = room2;
        System.out.println("Room 1 Rent Price after modification: " + room1.getRentPrice());
        System.out.println("Room 2 Rent Price (unchanged): " + room2.getRentPrice());

        
        System.out.println("\nF4: Snapshot behavior Proof"); 
        // Save a primitive snapshot of the rent at contract creation (primitive copy)
        double contractSnapshot = contract1.getRentAtContractTime();
        // Modify the room's current rent 
        room2.setRentPrice(900.0);
        System.out.println("Room Rent Price at Contract Time (saved in contract): " + contractSnapshot); 
        System.out.println("Current Room Rent Price after modification: " + room2.getRentPrice());
        System.out.println("Contract Rent Price during creation: " + contract1.getRentAtContractTime());
        System.out.println("Current Room price: " + room1.getRentPrice());
        System.out.println("Current Room price: " + room2.getRentPrice());
        // System.out.println("\nFind Contract by Tenant Name");
        // contract1.findContractByTenant(contracts, "Bob");
    }

}



