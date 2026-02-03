public class Contract {
    Room room;
    Tenant tenant;
    Tenant name;
    String startDate;
    String endDate;
    double WaterBill;
    double ElectricityBill;
    
    public Contract(Room room, Tenant tenant, String startDate, 
        Tenant name,String endDate,double WaterBill, double ElectricityBill) {
        // Constructor implementation
        this.room = room;
        this.tenant = tenant;
        this.startDate = startDate;
        this.endDate = endDate;
        this.WaterBill = WaterBill;
        this.ElectricityBill = ElectricityBill;

    }

    public void displayContractDetails() {
        System.out.println("-------------Contract Details-------------");
        System.out.println("Room Number: " + room.getRoomNumber());
        System.out.println("Tenant Name: " + name.getName());
        System.out.println("Start Date: " + startDate);
        System.out.println("End Date: " + endDate);
        System.out.println("Water Bill: $" + WaterBill);
        System.out.println("Electricity Bill: $" + ElectricityBill);
    }
}