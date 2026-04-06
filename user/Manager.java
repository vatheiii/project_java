package user;
import controller.RentalSystem;
public class Manager extends UserBase {
    private String phone;

public Manager(String Id, String Username, String Password, String phone) {
    super(Id, Username, Password);
    setPhone(phone); 
}
public String getPhone() {
    return phone;
}
public void setPhone(String phone) {
    if (phone == null || phone.trim().isEmpty()) {
        System.out.println("Invalid phone number");
        this.phone = "00000000";
        return;
    }
    
    phone = phone.trim();
    String normalizedPhone = null;
    
    // Case 1: Local Cambodian format (0 + 8-9 digits)
    if (phone.startsWith("0") && phone.matches("^0[0-9]{8,9}$")) {
        normalizedPhone = "+855" + phone.substring(1);
    }
    // Case 2: International format (+country code + 8+ digits)
    else if (phone.startsWith("+") && phone.matches("^\\+[0-9]{1,3}[0-9]{8,}$")) {
        normalizedPhone = phone;
    }
    // Invalid format
    else {
        System.out.println("Invalid phone number format.");

        return;
    }
    
    this.phone = normalizedPhone;
}


@Override
public boolean can(String action) {
     if (action == null){
        return false;
      } else {
      return action.equals(RentalSystem.View_Room) 
      || action.equals(RentalSystem.Update_Room) 
      || action.equals(RentalSystem.View_Tenant) 
      || action.equals(RentalSystem.Update_Tenant) 
      || action.equals(RentalSystem.View_Bill)
      || action.equals(RentalSystem.Update_Bill);
    }
}

@Override
public String toString() {
    return super.toString() +
           ", phone='" + phone + '\'';
}
}