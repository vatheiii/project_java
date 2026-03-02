public class LandLord extends UserBase {

public LandLord(String Id, String Username, String Password) {
    super(Id, Username, Password);
}
@Override
public String getRole(){
    return "Landlord";
}
@Override
public boolean can (String action){
    return true;
}

}
