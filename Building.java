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

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public void showRooms(){
        System.out.println("Rooms in " + name + ":");
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
