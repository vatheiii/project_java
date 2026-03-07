package Other;
public class Bill {
        public Contract getContract() {
            return contract;
        }
    private Room room;
    private Contract contract;
    private int waterUsed;
    private int electricityUsed;
    
public Bill(Contract contract, int waterUsed, int electricityUsed, Room room) {
    this.contract = contract;
    this.waterUsed = waterUsed;
    this.room = room;
    this.electricityUsed = electricityUsed;
}
public double calculateWaterBill(){
    return waterUsed * contract.getWaterRate();
}
public double electricityBill(){
    return electricityUsed*contract.getElectricityRate();
}
public double calculateTotalBill(){
    return room.getRentPrice()
    +calculateWaterBill()
    +electricityBill();
}

 
@Override
public String toString() {
    return  "-------------Monthly Bill-------------\n"
          + "Tenant: " + contract.getTenant().getName() + "\n"
          + "Room: " + contract.getTenant().getRoom().getRoomId() + "\n"
          + "Rent: $" + room.getRentPrice() + "\n"
          + "Water Bill: $" + calculateWaterBill() + " (" + waterUsed + " m3)\n"
          + "Electricity Bill: $" + electricityBill() + " (" + electricityUsed + " kWh)\n"
          + "TOTAL: $" + calculateTotalBill()
          + "\n======================================";
}
}
