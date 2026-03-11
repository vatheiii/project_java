package user;
import Other.Tenant;
import controller.RentalSystem;
public class TenantAcc extends UserBase {
    private Tenant tenant;

public TenantAcc(String Id, String Username, String Password) {
    super(Id, Username, Password);
}

public TenantAcc(Tenant tenant, String Username, String Password) {
        super(tenant != null ? tenant.getTenantId() : null, Username, Password);
        this.tenant = tenant;
}

public Tenant getTenant() {
    return tenant;
}

public boolean hasLinkedTenant() {
    return tenant != null;
}

@Override
    public boolean can(String action) {
        if (action == null) 
            return false;

        return action.equals(RentalSystem.View_Room) ||
               action.equals(RentalSystem.View_Contract) ||
               action.equals(RentalSystem.View_Bill);
    }
    
}

