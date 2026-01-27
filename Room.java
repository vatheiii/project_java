public class Room {
    int roomNumber;
    String roomType;
    boolean isAvailable;
    double rentPrice;
    int floor;

    public Room(int roomNumber, String roomType, boolean isAvailable, double rentPrice, int floor) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.isAvailable = isAvailable;
        this.rentPrice = rentPrice;
        this.floor = floor;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public String getRoomType() {
        return roomType;
    }

    public boolean getIsAvailable() {
        return isAvailable;
    }
    public double getRentPrice() {
        return rentPrice;
    }
    public int getFloor() {
        return floor;
    }
    public void displayRoomInfo() {
        System.out.println("--------------Room Information----------------");
        System.out.println("Room Number: " + roomNumber);
        System.out.println("Room Type: " + roomType);
        System.out.println("Available: " + isAvailable);
        System.out.println("Floor: " + floor);
        System.out.println("Rent Price: $" + rentPrice);

    }
}