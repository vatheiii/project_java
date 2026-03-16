package Other;

public class Room {
    private int roomId;
    private String roomType;
    private boolean isAvailable;
    private double rentPrice;
    private int floor;


    public Room(int roomId, String roomType, boolean isAvailable, double rentPrice, int floor) {
        
            this.roomId = roomId;
            this.roomType = roomType;
            this.isAvailable = isAvailable;
            this.rentPrice = rentPrice;
            this.floor = floor;
     
    }
    public int getRoomId() {

        return roomId;
    }

    public double getRentPrice(){
        return rentPrice;
    }
    public boolean isAvailable(){
        return isAvailable;
    }

    public String getRoomType(){
        return roomType;
    }

    public int getFloor() {
        return floor;
    }   

    public boolean setFloor(int floor) {
        if (floor > 0 && floor < 5) {
            this.floor = floor;
            return true;
        } else {
            System.out.println("Floor number must be between 1 and 4.");
            return false;
        }
    }

    public void setAvailable(boolean available){
        this.isAvailable = available;
    }

    public void setRentPrice(double rentPrice){
        if(rentPrice>0){
            this.rentPrice= rentPrice;
        }else{
            System.out.println("Rent price must be positive.");
        }
    }

    
   @Override
        public String toString() {
            return
            "Room Number: " + roomId + ", " + "\n"+
            "Room Type: " + roomType + ", " + "\n"+
            "Floor: " + floor + ", " + "\n"+
            "Rent: $" + rentPrice + ", " + "\n"+
            "Availability: " + (isAvailable()? "Available" : "Not Available") + "\n"+
            "=======================================";

    }
    
}