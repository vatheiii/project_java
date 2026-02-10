public class Tenant {
    private static int totalTenants = 0;
    
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
        String phoneRegex = "^\\+855[0-9]{8,9}$";
        if (phoneNumber != null && phoneNumber.matches(phoneRegex)) {
                this.phoneNumber = phoneNumber;
        } else if (phoneNumber == null || phoneNumber.trim().isEmpty()) {
                System.out.println("Phone number cannot be empty.");
        } else {
                System.out.println("Invalid phone number format.");
        }
}
public void setEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        
        if (email != null && email.matches(emailRegex)) {
                this.email = email;
        } else if (email == null || email.trim().isEmpty()) {
                System.out.println("Email cannot be empty.");
        } else {
                System.out.println("Invalid email format. Example: user@example.com");
        }
}
public void setRoom(Room room) {
        if (room != null) {
                this.room = room;
        } else {
                System.out.println("Room cannot be null.");
        }
}
public void setName(String name) {
        String nameRegex = "^[A-Za-z ]{2,}$";
        
        if (name != null && name.matches(nameRegex)) {
                this.name = name;
        } else if (name == null || name.trim().isEmpty()) {
                System.out.println("Name cannot be empty.");
        } else if (name.length() < 2) {
                System.out.println("Name must be at least 2 characters long.");
        } else {
                System.out.println("Invalid name format.");
        }
}
public void setAge(int age) {
        if (age >= 18 && age <= 100) {
                this.age = age;
        } else {
                System.out.println("Age must be between 18 and 100.");
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
