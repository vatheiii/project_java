public class TenantAcc implements Iuser{
    private String Id;
    private String Username;
    private String Password;
    private Tenant tenant;

public TenantAcc (String Id,String Username, String Password){
    this.Id=Id;
    this.Username=Username;
    this.Password=Password;
}

public TenantAcc(Tenant tenant, String Username, String Password) {
    this.tenant = tenant;
    this.Id = tenant != null ? tenant.getTenantId() : null;
    this.Username = Username;
    this.Password = Password;
}

public String getID(){
    return Id;
}

public Tenant getTenant() {
    return tenant;
}

public boolean hasLinkedTenant() {
    return tenant != null;
}

public String getUserName(){
    return Username;
}
public String getPassword(){
    return Password;
}
public String getRole(){
    return "Tenant";
}
public boolean can (String action){
    if(action == null){
        return false;
    }else{
        return action.equals(RentalSystem.View_Contract) || action.equals(RentalSystem.View_Bill);
    }
}
    
}

