public class LandLord implements Iuser{
    private String Id;
    private String Username;
    private String Password;

public LandLord(String Id,String Username, String Password){
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
    return "Landlord";
}
public boolean can (String action){
    return true;
}

}
