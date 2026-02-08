public class Room {
    private int roomNumber;
    private String roomType;
    private boolean isAvailable;
    private double rentPrice;
    private int floor;

    private static int totalRooms =0;

    public Room(int roomNumber, String roomType, boolean isAvailable, double rentPrice, int floor) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.isAvailable = isAvailable;
        this.rentPrice = rentPrice;
        this.floor = floor;
        totalRooms++;
    }

    public int getRoomNumber(){
        return roomNumber;
    }
    public double getRentPrice(){
        return rentPrice;
    }
    public boolean isAvailable(){
        return isAvailable;
    }
    public int getFloor (){
    return floor;
    }
    public String getRoomType(){
        return roomType;
    }

    public void setRentPrice(double rentPrice){
        if(rentPrice>0){
            this.rentPrice= rentPrice;
        }
    }
    public static int getTotalRooms() {
        return totalRooms;
        
    }   
   @Override
        public String toString() {
            return
            "Room Number: " + roomNumber + ", " + "\n"+
            "Room Type: " + roomType + ", " + "\n"+
            "Floor: " + floor + ", " + "\n"+
            "Rent: $" + rentPrice + ", " + "\n"+
            "Available: " + isAvailable;
    }
    
}