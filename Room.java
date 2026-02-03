class Room {
    int roomNumber;
    String roomType;
    boolean isAvailable;
    double rentPrice;
    int floor;
    Contract waterRate;
    Contract electricityRate;

    Room(int roomNumber, String roomType, boolean isAvailable, double rentPrice, int floor) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.isAvailable = isAvailable;
        this.rentPrice = rentPrice;
        this.floor = floor;
    }


    @Override
    public String toString() {
        return "Room [roomNumber=" + roomNumber + ", roomType=" + roomType + ", isAvailable=" + isAvailable
                + ", rentPrice=" + rentPrice + ", floor=" + floor + ", waterRate=" + waterRate + ", electricityRate="
                + electricityRate + "]";
    }


    @Override
    public boolean equals(Object obj) {
        // TODO Auto-generated method stub
        return super.equals(obj);
    }


    

    // void displayRoomInfo() {
    //     System.out.println("-------------Room Information-------------");
    //     System.out.println("Room Number: " + roomNumber);
    //     System.out.println("Room Type: " + roomType);
    //     System.out.println("Availability: " + (isAvailable ? "Available" : "Occupied"));
    //     System.out.println("Rent Price: $" + rentPrice);
    //     System.out.println("Floor: " + floor);
    // }
    
}