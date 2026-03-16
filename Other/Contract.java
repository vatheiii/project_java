package Other;
import java.util.ArrayList;

public class Contract {
    // global list of contracts for simple storage
    private static ArrayList<Contract> contracts = new ArrayList<>();

    // Contract signing details
    private Room room;
    private Tenant tenant;
    private Tenant tenantID;
    private String startDate;

    // water and electricity rates
    private double waterRate;
    private double electricityRate;

    public Contract(Room room, Tenant tenant, String startDate, double waterRate, double electricityRate) {
        this.room = room;
        this.tenant = tenant;
        this.tenantID = tenant; 
        this.startDate = startDate;

        setWaterRate(waterRate);
        setElectricityRate(electricityRate);
        if (room != null) {
            room.setAvailable(false); // mark room occupied
        }
        contracts.add(this);
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

    public String getStartDate() {
        return startDate;
    }

    public void setRoom(Room room) {
        if (room == null) {
            System.out.println("Room cannot be null.");
            return;
        }
        this.room = room;
    }

    public void setWaterRate(double waterRate) {
        if (waterRate <= 0) {
            System.out.println("Water rate cannot be negative nor zero. ");
            return;
        }
        this.waterRate = waterRate;
    }

    public void setElectricityRate(double electricityRate) {
        if (electricityRate <= 0) {
            System.out.println("Electricity rate cannot be negative nor zero. ");
            return;
        }
        this.electricityRate = electricityRate;
    }

    @Override
    public String toString() {
        return "Tenant: " + (tenant != null ? tenant.getName() : "N/A") + "\n"
                + "Tenant ID: " + (tenantID != null ? tenantID.getTenantId() : "N/A") + "\n"
                + "Room: " + (room != null ? room.getRoomId() : "N/A") + "\n"
                + "Start Date: " + startDate + "\n"
                + "Water Rate: $" + waterRate + " Per m3\n"
                + "Electricity Rate: $" + electricityRate + " Per kWh\n"
                + "======================================";
    }
}