import java.lang.reflect.Array;
import java.util.ArrayList;

public class Building {
    private String name;
    private String address;
    private ArrayList<Room> rooms;

    public Building(String name, String address) {
        this.name = name;
        this.address = address;
        this.rooms = new ArrayList<>();
    }

    public boolean isRoomExist(int roomId) {
        for (Room room : rooms) {
            if (room.getRoomId() == roomId) {
                return true;
            }
        }
        return false; // Room not found
    }

    public Boolean addRoom(Room room , double rentPrice , int floor) {
        
        if (isRoomExist(room.getRoomId())) {
            System.out.println("Room with ID " + room.getRoomId() + " already exists in the building.");
            return false; // Room already exists
        } else if (rentPrice <= 0) {
            System.out.println("Rent price must be positive. Room not added.");
            return false; // Invalid rent price
        } else if (floor <= 0) {
            System.out.println("Floor number must be above ground floor. Room not added.");
            return false; // Invalid floor number
        } else {
            room.setRentPrice(rentPrice); 
            room.setFloor(floor);
            rooms.add(room);
            return true; // Room added successfully
        }
     
    }
 
    public void showRooms(){
        System.out.println( name + ":");
        System.out.println("===============================");
        for (Room room : rooms) {
            System.out.println(room);
        }
    }

    public ArrayList<Room> getRooms() {
        return rooms;
    }


    @Override
    public String toString() {
        return "Building Name: " + name + "\n" +
               "Address: " + address + "\n" +
               "Total Rooms: " + rooms.size();
    }


}
