import java.util.ArrayList;//to store data 
import java.util.Scanner;// to read input of user

class Choice {
static Scanner scanner;
Choice(ArrayList<Room> rooms, ArrayList<Tenant> tenants, ArrayList<Contract> contracts, ArrayList<Bill>bills) {
        displayMenu(rooms, tenants, contracts,bills);
}
static void displayMenu(ArrayList<Room> rooms, ArrayList<Tenant> tenants, ArrayList<Contract> contracts,ArrayList<Bill>bills){
        scanner = new Scanner(System.in);
        int choice;
        do{  System.out.println("-------Apartment Management System-------");
             System.out.println("1.Room Information");
             System.out.println("2.Tenent Information");
             System.out.println("3.Contract Information");
             System.out.println("4.Bill"); 
             System.out.println("5.Exit");
             System.out.print("Select an option: ");
    
        choice = scanner.nextInt();
        scanner.nextLine(); 

        switch (choice) {
            case 1:
                System.out.println("Room Information:");
                for (Room room : rooms) {
                    System.out.println(room);
                }
                pressEnterToContinue();
                break;
            case 2:
                System.out.println("Tenant Information:");
                for (Tenant tenant : tenants) {
                     System.out.println(tenant);                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          
                }
                pressEnterToContinue();
                break;
            case 3:
                System.out.println("Contract Information:");
                for (Contract contract : contracts) {
                         System.out.println(contract);
                }
                pressEnterToContinue();
                break;
            case 4:
                System.out.println("Bill Information: ");
                   for (Bill bill: bills){
                     System.out.println(bill);
                }
                pressEnterToContinue();
                break;
            case 5:
                System.out.println("Exiting the system. Goodbye!");
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }

    }while (choice != 5);
    scanner.close();
        
}
static void pressEnterToContinue() {
        System.out.println("\nPress ENTER to go back to menu...");
        scanner.nextLine();
    }
}