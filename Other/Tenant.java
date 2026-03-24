package Other;
public class Tenant {
    private String name;
    private int age;
    private String phoneNumber;
    private String email;
    private Room room;
    private String TenantId;
        private String password;

public Tenant() {
}

public Tenant(String name, String phoneNumber, String email,
        Room room,  String TenantId, int age, String password) {
        setName(name);
        setPhoneNumber(phoneNumber);
        setEmail(email);
        setRoom(room);
        this.TenantId = TenantId;
        setAge(age);
        this.password = password;
}
public String getName() {
        return name;
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

public String getPassword() {
        return password;
}

public boolean setPhoneNumber(String phoneNumber) {
        String phoneRegex = "^\\+855[0-9]{8,9}$";
        if (phoneNumber != null && phoneNumber.matches(phoneRegex)) {
                this.phoneNumber = phoneNumber;
                return true;
        } else if (phoneNumber == null || phoneNumber.trim().isEmpty()) {
                System.out.println("Phone number cannot be empty.");
                return false;
        } else {
                System.out.println("Invalid phone number format.");
                return false;
        }
}
public boolean setEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        
        if (email != null && email.matches(emailRegex)) {
                this.email = email;
                return true;
        } else if (email == null || email.trim().isEmpty()) {
                System.out.println("Email cannot be empty.");
                return false;
        } else {
                System.out.println("Invalid email format. Example: user@example.com");
                return false;
        }
}
public boolean setRoom(Room room) {
        if (room != null) {
                this.room = room;
                return true;
        } else {
                System.out.println("Room cannot be null.");
                return false;
        }
}
public boolean setName(String name) {
        String nameRegex = "^[A-Za-z ]{2,}$";
        
        if (name != null && name.matches(nameRegex)) {
                this.name = name;
                return true;
        } else if (name == null || name.trim().isEmpty()) {
                System.out.println("Name cannot be empty.");
                return false;
        } else if (name.length() < 2) {
                System.out.println("Name must be at least 2 characters long.");
                return false;
        } else {
                System.out.println("Invalid name format.");
                return false;
        }
}
public boolean setAge(int age) {
        
        if (age >= 18 && age <= 100) {
                this.age = age;
                return true;
        } else {
                System.out.println("Age must be between 18 and 100.");
                return false;
        }
}

@Override
public String toString() {
    return  "-------------Tenant Details-------------\n"
          + "ID: " + TenantId + "\n"
          + "Name: " + name + "\n"
          + "Age: " + age + "\n"
          + "Phone: " + phoneNumber + "\n"
          + "Email: " + email + "\n"
          + "Room: " + room.getRoomId()
          +"\n========================================";
          
}

}
