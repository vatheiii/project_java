public class Contract {
    private Room room;                  
    private Tenant tenant;             
  // Contract signing details
    private String startDate;                    

    // Monthly bills at the time of contract signing (in dollars)
    private double waterRate;         
    private double electricityRate;   
    private double rentAtContractTime; 

    private int contractID;                  
    private static int totalContractCount = 0;   //to increment contract ID

    Contract(Room room, Tenant tenant, String startDate, double waterRate, double electricityRate, int contractID) {
    // Constructor implementation
    this.room = room;
    this.tenant = tenant;
    this.startDate = startDate;
    this.waterRate = waterRate;
    this.electricityRate = electricityRate;

    // snapshot of rent at contract time
    this.rentAtContractTime = room.getRentPrice();

    // Use provided positive contractID if given; otherwise auto-increment
    if (contractID > 0) {
        this.contractID = contractID;
        if (contractID > totalContractCount) {
            totalContractCount = contractID;
        }
    } else {
        this.contractID = ++totalContractCount;
    }
}
    void displayContractDetails() {
    System.out.println("-------------Contract Details-------------");
    System.out.println("Contract ID: " + contractID);
    System.out.println("Room Number: " + room.getRoomNumber());
    System.out.println("Tenant Name: " + tenant.getName());
    System.out.println("Start Date: " + startDate);
    System.out.println("Water Rate: $" + waterRate);
    System.out.println("Electricity Rate: $" + electricityRate);
}
    void findContractByTenant(Contract contract, String tenantName) {
        if (tenantName == null || tenantName.trim().isEmpty()) {
            System.out.println("Error: Tenant name cannot be null or empty.");
            return;
        }
        if (this.tenant == null) {
            System.out.println("Error: Contract tenant is null.");
            return;
        }
        if (this.tenant.getName().equals(tenantName)) {
             System.out.println("Contract found for tenant: " + tenantName);
            displayContractDetails();
        }
            else {
                System.out.println("No contract found for tenant: " + tenantName);
            }
    }

    public int contractID() {
        return contractID;
    }

    public double getWaterRate() {
        return waterRate;
    }

    public double getElectricityRate() {
        return electricityRate;
    }

    public double getRentAtContractTime() {
        return rentAtContractTime;
    }

    public Tenant getTenant() {
        return tenant;
    }

    public static int getTotalContractCount() {
        return totalContractCount;
    }

public String toString() {
        return "Contract [ID=" + getTotalContractCount() + 
               ", room=" + (room != null ? room.getRoomNumber() : "null") + 
               ", tenant=" + (tenant != null ? tenant.getName() : "null") + 
               ", waterRate=" + waterRate + 
               ", electricityRate=" + electricityRate + 
               ", startDate=" + startDate + 
               ", rentAtContractTime=" + rentAtContractTime + "]";
    }  
}