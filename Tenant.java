class Tenant {
    String name;
    int age;
    String phoneNumber;
    String email;
    Room room;
    String TenantId;
    
    Tenant(String name, String phoneNumber, String email,
        Room room, boolean hasActiveContract, String TenantId, int age) {
        this.name = name;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.room = room;
        this.TenantId = TenantId;

    }

    String getName() {
        return name;
    }

    void displayTenantInfo() {
        System.out.println("-------------Tenant Information-------------");
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Phone Number: " + phoneNumber);
        System.out.println("Email: " + email);
        System.out.println("Room Number: " + room.roomNumber);
        System.out.println("Tenant ID: " + TenantId);
    }
}