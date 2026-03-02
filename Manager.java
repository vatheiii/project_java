public class Manager extends UserBase {

public Manager(String Id, String Username, String Password) {
    super(Id, Username, Password);
}

@Override
public String getRole(){
    return "Manager";
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
}