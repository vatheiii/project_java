
import java.util.ArrayList;//to store data 
import java.util.Scanner;// to read input of user
public class Main {

    public static void main(String[] args) {
       Scanner scanner = new Scanner(System.in);
        ArrayList<Room> rooms = new ArrayList<>();
        ArrayList<Tenant> tenants = new ArrayList<>();
        ArrayList<Contract> contracts = new ArrayList<>();
        // Sample Data
        Room room1 = new Room(101, "Single", true, 500.0, 1);
        Room room2 = new Room(102, "Double", false, 800.0, 1);
        rooms.add(room1);   
        rooms.add(room2);
        Tenant tenant1 = new Tenant("Alice", "123-456-7890", "alice@example.com", room1, "T001",25);
        Tenant tenant2 = new Tenant("Bob", "987-654-3210", "bob@example.com", room2, "T002",30);
        tenants.add(tenant1);
        tenants.add(tenant2);
        Contract contract1 = new Contract(room1, tenant1, "2023-01-01", "2023-12-31",30.0,50.0);
        Contract contract2 = new Contract(room2, tenant2, "2023-02-01", "2023-11-30",25.0,45.0);
        contracts.add(contract1);   
        contracts.add(contract2);

        while(true){
            System.out.println("-------Apartment Management System-------");
            System.out.println("1.Room Information");
            System.out.println("2.Tenent Information");
            System.out.println("3.Contract Information");
            System.out.println("4.Exit");   
            System.out.print("Select an option: ");
            int option = scanner.nextInt();
            scanner.nextLine();
            if(option == 1){
                for(Room room : rooms){
                    room.displayRoomInfo();
                    
                }

            }else if(option == 2){
                for(Tenant tenant : tenants){
                    tenant.displayTenantInfo();
                }

            
            }else if(option == 3){
                for(Contract contract : contracts){
                    contract.displayContractDetails();
                }

            }else if (option == 4){
                System.out.println("Exiting the system.");
                break;
    }

}
}
}
