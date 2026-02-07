public class Tenant {
    private String name;
    private int age;
    private String phoneNumber;
    private String email;
    private Room room;
    private String TenantId;
    
    public Tenant(String name, String phoneNumber, String email,
        Room room, boolean hasActiveContract, String TenantId, int age) {
        this.name = name;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.room = room;
        this.TenantId = TenantId;

    }

    public String getName() {
        return name;
    }
    
    public int getAge() {
        return age;
    }
    
    public String getPhoneNum() {
        return phoneNumber;
    }
    
    public String getEmail() {
        return email;
    }
    
    public Room getRoom() {
        return room;
    }
    
    public String getID() {
        return TenantId;
    }
}