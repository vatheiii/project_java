public class Tenant {
    String name;
    String phoneNumber;
    String email;
    int roomNumber;
    boolean hasActiveContract;
    boolean hasPets;
    int floor;
    
    public Tenant(String name, String phoneNumber, String email, int roomNumber, boolean hasActiveContract, boolean hasPets, int floor) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.roomNumber = roomNumber;
        this.hasActiveContract = hasActiveContract;
        this.floor = floor;
        this.hasPets = hasPets;
    }

}
