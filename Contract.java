public class Contract {
    // Contract signing details
    private Room room;                  
    private Tenant tenant;             
    private String startDate;  

    // water and electricity rates + snapshot of rent at contract time
    private double waterRate;         
    private double electricityRate;   
    private double rentAtContractTime;


    public Contract(Room room, Tenant tenant, String startDate, double waterRate, double electricityRate) {
    // Constructor implementation
    this.room = room;
    this.tenant = tenant;
    this.startDate = startDate;
    this.waterRate = waterRate;
    this.electricityRate = electricityRate;
    room.setAvailable( false);// occupied room

    // rent snapshot at contract time
    this.rentAtContractTime = room.getRentPrice();
    }

    public double getRentAtContractTime() {
        return rentAtContractTime;
    }

    public double getWaterRate() {
        return waterRate;
    }

    public double getElectricityRate() {
        return electricityRate;
    }

    public Tenant getTenant() {
        
        return tenant;
    }

    public Room getRoom() {
        
        return room;
    }
    
    private void setWaterRate(double waterRate) {
        if (waterRate <= 0){
            System.out.println("Water rate cannot be negative nor zero. ");
            return;
        }
        this.waterRate = waterRate;
    }

    private void setElectricityRate(double electricityRate) {
        if (electricityRate <= 0){
            System.out.println("Electricity rate cannot be negative nor zero. ");
            return;
        }
        this.electricityRate = electricityRate;
    }

@Override
public String toString() {
    return 
     "Tenant: " + tenant.getName() + "\n"
          + "Room: " + room.getRoomId() + "\n"
          + "Start Date: " + startDate + "\n"
          + "Water Rate: $" + waterRate + " Per m3\n"
          + "Electricity Rate: $" + electricityRate + " Per kWh\n"
          + "======================================";
    }
}