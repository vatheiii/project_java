public class Tenant {
    String name;
    String age;
    String phoneNumber;
    String email;
    int roomNumber;
    boolean hasActiveContract;
    boolean hasPets;
    int floor;
    
    public Tenant(String name, String phoneNumber, String email, int roomNumber, boolean hasActiveContract, boolean hasPets, int floor, String age) {
        this.name = name;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.roomNumber = roomNumber;
        this.hasActiveContract = hasActiveContract;
        this.hasPets = hasPets;
        this.floor = floor;
    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public boolean hasActiveContract() {
        return hasActiveContract;
    }

    public boolean hasPets() {
        return hasPets;
    }

    public int getFloor() {
        return floor;
    }
}
