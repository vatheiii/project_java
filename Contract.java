import java.util.Scanner;

public class Contract {
    // Contract signing details
    private Room room;                  
    private Tenant tenant;             
    private String startDate;  

    // water and electricity rates
    private double waterRate;         
    private double electricityRate;   
    

    public Contract(Room room, Tenant tenant, String startDate, double waterRate, double electricityRate) {
    // Constructor implementation
    this.room = room;
    this.tenant = tenant;
    this.startDate = startDate;
    this.waterRate = waterRate;
    this.electricityRate = electricityRate;
    room.setAvailable( false);// occupied room
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

    private Tenant findContractByTenantID(String tenantID){
        for (int i = 0; i < tenant.size(); i++){
            if (tenant.get(i).getTenantId.equals(tenantID)){
                return tenant;
            }
        }
        return null;
    }

    void contractDisplayMenu(){
        Scanner sc = new Scanner(System.in);
        int contractChoice;
        do {
            System.out.println("1) Add Contract Details");
            System.out.println("2) View Contracts");
            System.out.println("3) Update Contract Details");
            System.out.println("4) Delete Contracts");
            System.out.println("5) Exit");
            System.out.print("Choose: ");
            contractChoice = sc.nextInt();
            sc.nextLine(); 
            switch (contractChoice){
                case 1:
                    System.out.print("Enter Tenant Full Name: ");
                    String tenantName = tenant.getName();
                    sc.nextLine();
                    System.out.print("Enter Room ID: ");
                    int roomId = room.getRoomId();
                    sc.nextLine();
                    System.out.print("Enter Start Date (YYYY-MM-DD): ");
                    String startDate = sc.nextLine();
                    System.out.print("Enter Water Rate: ");
                    double waterRate = sc.nextDouble();
                    System.out.print("Enter Electricity Rate: ");
                    double electricityRate = sc.nextDouble();
                    Contract newContract = new Contract(room, tenant, startDate, waterRate, electricityRate);
                    System.out.println("Contract added successfully!");
                    break;

                case 2: 
                    System.out.println("Find Contractby Tenant ID: ");
                    String tenantID = sc.nextLine();
                    if (findContractByTenantID(tenantID) != null){
                        System.out.println(findContractByTenantID(tenantID));
                    } else {
                        System.out.println("Tenant not found.");
                    }
            }
        } while (contractChoice != 4);
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