public class Room {
    int roomNumber;
    String roomType;
    boolean inGoodCondition;
    boolean isAvailable;

    public Room(int roomNumber, String roomType, boolean inGoodCondition, boolean isAvailable, double rentPrice) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.inGoodCondition = inGoodCondition;
        this.isAvailable = isAvailable;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public String getRoomType() {
        return roomType;
    }

    public boolean isInGoodCondition() {
        return inGoodCondition;
    }

    public boolean isAvailable() {
        return isAvailable;
    }
}