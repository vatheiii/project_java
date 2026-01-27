public class Contract {
    int roomNumber;
    String tenantName;
    String apartmentName;
    String startDate;
    String endDate;
    String contractStatus;
    int monthlyRent;


    public Contract(int roomNumber, String tenantName, String apartmentName,
         String startDate, String endDate, String contractStatus, int monthlyRent ) {
        this.roomNumber = roomNumber;
        this.tenantName = tenantName;
        this.apartmentName = apartmentName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.contractStatus = contractStatus;
        this.monthlyRent = monthlyRent;
    }

}
