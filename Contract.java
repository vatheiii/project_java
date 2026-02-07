
public class Contract {
    private Room room;                  //reference type
    private Tenant tenant;              //reference type

    
    // Contract signing details
    private String startDate;           //primitive type
    private String endDate;             //primitive type
    private int durationMonths;         //primitive type

    // Monthly bills at the time of contract signing (in dollars)
    private double waterRate;           //primitive type
    private double electricityRate;     //primitive type
    private double rentAtContractTime;  //primitive type
    
    public Contract(Room room, Tenant tenant, String startDate, double waterRate, double electricityRate) {
        // Constructor implementation
        this.room = room;
        this.tenant = tenant;
        this.startDate = startDate;
        this.waterRate = waterRate;
        this.electricityRate = electricityRate;

        // Capture the rent at the time of contract signing
        this.rentAtContractTime = room.getRentPrice();

    }

    public Tenant getTenant() {
        return tenant;
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
    

}