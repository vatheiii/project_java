public class Manager implements Iuser {
    private String Id;
    private String Username;
    private String Password;

public Manager(String Id,String Username, String Password){
    this.Id=Id;
    this.Username=Username;
    this.Password=Password;
}
public String getID(){
    return Id;
}
public String getUserName(){
    return Username;
}
public String getPassword(){
    return Password;
}
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