public class Tenant {
    private static int totalTenants = 0;
    
    private String name;
    private int age;
    private String phoneNumber;
    private String email;
    private Room room;
    private String TenantId;

Tenant(String name, String phoneNumber, String email,
        Room room, boolean hasActiveContract, String TenantId, int age) {
        this.name = name;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.room = room;
        this.TenantId = TenantId;
        totalTenants++;
}
public String getName() {
        return name;
}

public static int getTotalTenants() {
        return totalTenants;
}

public int getAge() {
        return age;
}

public String getPhoneNumber() {
        return phoneNumber;
}

public String getEmail() {
        return email;
}

public Room getRoom() {
        return room;
}

public String getTenantId() {
        return TenantId;
}

public void setPhoneNumber(String phoneNumber) {
        if (phoneNumber != null && !phoneNumber.trim().isEmpty()) {
                this.phoneNumber = phoneNumber;
        } else {
                System.out.println("Phone number cannot be empty.");
        }
}


@Override
public String toString() {
    return "Tenant{" +
            "name='" + name + '\'' +
            ", age=" + age +
            ", phoneNumber='" + phoneNumber + '\'' +
            ", email='" + email + '\'' +
            ", roomNumber=" + room.getRoomNumber() +
            ", TenantId='" + TenantId + '\'' +
            '}';
}

}
