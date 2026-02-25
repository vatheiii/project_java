import java.util.Scanner;

public class MainBuilding {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        RentalSystem system = new RentalSystem();
        Building building = new Building("Sunrise Apartment", "Phnom Penh");
        System.out.println("=== Welcome to Rental System ===");
        System.out.print("Username: ");
        String username = sc.nextLine();
        System.out.print("Password: ");
        String password = sc.nextLine();

        if (!system.login(username, password)) {
            System.out.println("Login failed. Exiting.");
            return;
        }
        int choice;
        do {
            System.out.println("\n=== MENU ===");
            System.out.println("1) Room");
            System.out.println("2) Tenant");
            System.out.println("3) Contract");
            System.out.println("4) Bills");
            System.out.println("5) Manager");
            System.out.println("6) Logout");
            System.out.println("7) Exit");
            System.out.print("Choose: ");
            choice = sc.nextInt();
            sc.nextLine(); 
            switch (choice) {
                case 1:  
                    if (!system.requirePermission("CREATE_ROOM")) 
                        break;

                    System.out.print("Enter Room ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Room Type: ");
                    String type = sc.nextLine();
                    System.out.print("Enter Rent Price: ");
                    double price = sc.nextDouble();
                    sc.nextLine();
                    System.out.print("Enter Floor Number: ");
                    int floor = sc.nextInt();
                    sc.nextLine();
                    Room room = new Room(id, type, true, price, floor);
                    building.addRoom(room, price, floor);
                    System.out.println("Room added!");
                    break;

                case 2:  //list
                    building.showRooms();
                    break;

                case 3: 
                    // if (!system.requirePermission("Create_Tenant")) break;
                    // System.out.print("Enter Tenant Name: ");
                    // String name = sc.nextLine();
                    // sc.nextLine();
                    // System.out.print("Enter Tenant Age: ");
                    // int age = sc.nextInt();
                    // sc.nextLine();
                    // System.out.print("Enter Tenant Phone Number: ");
                    // String phoneNumber = sc.nextLine();
                    // sc.nextLine();
                    // System.out.print("Enter Tenant E-Mail: ");
                    // String email = sc.nextLine();
                    // sc.nextLine();
                    // System.out.print("Enter Room Number: ");
                    // int roomNum = sc.nextInt();
                    // sc.nextLine();
                    // System.out.print("Enter Tenant ID: ");
                    // String ID = sc.nextLine();
                    // sc.nextLine();
                    // System.out.print("Enter Tenant Password: ");
                    // String passwords = sc.nextLine();
                    // sc.nextLine();
                    // Tenant tenant = new Tenant(name, age, phoneNumber, email, room, TenantId , password );
                    // tenant.;
                    // System.out.println("Tenant added!");
                    // break;
                    
                case 4:

                    break;
                    
                case 5:
                    break;

                

                case 6:
                    system.logout();
                    break;

                case 7:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 7);

        sc.close();
    }
}
