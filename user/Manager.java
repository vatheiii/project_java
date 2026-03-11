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
    if(phone == null || phone.length() < 8) {
        System.out.println("Invalid phone number");
        this.phone = "00000000";
    } else {
        this.phone = phone;
    }
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