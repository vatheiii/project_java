
import java.util.ArrayList;
import java.util.Scanner;

public class MainBuilding {
    public static void main(String[] args) {
       Scanner scanner = new Scanner(System.in);
        Building building = new Building("Sunrise Apartment", "Phnom Penh");

        for( int i = 0; i<2 ; i++){
        System.out.print("Enter Room ID: ");
        int id = scanner.nextInt();

        System.out.print("Enter Room Type: ");
        String type = scanner.next();

        System.out.print("Enter Rent Price: ");
        double price = scanner.nextDouble();

        System.out.print("Enter Floor Number: ");
        int floor = scanner.nextInt();

        Room newRoom = new Room(id, type, true, price, floor);
        building.addRoom(newRoom, price, floor);
        }
        building.showRooms();
    }
    }
