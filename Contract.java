
class Contract {
    Room room;                  //reference type
    Tenant tenant;              //reference type

    
    // Contract signing details
    String startDate;           //primitive type
    String endDate;             //primitive type
    int durationMonths;         //primitive type

    // Monthly bills at the time of contract signing (in dollars)
    double waterRate;           //primitive type
    double electricityRate;     //primitive type
    double rentAtContractTime;  //primitive type
    
    Contract(Room room, Tenant tenant, String startDate, double waterRate, double electricityRate) {
        // Constructor implementation
        this.room = room;
        this.tenant = tenant;
        this.startDate = startDate;
        this.waterRate = waterRate;
        this.electricityRate = electricityRate;

        // Capture the rent at the time of contract signing
        this.rentAtContractTime = room.rentPrice;

    }

    void displayContractDetails() {
        System.out.println("-------------Contract Details-------------");
        System.out.println("Room Number: " + room.roomNumber);
        System.out.println("Tenant Name: " + tenant.name);
        System.out.println("Start Date: " + startDate);
        System.out.println("Water Rate: $" + waterRate);
        System.out.println("Electricity Rate: $" + electricityRate);
    }
    
    void findContractByTenant(Contract contract, String tenantName) {
        if (this.tenant.name.equals(tenantName)) {
            System.out.println("Contract found for tenant: " + tenantName);
            displayContractDetails();
        }
    }

    @Override
    public String toString() {
        return "Contract [room=" + room + ", tenant=" + tenant + ", WaterRate=" + waterRate + ", ElectricityRate="
                + electricityRate + ", startDate=" + startDate + ", rentAtContractTime=" + rentAtContractTime + "]";
    }
    

}