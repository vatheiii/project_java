
import java.util.ArrayList;//to store data 
import java.util.Scanner;// to read input of user

public class Choice {


    public static Scanner scanner;


    public Choice(ArrayList<Room> rooms, ArrayList<Tenant> tenants, ArrayList<Contract> contracts) {
        displayMenu(rooms, tenants, contracts);
    }



public static void displayMenu(ArrayList<Room> rooms, ArrayList<Tenant> tenants, ArrayList<Contract> contracts){
        scanner = new Scanner(System.in);
          System.out.println("-------Apartment Management System-------");
          System.out.println("1.Room Information");
          System.out.println("2.Tenent Information");
          System.out.println("3.Contract Information");
          System.out.println("4.Exit");   
          System.out.print("Select an option: ");
                  int choice;
    do{
        choice = scanner.nextInt();
        scanner.nextLine(); 

        switch (choice) {
            case 1:
                System.out.println("Room Information:");
                for (Room room : rooms) {
                    room.displayRoomInfo();
                }
                break;
            case 2:
                System.out.println("Tenant Information:");
                for (Tenant tenant : tenants) {
                    tenant.displayTenantInfo();                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           
                }
                break;
            case 3:
                System.out.println("Contract Information:");
                for (Contract contract : contracts) {
                    contract.displayContractDetails();
                }
                break;
            case 4:
                System.out.println("Exiting the system. Goodbye!");
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }

    }while (choice != 4);
    scanner.close();
        
}


}